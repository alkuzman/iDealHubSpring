package com.bottle.team.model.awards;

import com.bottle.team.model.awards.bedges.Badge;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.NewCommentNotice;
import com.bottle.team.model.sharing.NewPackageNotice;
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
        @JsonSubTypes.Type(value = ProblemCoverageAward.class),
        @JsonSubTypes.Type(value = StandardAward.class),
        @JsonSubTypes.Type(value = TextQualityAward.class),
        @JsonSubTypes.Type(value = SnackPeakQualityAward.class)
})
public interface Award<T extends Badge> extends BaseEntity {
    T getBadge();

    void setBadge(T badge);

    String getDescription();

    void setDescription(String description);
}
