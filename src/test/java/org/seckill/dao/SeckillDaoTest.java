package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.seckill.BaseTest;
import org.seckill.dao.cache.RedisDao;
import org.seckill.entity.Seckill;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
public class SeckillDaoTest  {

	//注入Dao实现类依赖
	@Resource
	private SeckillDao seckillDao;

	@Resource
	private RedisDao redisDao;

	@Test
    public void get() throws Exception {
        Seckill seckill = redisDao.getSeckill(1002L);
        System.out.println("5769ujihjklojl-----"+seckill.getName());
    }

	@Test
	public void testQueryById() throws Exception {
		long id = 1003;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() throws Exception  {
		//Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
		// java没有保存形参的记录:queryAll(int offset, int limit) -> queryAll(arg1,arg2)
		List<Seckill> seckills = seckillDao.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			System.out.println(seckill);
		}
	}

	@Test
	public void testReduceNumber() throws Exception {
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println("updateCount=" + updateCount);
	}

}
