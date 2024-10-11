package com.maua.backendMetro.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageWriterEntity extends Throwable {
    private String message;

    public MessageWriterEntity(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageWriter{" +
                "message='" + message + '\'' +
                '}';
    }
}