package com.bottle.team.model.ideas.quality;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
public class SolutionQuality {
    @Relationship(type = "PROBLEM_COVERAGE")
    @IndexedEmbedded
    @Boost(1f)
    private ProblemCoverage problemCoverage;
    @Relationship(type = "SNACK_PEAK_QUALITY")
    @IndexedEmbedded
    @Boost(1f)
    private SnackPeakQuality snackPeakQuality;

    public ProblemCoverage getProblemCoverage() {
        return problemCoverage;
    }

    public void setProblemCoverage(ProblemCoverage problemCoverage) {
        this.problemCoverage = problemCoverage;
    }

    public SnackPeakQuality getSnackPeakQuality() {
        return snackPeakQuality;
    }

    public void setSnackPeakQuality(SnackPeakQuality snackPeakQuality) {
        this.snackPeakQuality = snackPeakQuality;
    }
}
