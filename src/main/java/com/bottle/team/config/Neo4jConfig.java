package com.bottle.team.config;

import com.bottle.team.events.EventPublisher;
import com.bottle.team.lucene.LuceneStorage;
import com.bottle.team.neo4j.Neo4jUtils;
import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Priority;

@Configuration
@ComponentScan(basePackages = "com.bottle.team.events")
@EnableNeo4jRepositories(basePackages = "com.bottle.team.repository")
@EnableTransactionManagement
public class Neo4jConfig {

    final private EventPublisher eventPublisher;

    @Autowired
    public Neo4jConfig(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(configuration(), "com.bottle.team.model") {
            @Override
            public Session openSession() {
                Session session = super.openSession();
                session.register(eventPublisher);
                return session;
            }
        };
    }

    public org.neo4j.ogm.config.Configuration configuration() {
        ConfigurationSource properties = new ClasspathConfigurationSource("ogm.properties");
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
        return configuration;
    }
}
