package com.shea.ai.sheaaiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoveAppDocumentLoaderTest {

    @Resource
    private LoveAppDocumentLoader loader;

    @Test
    void loadMarkdowns() {
        loader.loadMarkdowns();
    }
}