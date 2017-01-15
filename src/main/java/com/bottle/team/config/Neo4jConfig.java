package com.bottle.team.config;

import com.bottle.team.lucene.LuceneStorage;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.interfaces.BaseEntity;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.event.AfterDeleteEvent;
import org.springframework.data.neo4j.event.AfterSaveEvent;
import org.springframework.data.neo4j.event.BeforeSaveEvent;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.UUID;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.bottle.team.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {
    final
    private LuceneStorage luceneStorage;

    @Autowired
    public Neo4jConfig(LuceneStorage luceneStorage) {
        this.luceneStorage = luceneStorage;
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("com.bottle.team.model");
    }

    // needed for session in view in web-applications
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }

    @Bean
    public ApplicationListener<BeforeSaveEvent> beforeSaveEventApplicationListener() {
        return beforeSaveEvent -> {
            BaseEntityImpl baseEntityImpl = (BaseEntityImpl) beforeSaveEvent.getEntity();
            //baseEntityImpl.setId(UUID.randomUUID().getMostSignificantBits());
            if (baseEntityImpl.getId() == null) {
                baseEntityImpl.setCreationDate(new Date());
            }
            baseEntityImpl.setLastModified(new Date());
        };
    }

    @Bean
    ApplicationListener<AfterSaveEvent> afterSaveEventApplicationListener() {

        return event -> {
            System.out.println(event.getEntity());
            luceneStorage.store(event.getEntity());
        };
    }

    @Bean
    ApplicationListener<AfterDeleteEvent> afterDeleteEventApplicationListener() {
        return event -> luceneStorage.remove(event.getEntity());
    }
}
