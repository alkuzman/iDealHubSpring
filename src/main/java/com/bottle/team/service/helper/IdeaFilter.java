package com.bottle.team.service.helper;

import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
public class IdeaFilter {
    private Long id;
    private Long ownerId;
    private Long problemId;

    public IdeaFilter(Long id, Long ownerId, Long problemId) {
        this.id = id;
        this.ownerId = ownerId;
        this.problemId = problemId;
    }

    public Query getQuery() {
        boolean isValid = false;
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        if (id != null) {
            builder.add(LongPoint.newExactQuery("id", id), BooleanClause.Occur.MUST);
            isValid = true;
        }
        if (ownerId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("owner.id", ownerId), BooleanClause.Occur.MUST);
        }
        if (problemId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("problem.id", problemId), BooleanClause.Occur.MUST);
        }

        if (isValid)
            return builder.build();
        return null;
    }
}
