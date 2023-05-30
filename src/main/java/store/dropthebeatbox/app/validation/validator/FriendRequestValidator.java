package store.dropthebeatbox.app.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.exception.common.ErrorCode;
import store.dropthebeatbox.app.repository.FriendRequestRepository;
import store.dropthebeatbox.app.validation.annotation.ExistFriendRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class FriendRequestValidator implements ConstraintValidator<ExistFriendRequest, Long> {

    private final FriendRequestRepository friendRequestRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(!friendRequestRepository.existsById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.FRIEND_REQUEST_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ExistFriendRequest constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
