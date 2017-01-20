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
public class SolutionFilter implements Filter {
    private Long id;
    private Long ideaOwnerId;
    private Long problemId;
    private Long ideaId;
    private Long problemQuestionerId;

    public SolutionFilter(Long id, Long ideaOwnerId, Long problemQuestionerId, Long ideaId, Long problemId) {
        this.id = id;
        this.ideaOwnerId = ideaOwnerId;
        this.problemId = problemId;
        this.ideaId = ideaId;
        this.problemQuestionerId = problemQuestionerId;
    }

    public Query getQuery() {
        boolean isValid = false;
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        if (id != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("id", id), BooleanClause.Occur.MUST);
        }
        if (ideaOwnerId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("idea.owner.id", ideaOwnerId), BooleanClause.Occur.MUST);
        }
        if (problemId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("problem.id", problemId), BooleanClause.Occur.MUST);
        }
        if (ideaId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("idea.id", problemId), BooleanClause.Occur.MUST);
        }
        if (problemQuestionerId != null) {
            isValid = true;
            builder.add(LongPoint.newExactQuery("problem.questioner.id", problemId), BooleanClause.Occur.MUST);
        }

        if (isValid)
            return builder.build();
        return null;
    }
}
