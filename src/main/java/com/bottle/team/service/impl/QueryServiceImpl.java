package com.bottle.team.service.impl;

import com.bottle.team.lucene.LuceneUtils;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.repository.BaseEntityRepository;
import com.bottle.team.service.QueryService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * Created by AKuzmanoski on 13/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/01/2017
 */
@Service
public class QueryServiceImpl implements QueryService {
    private final
    Analyzer analyzer;
    private final
    Directory index;
    private final
    BaseEntityRepository baseEntityRepository;
    private String[] fields;

    @Autowired
    public QueryServiceImpl(Directory index, Analyzer analyzer, BaseEntityRepository baseEntityRepository) {
        this.index = index;
        this.analyzer = analyzer;
        this.baseEntityRepository = baseEntityRepository;
    }

    @PostConstruct
    public void init() {
        fields = new String[17];
        fields[0] = "name";
        fields[1] = "firstName";
        fields[2] = "lastName";
        fields[3] = "email";
        fields[4] = "text";
        fields[5] = "title";
        fields[6] = "snackPeak";
        fields[7] = "owner.name";
        fields[8] = "owner.firstName";
        fields[9] = "owner.lastName";
        fields[10] = "owner.email";
        fields[11] = "questioner.name";
        fields[12] = "questioner.firstName";
        fields[13] = "questioner.lastName";
        fields[14] = "questioner.email";
        fields[15] = "problem.title";
        fields[16] = "problem.text";
    }

    @Override
    public Iterable<? extends BaseEntity> search(String query, Integer offset, Integer limit) {
        return search(query, null, offset, limit, BaseEntity.class, null);
    }

    private String[] getFields(String... fields) {
        if (fields == null)
            return this.fields;
        return fields;
    }

    public <T extends BaseEntity> Iterable<? extends BaseEntity> search(String queryString, Query query, Integer offset, Integer limit, Class<T> type, String... fields) {
        fields = getFields(fields);
        if (queryString == null || queryString.trim().equals(""))
            return search(query, offset, limit, type);
        BooleanQuery.Builder fieldBuilder = new BooleanQuery.Builder();
        for (String field : fields) {
            BooleanQuery.Builder tokenBuilder = new BooleanQuery.Builder();
            PhraseQuery.Builder phrase = new PhraseQuery.Builder().setSlop(2);
            Iterable<String> tokens = LuceneUtils.getTokens(analyzer, field, queryString);
            int position = 0;
            String lastToken = "";
            for (String token : tokens) {
                tokenBuilder.add(new FuzzyQuery(new Term(field, token)), BooleanClause.Occur.SHOULD);
                phrase.add(new Term(field, token));
                position++;
                lastToken = token;
            }
            phrase.add(new Term(field, lastToken + "*"), position - 1);
            tokenBuilder.add(phrase.build(), BooleanClause.Occur.SHOULD);

            fieldBuilder.add(tokenBuilder.build(), BooleanClause.Occur.SHOULD);
        }
        if (query != null) {
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            builder.add(query, BooleanClause.Occur.MUST);
            builder.add(fieldBuilder.build(), BooleanClause.Occur.MUST);
            return search(builder.build(), offset, limit, type);
        } else {
            return search(fieldBuilder.build(), offset, limit, type);
        }
    }


    public <T extends BaseEntity> Iterable<? extends BaseEntity> search(Query query, Integer offset, Integer limit, Class<T> type) {
        Query typeQuery = null;
        try {
            typeQuery = new QueryParser("{{type}}", analyzer).parse(type.getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (query != null) {
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            builder.add(query, BooleanClause.Occur.MUST);
            builder.add(typeQuery, BooleanClause.Occur.MUST);
            query = builder.build();
        } else {
            query = typeQuery;
        }

        System.out.println(query);
        Map<Long, Float> scoreMap = new HashMap<>();
        List<Long> ids = new LinkedList<>();
        try {
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            int lmt;
            if (limit == null)
                lmt = Integer.MAX_VALUE;
            else lmt = limit;
            if (offset != null)
                lmt += offset;
            TopDocs docs = searcher.search(query, lmt);
            ScoreDoc[] hits = docs.scoreDocs;

            for (int i = offset != null ? offset : 0; i < hits.length; ++i) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                Long id = Long.parseLong(d.get("{{id}}"));
                scoreMap.put(id, hits[i].score);
                ids.add(id);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BaseEntity> entities = new ArrayList<>(scoreMap.size());
        for (BaseEntity baseEntity : baseEntityRepository.findAll(ids))
            if (baseEntity == null) {
                System.out.println("Null");
            } else
            entities.add(baseEntity);
        entities.sort((o1, o2) -> Float.compare(scoreMap.get(o2.getId()), scoreMap.get(o1.getId())));
        return entities;
    }
}
