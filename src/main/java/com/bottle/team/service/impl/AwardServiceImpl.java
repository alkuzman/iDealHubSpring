package com.bottle.team.service.impl;

import com.bottle.team.model.awards.Award;
import com.bottle.team.model.awards.ProblemCoverageAward;
import com.bottle.team.model.awards.SnackPeakQualityAward;
import com.bottle.team.model.ideas.quality.ProblemCoverage;
import com.bottle.team.model.ideas.quality.SnackPeakQuality;
import com.bottle.team.model.ideas.quality.SolutionQuality;
import com.bottle.team.service.AwardService;
import com.bottle.team.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
@Service
public class AwardServiceImpl implements AwardService {
    private final
    BadgeService badgeService;

    @Autowired
    public AwardServiceImpl(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @Override
    public Iterable<Award> generateAwards(SolutionQuality solutionQuality) {
        List<Award> awards = new ArrayList<>();
        awards.add(generateAward(solutionQuality.getProblemCoverage()));
        awards.add(generateAward(solutionQuality.getSnackPeakQuality()));
        return awards;
    }

    private ProblemCoverageAward generateAward(ProblemCoverage problemCoverage) {
        ProblemCoverageAward problemCoverageAward = new ProblemCoverageAward();
        problemCoverageAward.setTextQuality(problemCoverage);
        problemCoverageAward.setBadge(badgeService.getBadge(problemCoverage));
        return problemCoverageAward;
    }

    private SnackPeakQualityAward generateAward(SnackPeakQuality snackPeakQuality) {
        SnackPeakQualityAward snackPeakQualityAward = new SnackPeakQualityAward();
        snackPeakQualityAward.setTextQuality(snackPeakQuality);
        snackPeakQualityAward.setBadge(badgeService.getBadge(snackPeakQuality));
        return snackPeakQualityAward;
    }
}
