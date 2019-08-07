package com.sy.imoocdemo.service.impl;

import com.sy.imoocdemo.dao.SeckillDao;
import com.sy.imoocdemo.dao.SuccessKilledDao;
import com.sy.imoocdemo.dto.Exposer;
import com.sy.imoocdemo.dto.SeckillExecution;
import com.sy.imoocdemo.entity.SecKill;
import com.sy.imoocdemo.entity.SuccessKilled;
import com.sy.imoocdemo.enums.SeckillStaEnum;
import com.sy.imoocdemo.exception.RepeatKillException;
import com.sy.imoocdemo.exception.SeckillCloseException;
import com.sy.imoocdemo.exception.SeckillException;
import com.sy.imoocdemo.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service
public class SeckillServiceimpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    private final String slat="asdassadeqqwewq";

    @Override
    public List<SecKill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public SecKill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckUrl(long seckillId) {
        String md5 = getMd5(seckillId);
        SecKill secKill = seckillDao.queryById(seckillId);
        if(secKill == null){
            return new Exposer(false,seckillId);
        }
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }

        return new Exposer(true,md5,seckillId);
    }

    private String getMd5(long seckillId){
        String base= seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("没有开启");
        }
        Date nowTime = new Date();
        try {


            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SeckillCloseException("close");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("chongfu");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStaEnum.SUCCESS, successKilled);
                }
            }
        }
        catch (SeckillCloseException e1){
            throw e1;
        }
        catch (RepeatKillException e2){
            throw e2;
        }
    catch (Exception e){
            logger.error(e.getMessage());
            throw new SeckillException("error"+e.getMessage());
        }

}
}
