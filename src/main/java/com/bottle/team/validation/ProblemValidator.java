package com.bottle.team.validation;

import com.bottle.team.model.authentication.Person;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.ideas.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by AKuzmanoski on 24/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 24/11/2016
 */
@Component
public class ProblemValidator implements Validator {
    @Autowired AuthenticatedUserValidation authenticatedUserValidation;

    @Override
    public boolean supports(Class<?> aClass) {
        return Problem.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Problem problem = (Problem) o;
        Person questioner = problem.getQuestioner();
        if (questioner != null)
            ValidationUtils.invokeValidator(authenticatedUserValidation, questioner.user(), errors);
    }
}
