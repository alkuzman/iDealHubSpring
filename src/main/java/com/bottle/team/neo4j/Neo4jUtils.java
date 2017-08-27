package com.bottle.team.neo4j;

import com.bottle.team.model.interfaces.BaseEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by AKuzmanoski on 26/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 26/01/2017
 */
public class Neo4jUtils {
    public static void updateObject(Object object) {
        if (object == null)
            return;
        if (isNodeEntity(object.getClass())) {
            setDates(object);
        } else if (object instanceof Collection) {
            List list = (List) object;
            for (Object o: list)
                updateObject(o);
        } else {
            updateObject(getEndNode(object));
        }
    }

    private static Object getEndNode(Object object) {
        Field field = getAnnotatedFiled(object.getClass(), EndNode.class);
        return getObjectFromField(object, field);
    }

    private static List<Object> getRelationships(Object object, Class type) {
        List<Object> list = new LinkedList<Object>();
        if (type == null)
            return list;
        for(Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Relationship.class)) {
                Object o = getObjectFromField(object, field);
                if (o != null)
                    list.add(o);
            }
        }
        list.addAll(getRelationships(object, type.getSuperclass()));
        return list;
    }

    private static Object getObjectFromField(Object object, Field field) {
        Object o = null;
        try {
            field.setAccessible(true);
            o = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    private static void setDates(Object object) {
        Long id = getId(object);
        Field lastModified = getAnnotatedFiled(object.getClass(), LastModifiedDate.class);
        try {
            lastModified.set(object, new Date());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field creationDate = getAnnotatedFiled(object.getClass(), CreatedDate.class);
        creationDate.setAccessible(true);
        Date date = null;
        try {
            date = (Date)creationDate.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (date == null) {
            try {
                creationDate.set(object, new Date());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Long getId(Object object) {
        Field idField = getAnnotatedFiled(object.getClass(), Id.class);
        Long id = null;
        try {
            idField.setAccessible(true);
            id = (Long)idField.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

    private static Field getAnnotatedFiled(Class type, Class annotation) {
        if (type == null)
            return null;
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(annotation))
                return field;
        }
        return getAnnotatedFiled(type.getSuperclass(), annotation);
    }

    private static boolean isNodeEntity(Class type) {
        if (type == null)
            return false;
        if (type.isAnnotationPresent(NodeEntity.class))
            return true;
        return isNodeEntity(type.getSuperclass());
    }

    public static <T extends BaseEntity> T findById(@NotNull Neo4jRepository<T, Long> repository, @NotNull Long id) {
        List<Long> ids = new LinkedList<>();
        ids.add(id);
        Iterable<T> entities = repository.findAllById(ids);
        for (T entity : entities) {
            if (id.equals(entity.getId()))
                return entity;
        }
        return null;
    }

    public static <T extends BaseEntity> Iterable<T> findAllById(@NotNull Neo4jRepository<T, Long> repository, @NotNull Iterable<Long> ids) {
        Set<Long> idSet = toSet(ids);
        Iterable<T> entities = repository.findAllById(ids);
        List<T> results = new LinkedList<>();
        for (T entity : entities) {
            if (idSet.contains(entity.getId()))
                results.add(entity);
        }
        return results;
    }

    private static Set<Long> toSet(Iterable<Long> ids) {
        Set<Long> idSet = new HashSet<>();
        for (Long id : ids)
            idSet.add(id);
        return idSet;
    }
}
