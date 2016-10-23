package com.bottle.team.model.sharing;

import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
public class NewPackageNotice extends Notice {
    @Relationship(type = "PACKAGE")
    private Package aPackage;

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
