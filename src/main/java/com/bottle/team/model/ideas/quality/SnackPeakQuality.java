package com.bottle.team.model.ideas.quality;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public class SnackPeakQuality extends AbstractTextQuality {
    @Property(name = "quality")
    private Double quality;

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }
}
