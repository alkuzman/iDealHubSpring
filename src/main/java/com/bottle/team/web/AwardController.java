package com.bottle.team.web;

import com.bottle.team.model.awards.Award;
import com.bottle.team.model.ideas.quality.SolutionQuality;
import com.bottle.team.service.AwardService;
import com.bottle.team.web.helper.BaseEntityIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@RestController
@RequestMapping("/awards")
public class AwardController {
    private final
    AwardService awardService;

    @Autowired
    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @RequestMapping(value = "/factory", method = RequestMethod.POST)
    public BaseEntityIterable<Award> generateAwards(@RequestBody @Valid SolutionQuality solutionQuality) {
        return new BaseEntityIterable<>(awardService.generateAwards(solutionQuality));
    }
}
