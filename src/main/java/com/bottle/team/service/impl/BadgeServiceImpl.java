package com.bottle.team.service.impl;

import com.bottle.team.model.awards.badges.Badge;
import com.bottle.team.model.awards.badges.ProblemCoverageBadge;
import com.bottle.team.model.awards.badges.SimpleBadge;
import com.bottle.team.model.awards.badges.SnackPeakQualityBadge;
import com.bottle.team.model.ideas.quality.ProblemCoverage;
import com.bottle.team.model.ideas.quality.SnackPeakQuality;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.BadgeRepository;
import com.bottle.team.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
@Service
public class BadgeServiceImpl implements BadgeService {
    final
    private BadgeRepository badgeRepository;
    private ProblemCoverageBadge noProblemCoverageBadge;
    private SnackPeakQualityBadge noSnackPeakQualityBadge;
    private SimpleBadge noInnovativenessBadge;

    @Autowired
    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
        this.noProblemCoverageBadge = new ProblemCoverageBadge();
        this.noProblemCoverageBadge.setName("Poor Problem Coverage");
        this.noProblemCoverageBadge.setDescription("");

        this.noSnackPeakQualityBadge = new SnackPeakQualityBadge();
        this.noSnackPeakQualityBadge.setName("Poor Snack Peak Quality");
        this.noSnackPeakQualityBadge.setDescription("");
    }

    @Override
    public Iterable<Badge> findAll() {
        return badgeRepository.findAll();
    }

    @Override
    public Badge save(Badge object) {
        return badgeRepository.save(object);
    }

    @Override
    public Badge add(Badge object) {
        return this.save(object);
    }

    @Override
    public void delete(Long id) {
        badgeRepository.deleteById(id);
    }

    @Override
    public Badge findById(Long id) {
        return Neo4jUtils.findById(badgeRepository, id);
    }

    @Override
    public Iterable<Badge> save(Iterable<Badge> badges) {
        return badgeRepository.save(badges, 20);
    }

    @Override
    public Iterable<Badge> findByType(String type) {
        if (type == null || type.isEmpty())
            type = "Badge";
        return badgeRepository.findByType(type);
    }

    @Override
    public ProblemCoverageBadge getBadge(ProblemCoverage problemCoverage) {
        Iterable<Badge> badges = this.findByType("ProblemCoverageBadge");
        for (Badge badge: badges) {
            ProblemCoverageBadge problemCoverageBadge = (ProblemCoverageBadge)badge;
            if (problemCoverageBadge.fits(problemCoverage.getCoverage()))
                return problemCoverageBadge;
        }
        return noProblemCoverageBadge;
    }

    @Override
    public SnackPeakQualityBadge getBadge(SnackPeakQuality snackPeakQuality) {
        Iterable<Badge> badges = this.findByType("SnackPeakQualityBadge");
        for (Badge badge: badges) {
            SnackPeakQualityBadge snackPeakQualityBadge = (SnackPeakQualityBadge)badge;
            if (snackPeakQualityBadge.fits(snackPeakQuality.getQuality()))
                return snackPeakQualityBadge;
        }
        return noSnackPeakQualityBadge;
    }
}
