package pl.kul.todoapp.enums;

import lombok.Getter;

public enum TaskStatusEnum {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    @Getter
    private final String status;

    TaskStatusEnum(final String status) {
        this.status = status;
    }
}
