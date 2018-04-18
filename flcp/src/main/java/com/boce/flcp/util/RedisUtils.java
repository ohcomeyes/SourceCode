package com.boce.flcp.util;

import com.boce.flcp.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
@Component
public class RedisUtils {
    //Spring Data Redis(SDR)
    // redis虽然提供了对list set hash等数据类型的支持，但是没有提供对POJO对象的支持，
    // 底层都是把对象序列化后再以字符串的方式存储的。因此，Spring data提供了若干个Serializer
    //SDR是SpringFramework提供的一套简化访问Redis的API，是对Jedis的又一层封装。
    // SDR集成了Jedis，JRedis，SRP，Lettuce这四种开源的Redis Connector，这些Connector都是针对于Redis的开源Java库
    // SDR默认采用的序列化策略有两种，一种是String的序列化策略，一种是JDK的序列化策略。
    //StringRedisTemplate默认采用的是String的序列化策略，保存的key和value都是采用此策略序列化保存的。
    //RedisTemplate默认采用的是JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。
    //两者的关系是StringRedisTemplate继承RedisTemplate。
    // 两者的数据是不共通的；也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，RedisTemplate只能管理RedisTemplate中的数据。

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 新增集合
     * @param key
     * @param value
     */
    public boolean saveSet(final String key,Object... value){
        boolean result = false;
        try{
            redisTemplate.opsForSet().add(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取集合
     * @param key
     * @return
     */
    public Set<Object> getSet(final String key){
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 获取集合数量
     * @param key
     * @return
     */
    public Long getSetSize(final String key){
        return redisTemplate.opsForSet().size(key);
    }


    /**
     * @Title: savezSet
     * @Description: TODO  (有序集合通过score排序)
     * @Author xulovehua
     * @Date 2017/11/24 17:07
     * @Param [key, score, value]
     * @return boolean
     */
    public boolean savezSet(final String key,double score,Object... value){
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    /**
     * 写入列表 --遍历方向不同，所以效率也不同
     * @param key
     * @param value
     * @return
     */
    public boolean saveList(final String key, final String value){
        boolean result = false;
        try{
            redisTemplate.opsForList().rightPush(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入列表集合 --遍历方向不同，所以效率也不同
     * @param key
     * @param value
     * @return
     */
    public boolean saveList(final String key, Object... value){
        boolean result = false;
        try{
            redisTemplate.opsForList().rightPushAll(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入列表集合 --遍历方向不同，所以效率也不同
     * @param key
     * @param value
     * @return
     */
    public boolean saveList(final String key, Collection<Object> value){
        boolean result = false;
        try{
            redisTemplate.opsForList().rightPushAll(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取列表
     * @param key
     * @return
     */
    public List<String> getList(final String key){
        return (List<String>)redisTemplate.opsForList().range(key,0,-1);
    }

    /**
     * 写入map集合
     * @param key
     * @param value
     * @return
     */
    public boolean saveMap(final String key ,Map<String,String> value){
        boolean result = false;
        try{
            redisTemplate.opsForHash().putAll(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Title: saveStringMap
     * @Description: TODO  (String序列化)
     * @Author xulovehua
     * @Date 2017/12/21 14:57
     * @Param [key, value]
     * @return boolean
     */
    public boolean saveStringMap(final String key ,Map<Object,Object> value){
        boolean result = false;
        try{
            stringRedisTemplate.opsForHash().putAll(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Title: putStringMap
     * @Description: TODO  (修改)
     * @Author xulovehua
     * @Date 2017/12/21 15:07
     * @Param [key, map_key, value]
     * @return boolean
     */
    public boolean putStringMap(final String key,final String map_key,final String value){
        boolean result = false;
        try{
            stringRedisTemplate.opsForHash().put(key,map_key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取map集合
     * @param key
     * @return
     */
    public Map<String,String > getMap(final String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @Title: getStringMap
     * @Description: TODO  (获取String序列化map)
     * @Author xulovehua
     * @Date 2017/12/21 14:59
     * @Param [key]
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     */
    public Map<Object, Object> getStringMap(final String key){
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * @Title: getPipelinedMap
     * @Description: TODO  (通过管道批量获取map)
     * @Author xulovehua
     * @Date 2017/12/21 16:20
     * @Param [keys]
     * @return java.util.List<java.lang.Object>
     */
    public List<Object> getPipelinedMap(final List<String> keys){
        List<Object> list = stringRedisTemplate.executePipelined(new RedisCallback(){
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn = (StringRedisConnection) connection;
                for(String key:keys){
                    conn.hGetAll(key);
                }
                return null;
            }
        });
        return list;
    }


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public void pipelined(){
//        try{
//            redisTemplate.executePipelined(new RedisCallback<Object>() {
//                public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                    connection.openPipeline();//打开管道链接
//                     ListOperations opsForList = redisTemplate.opsForList();
////                    StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
//                    for(int i=0; i< 1000; i++) {
////                      stringRedisConn.rPop("myqueue");
//                    }
//                    return null;
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
