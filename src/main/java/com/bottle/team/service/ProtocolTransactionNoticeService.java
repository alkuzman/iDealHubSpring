package com.bottle.team.service;

import com.bottle.team.model.security.notices.ProtocolTransactionStepSevenNotice;

public interface ProtocolTransactionNoticeService extends NoticeService {

    ProtocolTransactionStepSevenNotice createProtocolTransactionSevenNotice(String message, String epoId, String user);
}
