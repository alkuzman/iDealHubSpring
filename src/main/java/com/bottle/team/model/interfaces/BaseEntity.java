package com.bottle.team.model.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

/**
 * Created by PC on 23/10/2016.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
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
