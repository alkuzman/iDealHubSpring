package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.awards.Award;
import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.sharing.Shareable;
import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by PC on 09/10/2016.
 */
@NodeEntity
public class Idea extends BaseEntityImpl implements NamedEntity, Shareable, DigitalGoods {
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

    @Property(name = "keywords")
    private List<String> keywords;
    @Relationship(type = "AWARD")
    private List<Award> awards;

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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
}
