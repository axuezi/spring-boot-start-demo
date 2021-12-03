package com.axue.code.generate.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("generate-code")
public class CodeGenerateProperties {

    /**
     * 数据库URL
     */
    private String url;

    /**
     * 项目路径
     */
    private String projectPath;

    /**
     * 包路径
     */
    private String parent;

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 作者，需要更改
     */
    public String author;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
