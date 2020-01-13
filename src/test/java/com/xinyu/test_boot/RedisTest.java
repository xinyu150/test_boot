package com.xinyu.test_boot;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	
	@Autowired
    private RedisTemplate<String,Object> redisTemplate;
	
	@Test
	public void test() {
		redisTemplate.opsForValue().set("key","value", 60, TimeUnit.SECONDS);
	}
}
