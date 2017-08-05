package com.bottle.team.service.helper;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
public class AnnouncementFilter implements Filter {
    private Long id;
    private Long ownerId;
    private String type;
    private Long shareableId;

    public AnnouncementFilter(Long id, Long ownerId, Long shareableId, String type) {
        this.id = id;
        this.ownerId = ownerId;
        this.shareableId = shareableId;
        this.type = type;
    }

    public Query getQuery() {
        boolean isValid = false;
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        if (id != null) {
            builder.add(LongPoint.newExactQuery("{{id}}", id), BooleanClause.Occur.MUST);
            isValid = true;
        }
        if (ownerId != null) {
            BooleanQuery.Builder ownerBuilder = new BooleanQuery.Builder();
            ownerBuilder.add(LongPoint.newExactQuery("pckg.shareable.owner.id", ownerId), BooleanClause.Occur.SHOULD);
            ownerBuilder.add(LongPoint.newExactQuery("pckg.shareable.questioner.id", ownerId), BooleanClause.Occur.SHOULD);
            builder.add(ownerBuilder.build(), BooleanClause.Occur.MUST);
            isValid = true;
        }
        if (shareableId != null) {
            builder.add(LongPoint.newExactQuery("pckg.shareable.id", shareableId), BooleanClause.Occur.MUST);
            isValid = true;
        }
        if (type != null) {
            isValid = true;
            QueryParser queryParser = new QueryParser("pckg.shareable.{{type}}", new StandardAnalyzer());
            try {
                if (type.toLowerCase().equals("idea"))
                    builder.add(queryParser.parse(Idea.class.getName()), BooleanClause.Occur.MUST);
                if (type.toLowerCase().equals("problem"))
                    builder.add(queryParser.parse(Problem.class.getName()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (isValid)
            return builder.build();
        return null;
    }
}
