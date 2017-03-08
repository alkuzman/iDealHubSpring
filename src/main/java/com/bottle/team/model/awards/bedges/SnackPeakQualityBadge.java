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
public class SnackPeakQualityBadge extends AbstractBadge<Double, SnackPeakQualityBadge> implements TextQualityBadge<Double, SnackPeakQualityBadge> {
    @Property(name = "minimumSnackPeakQuality")
    private Double minimumSnackPeakQuality;
    @Property(name = "maximumSnackPeakQuality")
    private Double maximumSnackPeakQuality;

    public Double getMinimumSnackPeakQuality() {
        return minimumSnackPeakQuality;
    }

    public void setMinimumSnackPeakQuality(Double minimumSnackPeakQuality) {
        this.minimumSnackPeakQuality = minimumSnackPeakQuality;
    }

    public Double getMaximumSnackPeakQuality() {
        return maximumSnackPeakQuality;
    }

    public void setMaximumSnackPeakQuality(Double maximumSnackPeakQuality) {
        this.maximumSnackPeakQuality = maximumSnackPeakQuality;
    }

    @Override
    public boolean fits(Double data) {
        return data.compareTo(getMinimumSnackPeakQuality()) >= 0 && data.compareTo(getMaximumSnackPeakQuality()) < 0;
    }
}
