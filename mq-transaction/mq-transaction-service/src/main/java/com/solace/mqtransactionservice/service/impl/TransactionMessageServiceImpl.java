package com.solace.mqtransactionservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.solace.mqtransactionservice.mapper.TransactionMessageMapper;
import com.solace.transactioncommon.entity.TransactionMessage;
import com.solace.transactioncommon.enums.MessageStatusEnum;
import com.solace.transactioncommon.enums.PublicEnum;
import com.solace.transactioncommon.exceptions.MessageBizException;
import com.solace.transactioncommon.service.TransactionMessageService;
import com.solace.transactioncommon.utils.PublicConfigUtil;
import com.solace.transactioncommon.utils.StringUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

@Component
@Service
public class TransactionMessageServiceImpl extends ServiceImpl<TransactionMessageMapper, TransactionMessage>  implements TransactionMessageService {
    @Autowired
    private JmsTemplate notifyJmsTemplate;

    public int saveMessageWaitingConfirm(TransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.setEditTime(new Date());
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
        message.setAreadlyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        return this.baseMapper.insert(message);
    }


    public void confirmAndSendMessage(String messageId) {
        final TransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }
        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setEditTime(new Date());
        this.baseMapper.updateById(message);

        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message.getMessageBody());
            }
        });
    }


    public int saveAndSendMessage(final TransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setAreadlyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        message.setEditTime(new Date());
        int result = this.baseMapper.insert(message);

        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message.getMessageBody());
            }
        });

        return result;
    }

    public void directSendMessage(final TransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }
        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }
        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message.getMessageBody());
            }
        });
    }
    public void reSendMessage(final TransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }
        message.addSendTimes();
        message.setEditTime(new Date());
        this.baseMapper.updateById(message);

        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message.getMessageBody());
            }
        });
    }
    public void reSendMessageByMessageId(String messageId) {
        final TransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        int maxTimes = Integer.valueOf(PublicConfigUtil.readConfig("message.max.send.times"));
        if (message.getMessageSendTimes() >= maxTimes) {
            message.setAreadlyDead(PublicEnum.YES.name());
        }

        message.setEditTime(new Date());
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        this.baseMapper.updateById(message);

        notifyJmsTemplate.setDefaultDestinationName(message.getConsumerQueue());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message.getMessageBody());
            }
        });
    }

    public void setMessageToAreadlyDead(String messageId) {
        TransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }
        message.setAreadlyDead(PublicEnum.YES.name());
        message.setEditTime(new Date());
        this.baseMapper.updateById(message);
    }

    public TransactionMessage getMessageByMessageId(String messageId) {
        QueryWrapper<TransactionMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TransactionMessage::getMessageId,messageId);
        TransactionMessage one = getOne(queryWrapper);
        return one;
    }

    public void deleteMessageByMessageId(String messageId) {
        this.baseMapper.deleteById(messageId);
    }
}
