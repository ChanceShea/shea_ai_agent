package com.shea.ai.sheaaiagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 终端操作工具
 * @author : Shea.
 * @since : 2026/5/11 14:58
 */
public class TerminalOperationTool {

    @Tool(description = "Execute a command in the terminal")
    public String executeTerminalCommand(
            @ToolParam(description = "Command to execute in the terminal") String command
    ) {
        StringBuilder builder = new StringBuilder();
        try {
            // Windows 环境正确写法
            ProcessBuilder pb;
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Windows: 使用 cmd /c 来执行命令并退出
                pb = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                // Linux/Mac: 使用 sh -c
                pb = new ProcessBuilder("sh", "-c", command);
            }

            pb.redirectErrorStream(true); // 合并错误流到输入流，避免阻塞
            Process process = pb.start();

            // 必须消费输入流，否则缓冲区满会阻塞
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(),"GBK"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                builder.append("Command execution failed with exit code: ").append(exitCode);
            }

        } catch (Exception e) {
            builder.append("Command execution failed: ").append(e.getMessage());
        }
        return builder.toString();
    }
}
