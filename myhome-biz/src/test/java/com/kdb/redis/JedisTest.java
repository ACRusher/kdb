package com.kdb.redis;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 下午7:28
 */
public class JedisTest {

    JedisPool pool;
    Jedis jedis;
    @Before
    public void setUp() {
        pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1",6379);
        jedis = pool.getResource();
        //   jedis.auth("password");
    }

    @Test
    public void testCommon(){
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.keys("map*"));
        System.out.println(jedis.ttl("a"));

        jedis.set("test-ttl", "a");
        jedis.setex("test-ttl", 10, "a");
        System.out.println(jedis.ttl("test-ttl"));

        jedis.setex("a", 10, "a-value");
        jedis.rename("a", "rename-a");
        System.out.println(jedis.ttl("rename-a"));

        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a","6");
        jedis.lpush("a","3");
        jedis.lpush("a","9");
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
        System.out.println(jedis.lrange("a",0,-1));
        
    }

    @Test
    public void testBasicString(){
        //保存一个字符串
        jedis.set("strKey", "value");
        System.out.println(jedis.get("strKey"));

        //在字符串后面append
        jedis.append("strKey", "-value1");
        System.out.println(jedis.get("strKey"));

        //覆盖数据
        jedis.set("strKey", "newvalue");
        System.out.println(jedis.get("strKey"));

        //删除数据
        jedis.del("strKey");
        System.out.println(jedis.get("strKey"));

        //测试多key操作
        jedis.mset("key-a", "value-a", "key-b", "value-b");
        System.out.println(jedis.mget("key-a", "key-b"));

        //遍历所有的key
        System.out.println(jedis.keys("*"));
        jedis.del("key-a");
        jedis.del("key-b");
        System.out.println(jedis.keys("*"));
    }

    @Test
    public void testMap(){
        //hash 设置key
        jedis.hset("map-key", "key-a", "value-a");
        //hash m设置key
        Map map=new HashMap();
        map.put("key-b", "value-b");
        map.put("key-c", "value-c");
        jedis.hmset("map-key", map);

        //hash mget
        List<String> list=jedis.hmget("map-key","key-a","key-b","key-c");
        System.out.println(list.toString());

        //hash key
        System.out.println(jedis.hkeys("map-key"));

        //hash values
        System.out.println(jedis.hvals("map-key"));

        //hash length
        System.out.println(jedis.hlen("map-key"));

        //exists
        System.out.println(jedis.exists("map-key"));

    }

    @Test
    public void testList(){
        jedis.del("list-key");

        jedis.lpush("list-key", "a");
        jedis.lpush("list-key", "a");
        jedis.lpush("list-key", "-b");
        jedis.rpush("list-key", "+b");

        System.out.println(jedis.llen("list-key"));
        System.out.println(jedis.lrange("list-key", 0, -1));

//        System.out.println(jedis.sort("list-key"));
        System.out.println(jedis.lrange("list-key", 0, -1));

        jedis.lrem("list-key", 2, "a");
        System.out.println(jedis.lrange("list-key", 0, -1));
    }

    @Test
    public void testSet(){
        jedis.sadd("set-key", "a");
        jedis.sadd("set-key", "b");
        jedis.sadd("set-key", "c");
        jedis.sadd("set-key", "d");

        jedis.srem("set-key", "d");

        System.out.println(jedis.smembers("set-key"));
        System.out.println(jedis.sismember("set-key", "a"));
        System.out.println(jedis.srandmember("set-key", 1));
        System.out.println(jedis.scard("set-key"));
    }

    @Test
    public void testZSet(){
        jedis.zremrangeByRank("zset-key",0,100);
        jedis.zadd("zset-key", 1.1, "2.1-v");
        jedis.zadd("zset-key",2.2,"1.2-v");

        System.out.println(jedis.zrange("zset-key", 0, 100));
        System.out.println(jedis.zrangeByLex("zset-key","[2","(3"));
    }


    private static class Person{
        public String name="name";
    }
}
