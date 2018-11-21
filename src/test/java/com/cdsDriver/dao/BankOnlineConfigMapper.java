package com.cdsDriver.dao;


import com.cdsDriver.model.BankOnlineConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * @author wangxk
 * @version 1.1
 * @since 1.1
 */

public interface BankOnlineConfigMapper {
    public void insert(BankOnlineConfigEntity v);

    public void update(BankOnlineConfigEntity v);

    public List<BankOnlineConfigEntity> findByPage(Map<String, Object> map);

    public int getCount(Map<String, Object> map);

    public List<BankOnlineConfigEntity> findByBankId(Map<String, Object> map);

    public BankOnlineConfigEntity getById(String id);
    
    /**
     * 批量插入
     * @param list
     */
    public int insertBatch(List<BankOnlineConfigEntity> list);
    
    public void insertTest(BankOnlineConfigEntity v);

    /**
     * 按修改时间批量更新数据为失效
     * @param updateEntity
     */
	public void updateByModifyTime(BankOnlineConfigEntity updateEntity);
}
