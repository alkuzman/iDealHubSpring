package com.bottle.team.lucene;

import com.bottle.team.neo4j.Neo4jUtils;
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
        Document document = LuceneUtils.getDocument(object);
        saveDocument(document);
    }

    public void remove(Object object) {
        // TODO implement remove operation
    }

    public void firstLevelStore(Object object) {
        List<Document> documents = LuceneUtils.getDocuments(object, true);
        saveDocuments(documents);
    }

    public void createIndex(Iterable<? extends Object> objects) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneAnalyzer);
        try {
            IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
            writer.deleteAll();
            // @TODO Viki writer
            for (Object object : objects) {
                Document document = LuceneUtils.getDocument(object);
                System.out.println(document.toString());
                writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDocuments(List<Document> documents) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneAnalyzer);
        try {
            IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
            for (Document document : documents) {
                //writer.addDocument(document);
                writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDocument(Document document) {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneAnalyzer);
        try {
            IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
                //writer.addDocument(document);
                writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
