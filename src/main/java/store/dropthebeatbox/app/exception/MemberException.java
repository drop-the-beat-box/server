package store.dropthebeatbox.app.exception;


import store.dropthebeatbox.app.exception.common.CustomException;
import store.dropthebeatbox.app.exception.common.ErrorCode;

public class MemberException extends CustomException {
    public MemberException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}

