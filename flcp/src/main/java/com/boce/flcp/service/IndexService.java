package com.boce.flcp.service;

import com.boce.flcp.dao.DemandRepository;
import com.boce.flcp.domain.model.DemandAnalyze;
import com.boce.flcp.domain.model.PendingWork;
import com.boce.flcp.util.CommonUtils;
import com.boce.flcp.util.RedisUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.service
 * @Description: TODO(首页)
 * @date 2017/12/4 14:06
 */
@Service
public class IndexService {
    //where demand_time>='2017-11-04 00:00:00' and demand_time<='2017-12-27 23:59:59'
    public static final String KEY_DEMAND_STATISTICS ="demand:statistics:";
    @Autowired
    DemandRepository demandRepository;
    @Autowired
    RedisUtils redisUtils;

    /**
     * @Title: getTotal
     * @Description: TODO  (任务统计)
     * @Author xulovehua
     * @Date 2017/12/18 16:05
     * @Param []
     * @return java.util.Map
     */
    public Map getTotal(){
        Map map = demandRepository.findDemandTotal();
        map.put("demand_money",new BigDecimal(map.get("demand_money").toString()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        return map;
    }


    /**
     * @Title: getPendingWork
     * @Description: TODO  (统计待处理的工作)
     * @Author xulovehua
     * @Date 2017/12/14 13:58
     * @Param []
     * @return List<PendingWork>
     */
    public List<PendingWork> getCountPendingWork(){
        List<PendingWork> pendingWorkList = new ArrayList<>();
        LinkedList<String> demandTime = demandRepository.findPendingByDemand();
        LinkedList<String> designTime = demandRepository.findPendingByDesign();
        LinkedList<String> specimenTime = demandRepository.findPendingBySpecimen();
        //需求
        pendingWorkList.add(getPendingWork(demandTime));
        //设计
        pendingWorkList.add(getPendingWork(designTime));
        //打板
        pendingWorkList.add(getPendingWork(specimenTime));
        return pendingWorkList;
    }

    /**
     * @Title: getPendingWork
     * @Description: TODO  (获取待处理工作记录)
     * @Author xulovehua
     * @Date 2017/12/14 14:35
     * @Param [workTime]
     * @return com.boce.flcp.domain.model.PendingWork
     */
    public PendingWork getPendingWork( LinkedList<String> workTime){
        PendingWork pendingWork = new PendingWork();
        if(workTime.size()>=1){
            pendingWork.setCount(workTime.size());
            pendingWork.setStart_time(workTime.getFirst());
            pendingWork.setEnd_time(workTime.getLast());
            pendingWork.setInterval_time(CommonUtils.getTwoIntervalTime(workTime.getFirst()));
        }else{
            pendingWork.setCount(0);
            pendingWork.setStart_time("暂无");
            pendingWork.setEnd_time("暂无");
            pendingWork.setInterval_time("0天0小时0分");
        }
        return pendingWork;
    }

    /**
     * @Title: getStatisticsTable
     * @Description: TODO  (获取指定时间内的数据报表)
     * @Author xulovehua
     * @Date 2017/12/21 16:25
     * @Param [begin_time, end_time]
     * @return java.util.List<java.util.Map>
     */
    public Map<String,List> getStatisticsTable(String begin_time,String end_time){
        List<String> keys = CommonUtils.getBetweenDatesAndPrefix(begin_time,end_time,"yyyyMMdd",KEY_DEMAND_STATISTICS);
        List<Object> list = redisUtils.getPipelinedMap(keys);
        Map<String,List> tableMap = new HashMap<>();
        List<Object> sizes = new ArrayList<>();//数量
        List<Object> moneys = new ArrayList<>();//金额
        for(Object object : list){
            JSONObject jsonObject = JSONObject.fromObject(object);
            if(!jsonObject.isEmpty()){
                sizes.add(Integer.valueOf(jsonObject.get("size").toString()));
                moneys.add(new BigDecimal(jsonObject.get("money").toString()));
            }else{
                sizes.add(0);
                moneys.add(0.00);
            }
        }
        tableMap.put("demand",sizes);
        tableMap.put("money",moneys);
        return tableMap;
    }

    /**
     * @Title: getCountDemandTable
     * @Description: TODO  (首页需求时间段统计)
     * @Author xulovehua
     * @Date 2017/12/25 14:37
     * @Param [begin_time, end_time]
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public Map getCountDemandTable(String begin_time,String end_time){
       Map map = demandRepository.findCountDemandTable(begin_time+" 00:00:00",end_time+" 23:59:59");
       if(map.get("money") == null){
            map.put("money","0.00");
       }
       System.out.println(map);
       int num1 = Integer.valueOf(map.get("winner").toString());
       int num2 = Integer.valueOf(map.get("size").toString());
       //计算百分比 数学除法规定，0不能做除数，因为会得到一个无穷大数据。
       if(num1 >0 && num2 > 0){
           map.put("percentage",CommonUtils.getPercentage(num1,num2));
       }else{
           map.put("percentage","0%");
       }
       return map;
    }

    /**
     * @Title: getTopFiveDemand
     * @Description: TODO
     * @Author xulovehua
     * @Date 2017/12/26 10:23
     * @Param []
     * @return java.util.Map
     */
    public List<Object> getTopFiveDemand(){
        List<Object> objects = demandRepository.findTopFiveDemand();
        return objects;
    }
}
