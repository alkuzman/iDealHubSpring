package com.bottle.team.model.awards.bedges;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public interface TextQualityBadge<F, T extends TextQualityBadge> extends Badge<F, T> {
}
