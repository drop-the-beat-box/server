package store.dropthebeatbox.app.exception;

import org.springframework.security.core.AuthenticationException;
import store.dropthebeatbox.app.exception.common.ErrorCode;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode.name());
    }
}
