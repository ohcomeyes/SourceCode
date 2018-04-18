package com.boce.flcp.web;

import com.boce.flcp.domain.list.UserSpecimenList;
import com.boce.flcp.domain.model.DemandAnalyze;
import com.boce.flcp.domain.model.PendingWork;
import com.boce.flcp.service.IndexService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.web
 * @Description: TODO
 * @date 2017/12/4 14:06
 */
@RestController
@RequestMapping("/api/index")
public class IndexController {
    @Autowired
    IndexService indexService;

    @ApiOperation(value = "任务统计",notes = "首页统计")
    @RequestMapping(value = "/getTotal",method = RequestMethod.GET)
    public Map getTotal(){
        return indexService.getTotal();
    }

    @ApiOperation(value = "工作记录",notes = "统计待处理",response = PendingWork.class)
    @RequestMapping(value = "/getCountPendingWork",method = RequestMethod.GET)
    public List<PendingWork> getCountPendingWork(){
        return indexService.getCountPendingWork();
    }

    @ApiOperation(value = "报表统计",notes = "首页报表统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "begin_time", value = "开始时间", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "end_time", value = "结束时间", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/getStatisticsTable",method = RequestMethod.GET)
    public Map<String,List> getStatisticsTable(String begin_time,String end_time){
        return indexService.getStatisticsTable(begin_time,end_time);
    }

    @ApiOperation(value = "需求时间内统计",notes = "首页需求时间内统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "begin_time", value = "开始时间", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "end_time", value = "结束时间", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/getCountDemandTable",method = RequestMethod.GET)
    public Map getCountDemandTable(String begin_time,String end_time){
       return indexService.getCountDemandTable(begin_time,end_time);
    }

    @ApiOperation(value = "前五需求",notes = "最热前五需求")
    @RequestMapping(value = "/getTopFiveDemand",method = RequestMethod.GET)
    public List<Object> getTopFiveDemand(){
        return indexService.getTopFiveDemand();
    }
}
