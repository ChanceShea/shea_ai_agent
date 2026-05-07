package com.shea.ai.sheaaiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void doChat() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是Shea";
        String answer = loveApp.doChat(message, chatId);

        message = "我想让我的另一半aehs更爱我";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);

        message = "我叫什么";
        answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }
}