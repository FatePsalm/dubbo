package com.solace.transactioncommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.solace.transactioncommon.entity.TransactionMessage;
import com.solace.transactioncommon.exceptions.MessageBizException;

public interface TransactionMessageService extends IService<TransactionMessage> {

    /**
     * 预存储消息.
     */
     int saveMessageWaitingConfirm(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 确认并发送消息.
     */
     void confirmAndSendMessage(String messageId) throws MessageBizException;


    /**
     * 存储并发送消息.
     */
     int saveAndSendMessage(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 直接发送消息.
     */
     void directSendMessage(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 重发消息.
     */
     void reSendMessage(TransactionMessage TransactionMessage) throws MessageBizException;


    /**
     * 根据messageId重发某条消息.
     */
     void reSendMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 将消息标记为死亡消息.
     */
     void setMessageToAreadlyDead(String messageId) throws MessageBizException;


    /**
     * 根据消息ID获取消息
     */
     TransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * 根据消息ID删除消息
     */
     void deleteMessageByMessageId(String messageId) throws MessageBizException;
}
