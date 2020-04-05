package com.cdsDriver;

import com.cdsDriver.model.BankOnlineConfigEntity;
import com.cdsDriver.service.BankOnlineServiceV2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: qushiwen
 * @Date: 18-11-20
 * @version: 1.0
 */

@ContextConfiguration(locations = { "classpath:spring/applicationContext_db.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class BatchInsertTest{

    @Autowired
    private BankOnlineServiceV2 bankOnlineService;

    @Test
    public void testBankInsert() {
        List<BankOnlineConfigEntity> bankOnlineConfigEntityList = new ArrayList<BankOnlineConfigEntity>();
        BankOnlineConfigEntity bankOnlineConfigEntity = null;

        for (int i=10000; i<10010; i++) {
            bankOnlineConfigEntity = new BankOnlineConfigEntity();
//			bankOnlineConfigEntity.setId(i);
            bankOnlineConfigEntity.setBankId(320);
            bankOnlineConfigEntity.setOnlineTime(new Date());
            bankOnlineConfigEntity.setOfflineTime(new Date());
            bankOnlineConfigEntity.setStatus(1);
            bankOnlineConfigEntity.setDelFlag(0);
            bankOnlineConfigEntity.setCreateTime(new Date());
            bankOnlineConfigEntity.setModifeTime(new Date());
            bankOnlineConfigEntityList.add(bankOnlineConfigEntity);
        }
        bankOnlineService.insertBatch(bankOnlineConfigEntityList);
    }




}
