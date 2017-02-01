package com.bottle.team.neo4j;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
            List<Object> objects = getRelationships(object, object.getClass());
            for (Object o: objects)
                updateObject(o);
        } else if (object instanceof Collection) {
            List list = (List) object;
            for (Object o: list)
                updateObject(o);
        } else {
            updateObject(getEndNode(object));
        }
    }

    private static Object getEndNode(Object object) {
        System.out.println(object.getClass().getName());
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
        if (id == null) {
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
}
