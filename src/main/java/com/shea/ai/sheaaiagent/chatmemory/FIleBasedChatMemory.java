package com.shea.ai.sheaaiagent.chatmemory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于文件的聊天记忆实现
 * @author : Shea.
 * @since : 2026/5/7 19:57
 */
public class FIleBasedChatMemory implements ChatMemory {

    private final String BASE_DIR;
    private static final Kryo kryo = new Kryo();

    static {
        // 初始化Kryo序列化器，设置自动注册需要序列化的类
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
    }

    public FIleBasedChatMemory(String baseDir) {
        BASE_DIR = baseDir;
        File dir = new File(BASE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void add(String conversationId, Message message) {
        List<Message> messages = getOrCreateConversation(conversationId);
        messages.add(message);
        saveConversation(conversationId, messages);
    }

    @Override
    public void add(String conversationId, List<Message> messages) {
        List<Message> conversation = getOrCreateConversation(conversationId);
        conversation.addAll(messages);
        saveConversation(conversationId, conversation);
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<Message> messageList = getOrCreateConversation(conversationId);
        return messageList.stream()
                .skip(Math.max(0, messageList.size() - lastN))
                .toList();
    }

    @Override
    public void clear(String conversationId) {
        File file = getConversationDir(conversationId);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 每个会话文件单独保存
     * @param conversationId
     * @return
     */
    private File getConversationDir(String conversationId) {
        return new File(BASE_DIR, conversationId + ".kryo");
    }

    /**
     * 获取或创建会话消息
     * @param conversationId
     * @return
     */
    private List<Message> getOrCreateConversation(String conversationId) {
        File file = getConversationDir(conversationId);
        List<Message> messages = new ArrayList<>();
        if (file.exists()) {
            try (Input input = new Input(new FileInputStream(file))) {
                messages = kryo.readObject(input, ArrayList.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    private void saveConversation(String conversationId, List<Message> messages) {
        File file = getConversationDir(conversationId);
        try (Output output = new Output(new FileOutputStream(file))){
            kryo.writeObject(output, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
