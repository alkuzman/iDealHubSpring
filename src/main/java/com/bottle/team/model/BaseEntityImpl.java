package com.bottle.team.model;

import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.SortableField;
import com.bottle.team.lucene.enumerations.Analyze;
import com.bottle.team.model.interfaces.BaseEntity;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.awt.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 09/10/2016.
 * @author AKuzmanoski
 * @since 09/10/2016
 * @version 1.0
 */
@NodeEntity
public abstract class BaseEntityImpl implements BaseEntity, Cloneable {
    @GraphId
    @Field(analyze = Analyze.NO)
    @Id
    private Long id;
    @CreatedDate
    @Property(name = "creationDate")
    @Field(analyze = Analyze.NO)
    @SortableField
    private Date creationDate;
    @LastModifiedDate
    @Property(name = "lastModified")
    @Field(analyze = Analyze.NO)
    @SortableField
    private Date lastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "BaseEntityImpl{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", lastModified=" + lastModified +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BaseEntityImpl baseEntity = (BaseEntityImpl)super.clone();
        baseEntity.setCreationDate(getCreationDate());
        baseEntity.setLastModified(getLastModified());
        baseEntity.setId(getId());
        return baseEntity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof BaseEntityImpl)) return false;

        BaseEntityImpl that = (BaseEntityImpl) object;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
