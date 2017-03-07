package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Person;
import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.sharing.Sharable;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Viki on 10/14/2016.
 */
@NodeEntity
public class Problem extends BaseEntityImpl implements NamedEntity, Sharable {
    @NotEmpty
    @Property(name = "title")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(3.0f)
    private String title;

    @Property(name = "text")
    @NotEmpty
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1.0f)
    private String text;

    @Relationship(type = "QUESTIONER")
    @NotNull
    @IndexedEmbedded()
    @Boost(0.5f)
    private Person questioner;

    @Relationship(type = "KEYWORD")
    private List<Keyword> keywords;

    public String getText() {
        return text;
    }

    public void setText(String problemDescription) {
        this.text = problemDescription;
    }

    public Person getQuestioner() {
        return questioner;
    }

    public void setQuestioner(Person questioner) {
        this.questioner = questioner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String constructName() {
        return getTitle();
    }

    @Override
    public String toString() {
        return super.toString() + "Problem{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", questioners=" + questioner +
                '}';
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
