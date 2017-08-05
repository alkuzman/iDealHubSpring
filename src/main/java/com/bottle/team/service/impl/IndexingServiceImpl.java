package com.bottle.team.service.impl;

import com.bottle.team.lucene.LuceneStorage;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.BaseEntityRepository;
import com.bottle.team.service.IndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
@Service
public class IndexingServiceImpl implements IndexingService {
    final
    private BaseEntityRepository baseEntityRepository;
    final
    private LuceneStorage luceneStorage;


    @Autowired
    public IndexingServiceImpl(BaseEntityRepository baseEntityRepository, LuceneStorage luceneStorage) {
        this.baseEntityRepository = baseEntityRepository;
        this.luceneStorage = luceneStorage;
    }

    @Override
    public void createIndex() {
        Iterable<BaseEntity> entities = baseEntityRepository.findAll();
        luceneStorage.createIndex(entities);
        System.out.println("Creating Index Done");
    }
}
