package com.bottle.team.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by AKuzmanoski on 11/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 11/01/2017
 */
@Configuration
public class LuceneConfig {
    @Value("${app.lucene.default.directoryProvider}")
    private String directory_provider;

    @Value("${app.lucene.default.indexBase}")
    private String indexBase;

    @Bean
    public Directory getLuceneDirectory() {
        Directory directory;
        if ("filesystem".equals(this.directory_provider)) {
            try {
                directory = FSDirectory.open(Paths.get(this.indexBase));
            } catch (IOException e) {
                e.printStackTrace();
                directory = new RAMDirectory();
            }
        } else {
            directory = new RAMDirectory();
        }
        return directory;
    }

    @Bean
    public Analyzer getLuceneAnalyzer() {
        return new StandardAnalyzer();
    }
}
