package training.employees;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class StartsWithLetterValidator implements ConstraintValidator<StartsWithLetter, String> {

    private String letter;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith(letter);
    }

    @Override
    public void initialize(StartsWithLetter constraintAnnotation) {
        letter = constraintAnnotation.letter();
    }
}
