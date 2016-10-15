package com.bottle.team.model;

import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.interfaces.Questioner;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by Viki on 10/14/2016.
 */
public class Problem extends BaseEntity implements NamedEntity {
    @Property(name = "title")
    String title;

    @Property(name = "problem")
    String problem;

    @Relationship(type = "QUESTIONER")
    List<Questioner> questioners;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public List<Questioner> getQuestioners() {
        return questioners;
    }

    public void setQuestioners(List<Questioner> questioners) {
        this.questioners = questioners;
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
}
