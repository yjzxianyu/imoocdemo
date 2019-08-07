package com.sy.imoocdemo.service.impl;

import com.sy.imoocdemo.dto.Exposer;
import com.sy.imoocdemo.dto.SeckillExecution;
import com.sy.imoocdemo.entity.SecKill;
import com.sy.imoocdemo.exception.RepeatKillException;
import com.sy.imoocdemo.exception.SeckillCloseException;
import com.sy.imoocdemo.exception.SeckillException;
import com.sy.imoocdemo.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceimplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() {
        List<SecKill> list = seckillService.getSeckillList();
        logger.info("list={}",list );
    }

    @Test
    public void getById() {
        long id=1000;
        SecKill secKill = seckillService.getById(id);
        logger.info("seckill={}",secKill);
    }

    @Test
    public void seckillLogic() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);

            long phone = 13219663323l;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",execution);
            }
            catch (RepeatKillException e){
                logger.error(e.getMessage());
            }
            catch (SeckillCloseException e1){
                logger.error(e1.getMessage());
            }
        }else {
            logger.warn("exposer={}",exposer);
        }

    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 13219663323l;
        String md5 = "5f7b1a8532b5ddaedd0d0a9abf9ae307";
        try {
            SeckillExecution execution = seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",execution);
        }
        catch (RepeatKillException e){
            logger.error(e.getMessage());
        }
        catch (SeckillCloseException e1){
            logger.error(e1.getMessage());
        }
    }
}