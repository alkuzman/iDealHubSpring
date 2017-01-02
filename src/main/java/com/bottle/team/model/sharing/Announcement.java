package com.bottle.team.model.sharing;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class Announcement extends BaseEntityImpl {
    @Relationship(type = "PACKAGE")
    private Package aPackage;

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
