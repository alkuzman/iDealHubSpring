package com.bottle.team.model;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.SortableField;
import com.bottle.team.lucene.enumerations.Analyze;
import com.bottle.team.model.interfaces.BaseEntity;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * Created by AKuzmanoski on 09/10/2016.
 * @author AKuzmanoski
 * @since 09/10/2016
 * @version 1.0
 */
@NodeEntity
public abstract class BaseEntityImpl implements BaseEntity, Cloneable {
    @Id
    @GraphId
    @Field(analyze = Analyze.NO)
    private Long id;
    @Property(name = "name")
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", lastModified=" + lastModified +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BaseEntityImpl baseEntity = (BaseEntityImpl)super.clone();
        baseEntity.setName(getName());
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
