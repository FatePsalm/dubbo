package com.solace.mqtransactionservice.service.impl;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
 /**
   * 作者 CG
   * 时间 2019/8/23 15:56
   * 注释 MQ监听器
   */
@Component
public class QueueConsummer {

    @JmsListener(destination = "${myqueue}")     // 注解监听
    public void receive(TextMessage textMessage) throws  Exception{
        System.out.println(" ***  消费者收到消息  ***"+textMessage.getText());
}

}
