package com.bottle.team.model.awards.bedges;

/**
 * Created by AKuzmanoski on 08/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 08/03/2017
 */
public class SimpleBadge extends AbstractBadge<Number, SimpleBadge> {
    @Override
    public boolean fits(Number data) {
        return true;
    }
}
