package com.shea.ai.sheaaiagent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shea.ai.sheaaiagent.model.ChatHistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 对话历史表 Mapper 接口
 * </p>
 *
 * @author Shea
 * @since 2026-05-07
 */
public interface ChatHistoryMapper extends BaseMapper<ChatHistory> {

    @Select("SELECT * FROM chat_history WHERE conversation_id = #{conversationId}")
    List<ChatHistory> selectByConversationId(String conversationId);

    List<ChatHistory> selectLastNByConversationId(@Param("conversationId") String conversationId, @Param("lastN") int lastN);

    @Delete("DELETE FROM chat_history WHERE conversation_id = #{conversationId}")
    void deleteByConversationId(String conversationId);
}
