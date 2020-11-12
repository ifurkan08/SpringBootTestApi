package com.testApi.springBootTestApi.Entities;

public class Command {
    private String Target;
    private String TargetType;
    private String Command;
    private String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getTargetType() {
        return TargetType;
    }

    public void setTargetType(String targetType) {
        TargetType = targetType;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }
}
