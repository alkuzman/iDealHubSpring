package com.bottle.team.model.sharing;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.comments.Commentable;
import com.bottle.team.model.ideas.Contract;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class Package extends BaseEntityImpl implements Commentable {
    @Relationship(type = "SHARABLE")
    @IndexedEmbedded
    @Boost(1f)
    private Sharable sharable;

    @Relationship(type = "CONTRACT")
    @IndexedEmbedded
    @Boost(0.5f)
    private List<Contract> contracts;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Sharable getSharable() {

        return sharable;
    }

    public void setSharable(Sharable sharable) {
        this.sharable = sharable;
    }
}
