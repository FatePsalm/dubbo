package com.solace.transactioncommon.service;

public interface QueueProduceService {
    //生产并发送
    void produceMessage();

    // 带定时投递的业务方法
    void produceMessageScheduled();
}
