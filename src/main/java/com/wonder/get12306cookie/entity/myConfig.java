package com.wonder.get12306cookie.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="myconfig")
public class myConfig {

    public String shell_command;

    public String tk_path;

    public String getShell_command() {
        return shell_command;
    }

    public void setShell_command(String shell_command) {
        this.shell_command = shell_command;
    }

    public Integer isLinux;


    public String getTk_path() {
        return tk_path;
    }

    public void setTk_path(String tk_path) {
        this.tk_path = tk_path;
    }

    public Integer getIsLinux() {
        return isLinux;
    }

    public void setIsLinux(Integer isLinux) {
        this.isLinux = isLinux;
    }

    @Override
    public String toString() {
        return "myConfig{" +
                "shell_command='" + shell_command + '\'' +
                ", tk_path='" + tk_path + '\'' +
                ", isLinux=" + isLinux +
                '}';
    }
}
