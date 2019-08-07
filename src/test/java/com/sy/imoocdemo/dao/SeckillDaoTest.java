package com.sy.imoocdemo.dao;

import com.sy.imoocdemo.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;



//注入Dao实现类依赖


public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception{
        long id = 1000;
        SecKill secKill = seckillDao.queryById(id);
        System.out.println(secKill.getName());
        System.out.println(secKill);
    }

    @Test
    public void queryAll() throws  Exception{
        List<SecKill> secKills = seckillDao.queryAll(0,100);
        for(SecKill secKill:secKills){
            System.out.println(secKill);
        }
    }

    @Test
    public void reduceNumber() throws Exception{
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount="+updateCount);
    }


}