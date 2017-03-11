package com.bottle.team.web;

import com.bottle.team.model.awards.badges.Badge;
import com.bottle.team.model.awards.badges.ProblemCoverageBadge;
import com.bottle.team.model.awards.badges.SnackPeakQualityBadge;
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
        badge1.setMaximumCoverage(Double.MAX_VALUE);
        badge1.setMinimumCoverage(0.65);
        badge1.setName("Excellent Problem Coverage");
        badge1.setDescription("This badge is for excellent problem coverage which means that coverage is between 75% and 100%");
        badges.add(badge1);

        ProblemCoverageBadge badge2 = new ProblemCoverageBadge();
        badge2.setMaximumCoverage(0.65);
        badge2.setMinimumCoverage(0.40);
        badge2.setName("Good Problem Coverage");
        badge2.setDescription("This badge is for good problem coverage which means that coverage is between 50% and 75%");
        badge2.setNext(badge1);
        badges.add(badge2);

        ProblemCoverageBadge badge3 = new ProblemCoverageBadge();
        badge3.setMaximumCoverage(0.4);
        badge3.setMinimumCoverage(0.15);
        badge3.setName("Fair Problem Coverage");
        badge3.setDescription("This badge is for fair problem coverage which means that coverage is between 25% and 50%");
        badge3.setNext(badge2);
        badges.add(badge3);

        SnackPeakQualityBadge badge = new SnackPeakQualityBadge();
        badge.setName("Excellent Snack Peak Quality");
        badge.setDescription("This badge is for the achieved Excellent snack peak quality.");
        badge.setMaximumSnackPeakQuality(Double.MAX_VALUE);
        badge.setMinimumSnackPeakQuality(1.5);
        badges.add(badge);

        SnackPeakQualityBadge badge_1 = new SnackPeakQualityBadge();
        badge_1.setName("Good Snack Peak Quality");
        badge_1.setDescription("This badge is for the achieved good snack peak quality.");
        badge_1.setMaximumSnackPeakQuality(1.5);
        badge_1.setMinimumSnackPeakQuality(0.8);
        badge_1.setNext(badge);
        badges.add(badge_1);

        return new BaseEntityIterable<>(badgeService.save(badges));
    }
}