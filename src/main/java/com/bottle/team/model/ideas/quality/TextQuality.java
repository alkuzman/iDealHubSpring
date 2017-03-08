package com.bottle.team.model.ideas.quality;

import com.bottle.team.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractTextQuality.class),
        @JsonSubTypes.Type(value = ProblemCoverage.class),
        @JsonSubTypes.Type(value = SnackPeakQuality.class)
})
public interface TextQuality extends BaseEntity {
}
