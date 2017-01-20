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
public class ProblemFilter implements Filter {
    private Long id;
    private Long questionerId;

    public ProblemFilter(Long id, Long questionerId) {
        this.id = id;
        this.questionerId = questionerId;
    }

    public Query getQuery() {
        boolean isValid = false;
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        if (id != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("id", id), BooleanClause.Occur.MUST);
        }
        if (questionerId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("questioner.id", questionerId), BooleanClause.Occur.MUST);
        }

        if (isValid)
            return builder.build();
        return null;
    }
}
