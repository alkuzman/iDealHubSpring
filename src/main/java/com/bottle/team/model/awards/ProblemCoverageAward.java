package com.bottle.team.model.awards;

import com.bottle.team.model.awards.bedges.ProblemCoverageBadge;
import com.bottle.team.model.ideas.quality.ProblemCoverage;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public class ProblemCoverageAward extends TextQualityAward<ProblemCoverageBadge, ProblemCoverage> {
}
