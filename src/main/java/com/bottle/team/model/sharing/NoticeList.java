package com.bottle.team.model.sharing;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viki on 7/1/2017.
 */
public class NoticeList {

    @Valid
    private List<Notice> notices;

    public NoticeList() {
        this.notices = new ArrayList<>();
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }
}
