package com.shea.ai.sheaaiagent.service.impl;

import com.shea.ai.sheaaiagent.model.ChatHistory;
import com.shea.ai.sheaaiagent.mapper.ChatHistoryMapper;
import com.shea.ai.sheaaiagent.service.IChatHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对话历史表 服务实现类
 * </p>
 *
 * @author Shea
 * @since 2026-05-07
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements IChatHistoryService {

}
