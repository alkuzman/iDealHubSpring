package com.bottle.team.model.sharing;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class Announcement extends BaseEntityImpl implements Searchable {
    @Relationship(type = "PACKAGE")
    @NotNull
    @IndexedEmbedded
    @Boost(1f)
    private Package pckg;

    public Package getPckg() {
        return pckg;
    }

    public void setPckg(Package pckg) {
        this.pckg = pckg;
    }
}
