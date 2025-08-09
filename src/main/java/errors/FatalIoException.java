package errors;

public class FatalIoException extends AppException {
    public FatalIoException(String message, Throwable cause) { super(message, cause); }
}