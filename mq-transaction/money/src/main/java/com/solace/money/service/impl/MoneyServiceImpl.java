package com.solace.money.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.solace.money.mapper.MoneyMapper;
import com.solace.transactioncommon.entity.Money;
import com.solace.transactioncommon.service.MoneyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CG
 * @since 2019-08-23
 */
@Service
public class MoneyServiceImpl extends ServiceImpl<MoneyMapper, Money> implements MoneyService {

}
