package com.shea.ai.sheaaiagent;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {MybatisPlusAutoConfiguration.class})
//@MapperScan("com.shea.ai.sheaaiagent.mapper")
public class SheaAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheaAiAgentApplication.class, args);
    }

}
