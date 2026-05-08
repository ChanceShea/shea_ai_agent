package com.shea.ai.sheaaiagent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shea.ai.sheaaiagent.mapper")
public class SheaAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheaAiAgentApplication.class, args);
    }

}
