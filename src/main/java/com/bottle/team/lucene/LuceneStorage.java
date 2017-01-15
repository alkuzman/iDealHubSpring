package com.bottle.team.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 11/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 11/01/2017
 */
@Component
public class LuceneStorage {
    private final Directory directory;
    private final Analyzer luceneAnalyzer;

    private IndexWriter writer;

    @Autowired
    public LuceneStorage(Analyzer luceneAnalyzer, Directory directory) {
        this.luceneAnalyzer = luceneAnalyzer;
        this.directory = directory;
    }

    public void store(Object object) {
        List<Document> documents = LuceneUtils.getDocuments(object, false);
        saveDocuments(documents);
    }

    public void remove(Object object) {
        System.out.println("Remove object");
    }

    public void firstLevelStore(Object object) {
        List<Document> documents = LuceneUtils.getDocuments(object, true);
        saveDocuments(documents);
    }

    public void createIndex(Iterable<? extends Object> objects) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneAnalyzer);
        try {
            IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
            for (Object object : objects) {
                List<Document> documents = LuceneUtils.getDocuments(object, true);
                for (Document document : documents) {
                    writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDocuments(List<Document> documents) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneAnalyzer);
        System.out.println(documents.size());
        try {
            IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
            for (Document document : documents) {
                //writer.addDocument(document);
                writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
            }
            System.out.println("Flush");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
