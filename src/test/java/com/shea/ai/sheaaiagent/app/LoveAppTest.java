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

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是Shea，我想让另一半（Aehs）更爱我，但我不知道该怎么做，请你直接告诉我该怎么做";
        LoveApp.LoveReport answer = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message = "我结婚了，但是我的婚后生活并不幸福，我想让我的另一半（Aehs）更爱我，但我不知道该怎么做，请你直接告诉我该怎么做";
        String answer = loveApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithTools() {
        // 测试联网搜索
        testMessage("周末想带女朋友去南昌约会，推荐几个适合情侣的小众打卡地？");
        // 测试从网站抓取
        testMessage("最近和对象吵架了，看看编程导航网站（codefather.cn）的其他情侣是怎么解决矛盾的？");
        // 测试下载图片
        testMessage("直接下载一张适合做手机壁纸的心空情侣图片为文件");
        // 测试执行中断命令
        testMessage("执行Python3脚本来生成数据分析报告");
        // 测试保存文件
        testMessage("保存我的恋爱档案为文件");
        // 测试生成PDF
        testMessage("生成一份‘七夕约会计划’PDF，包含餐厅预订、活动流程和礼物清单");
    }

    private void testMessage(String message) {
        String chatId = UUID.randomUUID().toString();
        String answer = loveApp.doChatWithTools(message, chatId);
        Assertions.assertNotNull(answer);
    }
}