package com.bottle.team.config;

import com.bottle.team.lucene.LuceneStorage;
import com.bottle.team.neo4j.Neo4jUtils;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.bottle.team.repository")
@EnableTransactionManagement
public class Neo4jConfig {
    final
    private LuceneStorage luceneStorage;

    @Autowired
    public Neo4jConfig(LuceneStorage luceneStorage) {
        this.luceneStorage = luceneStorage;
    }

    @Bean
    public SessionFactory sessionFactory() {
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
            Object o = beforeSaveEvent.getEntity();
            Neo4jUtils.updateObject(o);
        };
    }

    @Bean
    ApplicationListener<AfterSaveEvent> afterSaveEventApplicationListener() {

        return event -> {
            luceneStorage.store(event.getEntity());
        };
    }

    @Bean
    ApplicationListener<AfterDeleteEvent> afterDeleteEventApplicationListener() {
        return event -> luceneStorage.remove(event.getEntity());
    }
}
