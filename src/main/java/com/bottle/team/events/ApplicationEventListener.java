package com.bottle.team.events;

import com.bottle.team.lucene.LuceneStorage;
import com.bottle.team.neo4j.Neo4jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    final
    private LuceneStorage luceneStorage;

    @Autowired
    public ApplicationEventListener(LuceneStorage luceneStorage) {
        this.luceneStorage = luceneStorage;
    }

    @EventListener
    public void onPreSave(PreSaveEvent event) {
        Object o = event.getSource().getObject();
        Neo4jUtils.updateObject(o);
    }

    @EventListener
    public void onPostSave(PostSaveEvent event) {
        this.luceneStorage.store(event.getSource().getObject());
    }

    @EventListener
    public void onPreDelete(PreDeleteEvent event) {
        this.luceneStorage.remove(event.getSource().getObject());
    }
}
