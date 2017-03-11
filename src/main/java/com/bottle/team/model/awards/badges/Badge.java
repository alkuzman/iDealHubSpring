package com.bottle.team.model.awards.badges;

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
        @JsonSubTypes.Type(value = AbstractBadge.class),
        @JsonSubTypes.Type(value = ProblemCoverageBadge.class),
        @JsonSubTypes.Type(value = SnackPeakQualityBadge.class)
})
public interface Badge<F, T extends Badge> extends BaseEntity {
    T getNext();

    void setNext(T badge);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    boolean fits(F data);
}
