package com.cdsDriver.service;

import com.cdsDriver.model.BankOnlineConfigEntity;

import java.util.List;

/**
 * @Author: qushiwen
 * @Date: 18-11-20
 * @version: 1.0
 */
public interface BankOnlineServiceV2 {
    void insertBatch(List<BankOnlineConfigEntity> bankOnlineConfigEntities);

}
