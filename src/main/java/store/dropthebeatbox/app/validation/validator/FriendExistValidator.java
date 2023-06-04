package store.dropthebeatbox.app.validation.validator;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.exception.common.ErrorCode;
import store.dropthebeatbox.app.repository.FriendRepository;
import store.dropthebeatbox.app.validation.annotation.ExistFriend;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class FriendExistValidator implements ConstraintValidator<ExistFriend, Long> {

    private final FriendRepository friendRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(!friendRepository.existsByTargetId(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.FRIEND_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ExistFriend constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
