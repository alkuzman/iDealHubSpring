package com.bottle.team.lucene.store;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by AKuzmanoski on 06/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/09/2017
 */
@Component
public class LuceneStoreImpl implements LuceneStore {
    private final Directory directory;
    private final Analyzer luceneAnalyzer;
    private final IndexWriterConfig indexWriterConfig;

    @Autowired
    public LuceneStoreImpl(Analyzer luceneAnalyzer, Directory directory) {
        this.luceneAnalyzer = luceneAnalyzer;
        this.directory = directory;
        this.indexWriterConfig = new IndexWriterConfig(luceneAnalyzer);
    }

    @Override
    public void save(Document document) throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        writer.addDocument(document);
        writer.flush();
        writer.close();
    }

    @Override
    public void save(List<Document> documents) throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        for (Document document : documents) {
            writer.addDocument(document);
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void update(Document document) throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
        writer.flush();
        writer.close();
    }

    @Override
    public void update(List<Document> documents) throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        for (Document document : documents) {
            writer.updateDocument(new Term("{{id}}", document.get("{{id}}")), document);
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void remove(Long id) throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        writer.deleteDocuments(new Term("{{id}}", id.toString()));
        writer.flush();
        writer.close();
    }

    @Override
    public void remove(List<Long> ids) throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        for (Long id : ids) {
            writer.deleteDocuments(new Term("{{id}}", id.toString()));
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void clear() throws IOException {
        IndexWriter writer = new IndexWriter(directory, indexWriterConfig);
        writer.deleteAll();
        writer.flush();
        writer.close();
    }
}
