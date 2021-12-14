package com.example.booksforgram.model.validator;

import com.example.booksforgram.service.EmailService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailNewsletterValidator implements ConstraintValidator<UniqueEmailNewsletter,String> {
    private final EmailService emailService;

    public UniqueEmailNewsletterValidator(EmailService emailService) {

        this.emailService = emailService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }
        return emailService.isEmailFree(email);
    }
}
