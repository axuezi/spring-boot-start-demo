package com.axue.code.generate.config;


import com.axue.code.generate.GenerateCode;
import com.axue.code.generate.properties.CodeGenerateProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * ConditionalOnClass: 当classpath中出现了Jedis这个类时，才会进行相应的配置。conditional是"有条件的"意思
 * <p>
 * ConfigurationProperties: 注解主要用来把properties配置文件转化为bean来使用的，
 * EnableConfigurationProperties: 注解的作用是@ConfigurationProperties注解生效。
 * 如果只配置@ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的。
 */
@ConditionalOnClass({GenerateCode.class})
@Configuration
@EnableConfigurationProperties(CodeGenerateProperties.class)
public class CodeGenerateAutoConfiguration {

    @Resource
    CodeGenerateProperties properties;

    @Bean
    public GenerateCode generateCode() {
        return new GenerateCode(properties.getUrl(),
                properties.getProjectPath(),
                properties.getParent(),
                properties.getUsername(),
                properties.getPassword(),
                properties.getAuthor());
    }

}
