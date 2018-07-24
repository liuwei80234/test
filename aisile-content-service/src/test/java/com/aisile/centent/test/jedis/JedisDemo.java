package com.aisile.centent.test.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDemo {
	 @Test 
     public void jedisRest() {
    	 Jedis jedis= new Jedis("192.168.52.130",6379);
    	 jedis.set("nameTest", "valueTest");
    	System.out.println(jedis.get("name100"));
    	jedis.close();
     }
     
 @Test    
     public void jedisTestByPool() {
    	JedisPool jedisPool =new JedisPool("192.168.52.130",6379);
    	Jedis jedis = jedisPool.getResource();
    	 jedis.set("nameTest1", "valueTest1");
    	 System.out.println(jedis.get("nameTest1"));
    	 jedis.close();
    	 jedisPool.close();
     }
}
