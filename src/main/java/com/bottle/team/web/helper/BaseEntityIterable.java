package com.bottle.team.web.helper;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.interfaces.BaseEntity;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by AKuzmanoski on 07/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 07/03/2017
 */
public class BaseEntityIterable<T extends BaseEntity> implements Iterable<T> {
    private Iterable<T> iterable;

    public BaseEntityIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        iterable.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return iterable.spliterator();
    }
}
