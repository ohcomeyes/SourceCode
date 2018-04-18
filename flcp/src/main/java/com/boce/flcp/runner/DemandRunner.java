package com.boce.flcp.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.runner
 * @Description: TODO(服务启动后需求初始化加载)
 * @date 2017/11/30 10:54
 */
@Component
public class DemandRunner implements CommandLineRunner {
    public static Map<String,Map> entranceMap = new ConcurrentHashMap<String,Map>();
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    }
}
