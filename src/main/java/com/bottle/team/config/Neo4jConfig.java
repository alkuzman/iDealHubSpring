package com.bottle.team.config;

import com.bottle.team.model.BaseEntity;
import com.bottle.team.model.interfaces.NamedEntity;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.event.BeforeSaveEvent;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.bottle.team.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

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
            return new ApplicationListener<BeforeSaveEvent>() {
                @Override
                public void onApplicationEvent(BeforeSaveEvent beforeSaveEvent) {
                    NamedEntity namedEntity = (NamedEntity) beforeSaveEvent.getEntity();
                    BaseEntity baseEntity = (BaseEntity) beforeSaveEvent.getEntity();
                    if (baseEntity.getId() == null) {
                        baseEntity.setName(namedEntity.constructName());
                        baseEntity.setCreationDate(new Date());
                    }
                    baseEntity.setLastModified(new Date());
                }
            };
        }
}
