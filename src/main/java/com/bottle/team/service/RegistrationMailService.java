package com.bottle.team.service;

import com.bottle.team.model.authentication.User;

/**
 * Created by AKuzmanoski on 13/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/11/2016
 */
public interface RegistrationMailService {
    void sendActivationMail(User user, boolean isAsync);
}
