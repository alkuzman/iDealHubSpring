package com.bottle.team.service;

import com.bottle.team.model.enumaration.CertificateType;
import com.bottle.team.model.security.SecurityProfile;

/**
 * Created by Viki on 2/4/2017.
 */

public interface SecurityProfileService extends Service<SecurityProfile> {

    SecurityProfile findByUserIdAndCertificateType(Long userId, CertificateType type);

}
