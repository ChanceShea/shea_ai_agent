package com.shea.ai.sheaaiagent.chatmemory;

import com.shea.ai.sheaaiagent.mapper.ChatHistoryMapper;
import com.shea.ai.sheaaiagent.model.ChatHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于MySQL的聊天记忆
 * @author : Shea.
 * @since : 2026/5/7 21:58
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MysqlChatMemory implements ChatMemory {

    private final ChatHistoryMapper chatHistoryMapper;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    @Transactional
    public void add(String conversationId, List<Message> messages) {
        log.info("保存聊天记录：{}", conversationId);
        // 异步保存聊天记录，不阻塞主线程
//        executor.submit(() -> saveMessages(conversationId, messages));
        saveMessages(conversationId, messages);
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        log.info("获取聊天记录：{}", conversationId);
        List<ChatHistory> histories = chatHistoryMapper.selectLastNByConversationId(conversationId, lastN);
        return histories.stream()
                .map(this::convertToMessage)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void clear(String conversationId) {
        log.info("删除聊天记录：{}", conversationId);
        chatHistoryMapper.deleteByConversationId(conversationId);
    }

    private void saveMessages(String conversationId, List<Message> messages) {
        List<ChatHistory> chatHistories = new ArrayList<>();
        for (Message message : messages) {
            if (shouldIgnoreMessage(message)) continue;

            ChatHistory history = ChatHistory.builder()
                    .conversationId(conversationId)
                    .role(message.getMessageType().getValue())
                    .content(message.getText())
                    .build();
            chatHistories.add(history);
        }

        if (!chatHistories.isEmpty()) {
            chatHistoryMapper.insert(chatHistories);
        }
    }

    private boolean shouldIgnoreMessage(Message message) {
        return message == null ||
                message.getText() == null ||
                message.getText().isBlank();
    }

    private Message convertToMessage(ChatHistory history) {
        return switch (history.getRole()) {
            case "user" -> new UserMessage(history.getContent());
            case "assistant" -> new AssistantMessage(history.getContent());
            case "system" -> new SystemMessage(history.getContent());
            default -> {
                log.warn("未知的消息类型: {}", history.getRole());
                yield null;
            }
        };
    }
}
