package com.bottle.team.model.awards.bedges;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public class ProblemCoverageBadge extends AbstractBadge<Double, ProblemCoverageBadge> implements TextQualityBadge<Double, ProblemCoverageBadge> {
    @Property(name = "minimumCoverage")
    private Double minimumCoverage;
    @Property(name = "maximumCoverage")
    private Double maximumCoverage;

    public Double getMinimumCoverage() {
        return minimumCoverage;
    }

    public void setMinimumCoverage(Double minimumCoverage) {
        this.minimumCoverage = minimumCoverage;
    }

    public Double getMaximumCoverage() {
        return maximumCoverage;
    }

    public void setMaximumCoverage(Double maximumCoverage) {
        this.maximumCoverage = maximumCoverage;
    }

    @Override
    public boolean fits(Double data) {
        return data.compareTo(getMinimumCoverage()) >= 0 && data.compareTo(getMaximumCoverage()) < 0;
    }
}
