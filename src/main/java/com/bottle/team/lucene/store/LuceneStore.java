package com.bottle.team.lucene.store;

import org.apache.lucene.document.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 * <p>
 * <p>
 * This interface provides API for CRUD operations over lucene index.
 * It operates with Lucene {@link Document}'s. Its only purpose is Storage.
 * </p>
 *
 * @author AKuzmanoski
 * @version 1.0
 * @apiNote there is method for {@link #clear()} which cleans the index. After this call the index is empty.
 * @since 06/09/2017
 */
public interface LuceneStore {
    /**
     * By calling this method, the {@link Document} provided as argument is saved into the lucene index.
     *
     * @param document that will be added into the lucene index
     * @throws IOException if there is a problem with writing the data into lucene index
     */
    void save(Document document) throws IOException;

    /**
     * By calling this method, the {@link Document}'s provided as argument are saved into the lucene index.
     *
     * @param documents that will be added into the lucene index
     * @throws IOException if there is a problem with writing the data into lucene index
     */
    void save(List<Document> documents) throws IOException;

    /**
     * By calling this method, the {@link Document} provided as argument is updated into the lucene index.
     *
     * @param document that will be updated into the lucene index
     * @throws IOException if there is a problem with writing the data into lucene index
     */
    void update(Document document) throws IOException;

    /**
     * By calling this method, the {@link Document}'s provided as argument are updated into the lucene index.
     *
     * @param documents that will be updated into the lucene index
     * @throws IOException if there is a problem with writing the data into lucene index
     */
    void update(List<Document> documents) throws IOException;

    /**
     * By calling this method, the {@link Document} with id provided as argument is removed from the lucene index.
     *
     * @param id of the {@link Document} that will be removed from the lucene index
     * @throws IOException if there is a problem with removing the data from lucene index
     */
    void remove(Long id) throws IOException;

    /**
     * By calling this method, the {@link Document}'s with ids provided as argument are removed from the lucene index.
     *
     * @param ids of the {@link Document}'s that will be removing from the lucene index
     * @throws IOException if there is a problem with removing the data from lucene index
     */
    void remove(List<Long> ids) throws IOException;

    /**
     * This method clears the lucene index. So after calling this method the index will be empty
     *
     * @throws IOException if there is a problem with clearing the index.
     */
    void clear() throws IOException;
}
