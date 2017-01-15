package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.sharing.Sharable;
import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 09/10/2016.
 */
@NodeEntity
public class Idea extends BaseEntityImpl implements NamedEntity, Sharable {
    @NotEmpty
    @Property(name = "title")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(3.0f)
    private String title;
    @Property(name = "snackPeak")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1.5f)
    private String snackPeak;
    @Relationship(type = "PROBLEM")
    @NotNull
    @IndexedEmbedded
    @Boost(1f)
    private Problem problem;
    @Relationship(type = "OWNER")
    @IndexedEmbedded
    @Boost(0.5f)
    private User owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String constructName() {
        return title;
    }

    @Override
    public String toString() {
        return super.toString() + "Idea{" +
                "title='" + title + '\'' +
                ", problem=" + problem +
                ", owner=" + owner +
                '}';
    }

    public String getSnackPeak() {
        return snackPeak;
    }

    public void setSnackPeak(String snackPeak) {
        this.snackPeak = snackPeak;
    }
}
