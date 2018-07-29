package com.bottle.team.model.ideas.quality;

import com.bottle.team.model.ideas.Keyword;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public class ProblemCoverage extends AbstractTextQuality {
    @Property(name = "coverage")
    private Double coverage;
    @Relationship(type = "COVERED_KEYWORD")
    private List<Keyword> coveredKeywords;
    @Relationship(type = "NOT_COVERED_KEYWORD")
    private List<Keyword> notCoveredKeywords;

    public Double getCoverage() {
        return coverage;
    }

    public void setCoverage(Double coverage) {
        this.coverage = coverage;
    }

    public List<Keyword> getCoveredKeywords() {
        return coveredKeywords;
    }

    public void setCoveredKeywords(List<Keyword> coveredKeywords) {
        this.coveredKeywords = coveredKeywords;
    }

    public List<Keyword> getNotCoveredKeywords() {
        return notCoveredKeywords;
    }

    public void setNotCoveredKeywords(List<Keyword> notCoveredKeywords) {
        this.notCoveredKeywords = notCoveredKeywords;
    }
}
