package com.bottle.team.model.interfaces;

import com.bottle.team.lucene.annotations.Indexed;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@Indexed
public interface BaseEntity {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Date getCreationDate();

    void setCreationDate(Date creationDate);

    Date getLastModified();

    void setLastModified(Date lastModified);
}
