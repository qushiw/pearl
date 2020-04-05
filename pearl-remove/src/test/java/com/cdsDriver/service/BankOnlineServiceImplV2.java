package com.cdsDriver.service;

import com.cdsDriver.dao.BankOnlineConfigMapper;
import com.cdsDriver.model.BankOnlineConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: qushiwen
 * @Date: 18-11-20
 * @version: 1.0
 */
@Transactional
public class BankOnlineServiceImplV2 implements BankOnlineServiceV2{

    @Autowired
    private BankOnlineConfigMapper bankOnlineConfigMapper;

    @Override
    public void insertBatch(List<BankOnlineConfigEntity> bankOnlineConfigEntities) {
        bankOnlineConfigMapper.insertBatch(bankOnlineConfigEntities);

    }
}
