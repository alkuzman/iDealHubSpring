package com.bottle.team.model.sharing;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class NewPackageNotice extends AbstractNotice {
    @Relationship(type = "PACKAGE")
    private Package pckg;

    public Package getPckg() {
        return pckg;
    }

    public void setPckg(Package pckg) {
        this.pckg = pckg;
    }
}
