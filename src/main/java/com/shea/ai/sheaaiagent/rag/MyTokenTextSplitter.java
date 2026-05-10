package com.shea.ai.sheaaiagent.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Shea.
 * @since : 2026/5/10 15:05
 */
@Component
public class MyTokenTextSplitter {

    public List<Document> splitDocuments(List<Document> document) {
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        return tokenTextSplitter.apply(document);
    }

    public List<Document> splitCustomized(List<Document> document) {
        TokenTextSplitter splitter = new TokenTextSplitter(200,100,10,5000,true);
        return splitter.apply(document);
    }
}
