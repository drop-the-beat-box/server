package store.dropthebeatbox.app.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.exception.common.ErrorCode;
import store.dropthebeatbox.app.repository.TeamRepository;
import store.dropthebeatbox.app.validation.annotation.ExistTeam;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class TeamExistValidator implements ConstraintValidator<ExistTeam, Long> {

    private final TeamRepository teamRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(!teamRepository.existsById(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.TEAM_REQUEST_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ExistTeam constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
