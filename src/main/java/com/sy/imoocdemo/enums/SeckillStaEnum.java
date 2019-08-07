package com.sy.imoocdemo.enums;

public enum  SeckillStaEnum {
    SUCCESS(1,"成功"),
    END(0,"结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;
    private String stateInfo;

    SeckillStaEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    public static SeckillStaEnum stateof(int index){
        for (SeckillStaEnum state:values()){
            if(state.getState()==index){
                return state;
            }
        }
return null;
    }
}
