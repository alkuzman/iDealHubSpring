package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Person;
import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.sharing.Sharable;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;

/**
 * Created by Viki on 10/14/2016.
 */
public class Problem extends BaseEntityImpl implements NamedEntity, Sharable {
    @NotEmpty
    @Property(name = "title")
    private String title;

    @Property(name = "text")
    @NotEmpty
    private String text;

    @Relationship(type = "QUESTIONER")
    @NotNull
    private Person questioner;

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
}
