package com.bottle.team.service.impl;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.repository.SecurityProfileRepository;
import com.bottle.team.repository.UserRepository;
import com.bottle.team.service.SecurityProfileService;
import com.bottle.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 2/12/2017.
 */
@Service
public class SecurityProfileServiceImpl implements SecurityProfileService {

    final private SecurityProfileRepository securityProfileRepository;
    //final private UserRepository userRepository;

    @Autowired
    public SecurityProfileServiceImpl(SecurityProfileRepository securityProfileRepository) {
        this.securityProfileRepository = securityProfileRepository;
    }

    @Override
    public Iterable<SecurityProfile> findAll() {
        return null;
    }

    @Override
    public SecurityProfile save(SecurityProfile object) {

        return this.securityProfileRepository.save(object);
    }

    @Override
    public SecurityProfile add(SecurityProfile object) {
        return this.save(object);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SecurityProfile findById(Long id) {
        return null;
    }

    /*
    @Override
    public SecurityProfile findByUserEmailAndCertificateType(String email, CertificateType type) {
        return securityProfileRepository.findByUserEmailAndCertificateType(email, type);
    }
    */

    @Override
    public SecurityProfile findByUserEmail(String email) {
        return securityProfileRepository.findByUserEmail(email);
    }

    @Override
    public SecurityProfile getAuthenticatedUserSecurityProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        SecurityProfile authenticatedUserSecurityProfile = securityProfileRepository
                .findByUserEmail(userContext.getUsername());
        return authenticatedUserSecurityProfile;
    }

    /*
    public SecurityProfile getSecurityProfileForUsernameAndType(String username, CertificateType type) {
        return securityProfileRepository.findByUserEmailAndCertificateType(username, type);
    }
    */
}