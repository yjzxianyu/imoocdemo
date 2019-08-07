package com.sy.imoocdemo.dao;

import com.sy.imoocdemo.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀列表
     * @param seckillId
     * @return
     */
    SecKill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀库存
     * @param offet
     * @param limit
     * @return
     */
    List<SecKill> queryAll(@Param("offset") int offet,@Param("limit") int limit);
}
