package com.mysql;

import javax.transaction.xa.Xid;

/**
*
* @author qushiwen
* @create 2017-11-27
* @version 1.0
**/
public class MyXid implements Xid {

    public int formatId;

    public byte gtrid[];

    public byte bqual[];


    public MyXid(){

    }

    public MyXid(int formatId, byte[] gtrid, byte[] bqual) {
        this.formatId = formatId;
        this.gtrid = gtrid;
        this.bqual = bqual;
    }




    @Override
    public int getFormatId() {
        return formatId;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return gtrid;
    }

    @Override
    public byte[] getBranchQualifier() {
        return bqual;
    }
}
