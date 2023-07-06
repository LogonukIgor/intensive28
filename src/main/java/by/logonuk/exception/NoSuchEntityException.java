package by.logonuk.exception;

public class NoSuchEntityException extends RuntimeException {

    private String customMessage;

    private Integer errorCode;

    public NoSuchEntityException(String customMessage, Integer errorCode) {
        this.customMessage = customMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "NoSuchEntityException{" +
                "customMessage='" + customMessage + '\'' +
                ", errorCode=" + errorCode +
                "} " + super.toString();
    }
}
