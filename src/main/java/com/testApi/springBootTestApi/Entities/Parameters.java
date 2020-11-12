package com.testApi.springBootTestApi.Entities;

import java.util.List;

public class Parameters {
    private String Browser;
    private String Url;
    private List<Command> Commands;


    public List<Command> getCommands() {
        return Commands;
    }

    public void setCommands(List<Command> commands) {
        Commands = commands;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
