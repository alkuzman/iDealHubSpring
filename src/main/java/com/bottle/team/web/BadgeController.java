package com.bottle.team.web;

import com.bottle.team.model.awards.bedges.Badge;
import com.bottle.team.model.awards.bedges.ProblemCoverageBadge;
import com.bottle.team.model.awards.bedges.SnackPeakQualityBadge;
import com.bottle.team.service.BadgeService;
import com.bottle.team.web.helper.BaseEntityIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
@RestController
@RequestMapping(value = "/badges")
public class BadgeController {
    final
    private BadgeService badgeService;

    @Autowired
    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public BaseEntityIterable<Badge> findAll(@RequestParam(required = false) String type) {
        return new BaseEntityIterable<>(badgeService.findByType(type));
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public BaseEntityIterable<Badge> init() {
        List<Badge> badges = new LinkedList<>();

        ProblemCoverageBadge badge1 = new ProblemCoverageBadge();
        badge1.setMaximumCoverage(100.0);
        badge1.setMinimumCoverage(75.0);
        badge1.setName("Excellent Problem Coverage");
        badge1.setDescription("This badge is for excellent problem coverage which means that coverage is between 75% and 100%");
        badges.add(badge1);

        ProblemCoverageBadge badge2 = new ProblemCoverageBadge();
        badge2.setMaximumCoverage(75.0);
        badge2.setMinimumCoverage(50.0);
        badge2.setName("Good Problem Coverage");
        badge2.setDescription("This badge is for good problem coverage which means that coverage is between 50% and 75%");
        badge2.setNext(badge1);
        badges.add(badge2);

        ProblemCoverageBadge badge3 = new ProblemCoverageBadge();
        badge3.setMaximumCoverage(50.0);
        badge3.setMinimumCoverage(25.0);
        badge3.setName("Fair Problem Coverage");
        badge3.setDescription("This badge is for fair problem coverage which means that coverage is between 25% and 50%");
        badge3.setNext(badge2);
        badges.add(badge3);

        ProblemCoverageBadge badge4 = new ProblemCoverageBadge();
        badge4.setMaximumCoverage(25.0);
        badge4.setMinimumCoverage(0.0);
        badge4.setName("Poor Problem Coverage");
        badge4.setDescription("This badge is for poor problem coverage which means that coverage is between 0% and 25%");
        badge4.setNext(badge3);
        badges.add(badge4);

        SnackPeakQualityBadge badge = new SnackPeakQualityBadge();
        badge.setName("Snack Peak Quality");
        badge.setDescription("This badge is for the achieved snack peak quality.");
        badge.setMaximumSnackPeakQuality(Double.MAX_VALUE);
        badge.setMinimumSnackPeakQuality(0.8);
        badges.add(badge);

        return new BaseEntityIterable<>(badgeService.save(badges));
    }
}