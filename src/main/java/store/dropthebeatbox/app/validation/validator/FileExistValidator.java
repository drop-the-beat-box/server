package store.dropthebeatbox.app.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.exception.common.ErrorCode;
import store.dropthebeatbox.app.repository.FileRepository;
import store.dropthebeatbox.app.validation.annotation.ExistFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class FileExistValidator implements ConstraintValidator<ExistFile, Long> {

    private final FileRepository fileRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(!fileRepository.existsById(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.FILE_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ExistFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
