package com.bottle.team.model.awards;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.awards.badges.TextQualityBadge;
import com.bottle.team.model.ideas.quality.AbstractTextQuality;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public class TextQualityAward<T extends TextQualityBadge, F extends AbstractTextQuality> extends StandardAward<T> {
    @Relationship(type = "QUALITY")
    @IndexedEmbedded
    @Boost(1f)
    private F textQuality;

    public F getTextQuality() {
        return textQuality;
    }

    public void setTextQuality(F textQuality) {
        this.textQuality = textQuality;
    }
}
