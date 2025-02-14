package com.redhat.developers.resterrors;

import java.io.Serializable;
import java.time.LocalDate;

public class ClientErrors implements Serializable {

    private int errorCode;
    private String errorMessage;
    private String traceId;

    public ClientErrors(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.traceId = LocalDate.now().toString();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return String.format("ClientErrors{errorCode=%d, errorMessage='%s', traceId='%s'}", errorCode, errorMessage, traceId);
    }
}
