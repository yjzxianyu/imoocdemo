package com.sy.imoocdemo.dto;

import com.sy.imoocdemo.entity.SuccessKilled;
import com.sy.imoocdemo.enums.SeckillStaEnum;

public class SeckillExecution {
    private long seckillId;
    private int state;
    private  String stateInfo;
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStaEnum seckillStaEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStaEnum.getState();
        this.stateInfo = seckillStaEnum.getStateInfo();
        this.successKilled = successKilled;
    }


    public SeckillExecution(long seckillId, SeckillStaEnum seckillStaEnum) {
        this.seckillId = seckillId;
        this.state = seckillStaEnum.getState();
        this.stateInfo = seckillStaEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
