package com.bottle.team.validation;

import com.bottle.team.model.authentication.User;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.ideas.Solution;
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
public class SolutionValidator implements Validator {
    @Autowired
    IdeaValidator ideaValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return Problem.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Solution solution = new Solution();
        Idea idea =  solution.getIdea();
        if (idea != null)
            ValidationUtils.invokeValidator(ideaValidator, idea, errors);
    }
}
