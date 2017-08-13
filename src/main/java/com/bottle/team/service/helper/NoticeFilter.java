package com.bottle.team.service.helper;

import com.bottle.team.model.sharing.Notice;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class NoticeFilter implements Filter {

    private String recipientEmail;

    public NoticeFilter(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    @Override
    public Query getQuery() {
        boolean isValid = false;
        if (this.recipientEmail != null) {
            return new TermQuery(new Term("recipient.email", recipientEmail));
        }
        return null;
    }
}
