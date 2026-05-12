package com.shea.ai.sheaaiagent.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.IOException;

/**
 * 网页抓取工具
 * @author : Shea.
 * @since : 2026/5/11 14:55
 */
public class WebScrapingTool {

    @Tool(description = "Scrape a web page and return the HTML content")
    public String scrapeWebPage(@ToolParam(description = "The URL of the web page to scrape") String url) {
        try {
            Document document = Jsoup.connect(url).get();
            return document.html();
        } catch (IOException e) {
            return "Failed to scrape web page: " + e.getMessage();
        }
    }
}
