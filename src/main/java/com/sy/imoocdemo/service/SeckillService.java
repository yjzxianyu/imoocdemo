package com.sy.imoocdemo.service;

import com.sy.imoocdemo.dto.Exposer;
import com.sy.imoocdemo.dto.SeckillExecution;
import com.sy.imoocdemo.entity.SecKill;
import com.sy.imoocdemo.exception.RepeatKillException;
import com.sy.imoocdemo.exception.SeckillCloseException;
import com.sy.imoocdemo.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    List<SecKill> getSeckillList();
    SecKill getById(long seckillId);
    Exposer exportSeckUrl(long seckillId);
    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)throws SeckillCloseException, RepeatKillException, SeckillException;
}
