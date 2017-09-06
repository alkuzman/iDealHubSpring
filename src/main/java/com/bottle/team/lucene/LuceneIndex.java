package com.bottle.team.lucene;

import java.io.IOException;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/09/2017
 */
public interface LuceneIndex {
    /**
     * Clears the old index, and creates new one
     */
    void createIndex() throws IOException;

    /**
     * Clears the old index, creates new one, and indexes the provided objects
     *
     * @param objects which will be indexed
     */
    void createIndex(List<Object> objects) throws IOException;

    /**
     * Clears the old index, creates new one, and indexes the provided object
     *
     * @param object which will be indexed
     */
    void createIndex(Object object) throws IOException;

    /**
     * Index the object
     *
     * @param object which will be indexed
     */
    void index(Object object) throws IOException;

    /**
     * Index the objects from the list
     *
     * @param objects is the list of objects which will be indexed.
     */
    void index(List<Object> objects) throws IOException;

    /**
     * Remove this object from the index
     *
     * @param object
     */
    void remove(Object object);

    /**
     * Remove the objects from the index
     *
     * @param objects list of objects which will be removed from the index
     */
    void remove(List<Object> objects);

    /**
     * Remove the object connected to the id from the index
     *
     * @param id of the object which will be removed
     */
    void removeById(Long id) throws IOException;

    /**
     * Remove the objects connected to the ids from the index
     *
     * @param ids of the objects which will be removed
     */
    void removeById(List<Long> ids) throws IOException;

    /**
     * Clear the index (remove all indexed objects)
     */
    void clear() throws IOException;
}
