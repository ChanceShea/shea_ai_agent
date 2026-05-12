package com.shea.ai.sheaaiagent.tools;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Web搜索工具
 * @author : Shea.
 * @since : 2026/5/11 14:15
 */
public class WebSearchTool {

    private static final String SEARCH_API_KEY = "https://serpapi.com/search?engine=google";
    private final String apiKey;

    public WebSearchTool(String apiKey) {
        this.apiKey = apiKey;
    }

    @Tool(description = "Search for information from Google Search Engine")
    public String searchWeb(@ToolParam(description = "Search query keyword") String query) {
        Map<String,Object> params = new HashMap<>();
        params.put("q", query);
        params.put("api_key", apiKey);
        try{
            String resp = HttpUtil.get(SEARCH_API_KEY, params);
            // 取出前五条结果
            JSONObject jsonObject = JSONUtil.parseObj(resp);
            // 提取出organic_results字段
            JSONArray organicResults = jsonObject.getJSONArray("organic_results");
            List<Object> objects = organicResults.subList(0, 5);
            // 将结果转换为字符串
            return objects.stream()
                    .map(obj -> {
                        JSONObject result = (JSONObject) obj;
                        return result.toString();
                    }).collect(Collectors.joining(","));
        }catch (Exception e){
            return "Error searching: " + e.getMessage();
        }
    }
}
