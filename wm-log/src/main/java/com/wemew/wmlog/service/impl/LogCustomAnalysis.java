package com.wemew.wmlog.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wemew.wmlog.service.LogCustomAnalysisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Service(interfaceClass = LogCustomAnalysisService.class,version = "1.0.0",timeout = 3000)
@Component
public class LogCustomAnalysis implements LogCustomAnalysisService {

    private static final Logger logger = LogManager.getLogger(LogCustomAnalysis.class.getSimpleName());
    //category 1-1 微上墙人数 1-2 手机型号 3-1 点歌流水
    //11-1 艺人上台统计
    //12-1 点歌发送模板消息失败统计
    //13-1 大屏JS错误统计 13-2 喵STAR全局JS错误 13-3 喵STAR接口返回错误 13-4 手机JS错误统计
    //14-1 支付埋点
    //16-1 订座下单 16-2 酒吧全部座位 16-3 去过的酒吧列表 16-4 推荐酒吧
    @Override
    public  void writeLog(Integer category,Integer type,String userid,String username,String moreinfo,Integer price,
                                String barid,String barname){
        logger.info(category+" "+type+" "+userid+" "+username+" "+moreinfo+" "+barid+" "+barname+" "+price);
    }
}
