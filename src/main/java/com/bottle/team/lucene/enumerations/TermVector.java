package com.bottle.team.lucene.enumerations;

/**
 * Created by AKuzmanoski on 11/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 11/01/2017
 */
public enum TermVector {
    YES,
    NO,
    WITH_OFFSETS,
    WITH_POSITIONS,
    WITH_POSITION_OFFSETS;

    private TermVector() {
    }
}
