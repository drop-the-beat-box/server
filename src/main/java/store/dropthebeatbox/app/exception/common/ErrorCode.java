package store.dropthebeatbox.app.exception.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Common
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증정보가 유효하지 않습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    // Auth
    JWT_BAD_REQUEST(HttpStatus.UNAUTHORIZED, "잘못된 JWT 서명입니다."),
    JWT_ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다."),
    JWT_REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 만료되었습니다. 다시 로그인하시기 바랍니다."),
    JWT_UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 JWT 토큰입니다."),
    JWT_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "유효한 JWT 토큰이 없습니다."),
    UNVERIFIED_PHONE_NUMBER(HttpStatus.CONFLICT, "인증되지 않은 휴대전화 번호입니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 사용자가 존재하지 않습니다."),
    MEMBER_ACCESS_DENIED(HttpStatus.FORBIDDEN, "본인 회사의 구성원만 조회할 수 있습니다."),
    MEMBER_UPDATE_SAME(HttpStatus.CONFLICT, "본인 정보는 내 정보 수정을 이용하세요."),
    PHONE_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "중복된 휴대폰번호입니다."),

    // File
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 파일이 존재하지 않습니다"),

    // Friend
    FRIEND_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저와 친구가 아닙니다"),

    //Friend Request
    FRIEND_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 친구 요청은 존재하지 않습니다"),

    //Team
    TEAM_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 팀이 존재하지 않습니다");
    private final HttpStatus status;
    private final String message;


    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
