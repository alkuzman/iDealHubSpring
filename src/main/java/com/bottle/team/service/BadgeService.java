package com.bottle.team.service;

import com.bottle.team.model.awards.badges.Badge;
import com.bottle.team.model.awards.badges.ProblemCoverageBadge;
import com.bottle.team.model.awards.badges.SnackPeakQualityBadge;
import com.bottle.team.model.ideas.quality.ProblemCoverage;
import com.bottle.team.model.ideas.quality.SnackPeakQuality;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
public interface BadgeService extends Service<Badge> {
    Iterable<Badge> save(Iterable<Badge> badges);

    Iterable<Badge> findByType(String type);

    ProblemCoverageBadge getBadge(ProblemCoverage problemCoverage);

    SnackPeakQualityBadge getBadge(SnackPeakQuality snackPeakQuality);
}
