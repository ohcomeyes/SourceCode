package com.boce.flcp.web;


import com.boce.flcp.domain.Cost;
import com.boce.flcp.domain.Unify;
import com.boce.flcp.domain.User;
import com.boce.flcp.service.CostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: flcp
 * @description: 规则控制器
 * @author: Mr.Tang
 * @create: 2018-03-28 15:29
 **/
@Api(value="5_规则",position=5,description ="规则API")
@RestController
@RequestMapping("/api/costs")
public class CostController {
    @Autowired
    CostService costService;

    /**
    * @Description: 获取所有规则
    * @Param: []
    * @return: java.util.List<com.boce.flcp.domain.Cost>
    * @Author: Mr.Tang
    * @Date: 2018/3/28
    */
    @ApiOperation(value = "获取规则列表",notes = "获取规则列表信息",response = Cost.class,responseContainer = "List")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Cost> getUserList(){
        return costService.getCostList();
    }

    /**
    * @Description: 获取单条规则信息
    * @Param: [id]
    * @return: com.boce.flcp.domain.Cost
    * @Author: Mr.Tang
    * @Date: 2018/3/29
    */ 
    @ApiOperation(value = "获取单个规则信息",notes = "获取单个规则信息",response = User.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cost getCostById(@PathVariable Long id){
        return costService.getCostById(id);
    }

    /**
    * @Description: 添加规则
    * @Param: [cost]
    * @return: com.boce.flcp.domain.Unify
    * @Author: Mr.Tang
    * @Date: 2018/3/29
    */ 
    @ApiOperation(value = "添加规则",notes = "添加规则")
    @RequestMapping(value = "/addCost",method = RequestMethod.POST)
    public Unify addCost(@RequestBody Cost cost){
        return costService.addCost(cost);
    }
}
