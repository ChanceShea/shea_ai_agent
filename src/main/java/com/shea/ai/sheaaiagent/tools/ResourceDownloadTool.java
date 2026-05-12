package com.shea.ai.sheaaiagent.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.File;

import static com.shea.ai.sheaaiagent.constant.FileConstant.FILE_SAVE_DIR;

/**
 * 资源下载工具
 * @author : Shea.
 * @since : 2026/5/11 15:14
 */
public class ResourceDownloadTool {

    @Tool(description = "Download a resource from a given URL")
    public String downloadResource(
            @ToolParam(description = "URL of the resource to download") String url,
            @ToolParam(description = "Name of the file to save the resource as") String fileName
    ) {
        String fileDir = FILE_SAVE_DIR + "/download";
        String filePath = fileDir + "/" + fileName;
        try {
            FileUtil.mkdir(filePath);
            HttpUtil.downloadFile(url,new File(filePath));
            return "Resource downloaded successfully: " + filePath;
        }catch (Exception e) {
            return "Error downloading resource: " + e.getMessage();
        }
    }
}
