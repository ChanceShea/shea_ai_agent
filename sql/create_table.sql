CREATE DATABASE IF NOT EXISTS shea_ai_agent ;
USE shea_ai_agent;
CREATE TABLE `chat_history` (
                                `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                `conversation_id` VARCHAR(64) NOT NULL COMMENT '会话ID（同一对话共享）',
                                `role`            VARCHAR(32) NOT NULL COMMENT '消息角色：system/user/assistant/tool/function 等',
                                `content`         LONGTEXT    NOT NULL COMMENT '消息内容（文本或JSON字符串）',
                                `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                KEY `idx_conversation_id` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对话历史表';