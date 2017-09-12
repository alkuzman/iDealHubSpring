package com.bottle.team.lucene.reflection;

/**
 * Created by AKuzmanoski on 10/09/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 10/09/2017 15:16
 */
public abstract class ReflectionFacadeDecorator implements ReflectionFacade {
    private final ReflectionFacade decoratee;

    ReflectionFacadeDecorator(ReflectionFacade decoratee) {
        this.decoratee = decoratee;
    }

    public ReflectionFacade getDecoratee() {
        return decoratee;
    }
}
