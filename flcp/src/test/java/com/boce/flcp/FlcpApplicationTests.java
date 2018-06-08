package com.boce.flcp;

import com.boce.flcp.dao.WorksRepository;
import com.boce.flcp.domain.Works;
import com.boce.flcp.domain.model.DemandAnalyze;
import com.boce.flcp.domain.model.DemandStatement;
import com.boce.flcp.domain.model.PendingWork;
import com.boce.flcp.service.IndexService;
import com.boce.flcp.util.CommonUtils;
import com.boce.flcp.util.RedisUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.util.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FlcpApplicationTests {
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    RedisTemplate redisTemplate;
//    @Autowired
//    RedisUtils redisUtils;
//
//    @Autowired
//    WorksRepository worksRepository;

    // @Test
//    public void writeWorks(){
//        String path="C:/Users/tangxu/Desktop/协同设计平台模板/girl";
//        File file=new File(path);
//        File[] tempList = file.listFiles();
//        System.out.println("该目录下对象个数："+tempList.length);
//        List<Works> worksList = new ArrayList<>();
//        for (int i = 0; i < tempList.length; i++) {
//            if (tempList[i].isFile()) {
//                System.out.println("文件：" + tempList[i].getName());
//                Works works = new Works();
//                works.setType("福利");
//                works.setUsed("1099833");
//                works.setSource("xiaochengxu");
//                works.setUrl(tempList[i].getName());
//                works.setWho("daimajia");
//                worksList.add(works);
//            }
//        }
//        worksRepository.save(worksList);
//    }

//    @Test
    @SuppressWarnings("unchecked")
    public void contextLoads() {
//		System.out.println(redisTemplate.opsForValue().get("123"));
//		List<String> list = new ArrayList<>();
//		for(int i=0;i<100;i++){
//			list.add(i+"");
//		}
//		long start = System.currentTimeMillis();
//		int i = 2;
//		for(String str : list){
//			redisTemplate.opsForValue().set("test"+i,str);//467
//			i++;
//		}
//        DemandStatement demandStatement = new DemandStatement();
//        demandStatement.setId("2");
//        demandStatement.setName("嬉皮笑脸");
//        redisTemplate.opsForValue().set("pojo2",demandStatement);
//		System.out.println(System.currentTimeMillis()-start);
//		long start1 = System.currentTimeMillis();
//		redisTemplate.opsForList().rightPushAll("test1",list);
//		System.out.println(System.currentTimeMillis()-start1);
        long start = System.currentTimeMillis();
//		List<String> list = redisTemplate.opsForList().range("test",0,10);
//		System.out.println(list);
//		System.out.println(System.currentTimeMillis()-start);

//        redisTemplate.delete("demand:statistics:20171221");
//        redisTemplate.delete("demand:statistics:20171222");

//        String begin_time = "2017-12-21";
//        String end_time = "2017-12-25";
//        List<String> keys = CommonUtils.getBetweenDatesAndPrefix(begin_time,end_time,"yyyyMMdd",IndexService.KEY_DEMAND_STATISTICS);
//        List<Object> list = redisUtils.getPipelinedMap(keys);
//        System.out.println(list);
//        for(Object object : list){
//            JSONObject jasonObject = JSONObject.fromObject(object);
//            if(!jasonObject.isEmpty()){
//                System.out.println(jasonObject);
//            }else{
//                System.out.println("空:"+jasonObject);
//            }
//
//        }

//        Map map = new HashMap<>();
//        map.put("size","10");
//        map.put("money","70084.00");
//        stringRedisTemplate.opsForHash().putAll("demand:statistics:20171222",map);
//        System.out.println(stringRedisTemplate.opsForHash().entries("demand:statistics:20171222"));
//		List<Object> list = this.stringRedisTemplate.executePipelined(new RedisCallback() {
//			@Override
//			public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
//					StringRedisConnection conn = (StringRedisConnection) connection;
////					for(int i =2 ;i<102;i++){
////						conn.get("test"+i);
////					}
////                    conn.get("demand:statistics:20171221");
////                    conn.get("demand:statistics:20171222");
//                    conn.hGetAll("demand:statistics:20171221");
//                    conn.hGetAll("demand:statistics:20171222");
//					return null;
//			}
//		});
//        System.out.println(list);

		//pipeline
//		RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>() {
//			@Override
//			public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
//				connection.openPipeline();
//                final byte[] rawKey = redisTemplate.getKeySerializer().serialize("pojo1");
//                final byte[] rawKey1 = redisTemplate.getKeySerializer().serialize("pojo2");
//                connection.get(rawKey);
//                connection.get(rawKey1);
////				connection.incr(rawKey);
////				connection.incr(rawKey);
//				return connection.closePipeline();
//			}
//		};
//		List<Object> results = (List<Object>)redisTemplate.execute(pipelineCallback);
//        System.out.println(results);
//        for(Object o : results){
//
//        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
