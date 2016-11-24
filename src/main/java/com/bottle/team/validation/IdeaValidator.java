package com.bottle.team.validation;

import com.bottle.team.model.authentication.User;
import com.bottle.team.model.ideas.Idea;
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
public class IdeaValidator implements Validator {
    @Autowired
    AuthenticatedUserValidation authenticatedUserValidation;

    @Autowired
    ProblemValidator problemValidator;


    @Override
    public boolean supports(Class<?> aClass) {
        return Idea.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Idea idea = (Idea) o;
        User user = idea.getOwner();
        Problem problem = idea.getProblem();
        if (user != null)
            ValidationUtils.invokeValidator(authenticatedUserValidation, user, errors);
        if (problem != null)
            ValidationUtils.invokeValidator(problemValidator, problem, errors);
    }
}
