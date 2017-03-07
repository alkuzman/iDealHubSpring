package com.bottle.team.service;

import com.bottle.team.model.awards.Award;
import com.bottle.team.model.ideas.quality.SolutionQuality;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
public interface AwardService {
    Iterable<Award> generateAwards(SolutionQuality solutionQuality);
}
