package com.shea.ai.sheaaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TerminalOperationToolTest {

    @Test
    void executeTerminalCommand() {
        TerminalOperationTool tool = new TerminalOperationTool();
        String result = tool.executeTerminalCommand("dir");
        Assertions.assertNotNull(result);
    }
}