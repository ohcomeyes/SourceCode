package com.boce.flcp.service;

import com.boce.flcp.dao.CostRepository;
import com.boce.flcp.domain.Cost;
import com.boce.flcp.domain.Unify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @program: flcp
 * @description: 规则服务
 * @author: Mr.Tang
 * @create: 2018-03-28 15:35
 **/
@Service
public class CostService {
    @Autowired
    CostRepository costRepository;

    /**
    * @Description: 获取规则列表
    * @Param: []
    * @return: List<Cost>
    * @Author: Mr.Tang
    * @Date: 2018/3/28
    */
    public List<Cost> getCostList(){
        return costRepository.findAll();
    }

    /**
    * @Description: 获取单条规则嘻嘻你
    * @Param: [id]
    * @return: com.boce.flcp.domain.Cost
    * @Author: Mr.Tang
    * @Date: 2018/3/29
    */
    public Cost getCostById(Long id){
        return costRepository.findOne(id);
    }

    /**
    * @Description: 添加规则
    * @Param: [cost]
    * @return: com.boce.flcp.domain.Unify
    * @Author: Mr.Tang
    * @Date: 2018/3/29
    */
    public Unify addCost(Cost cost){
        Unify unify = new Unify();
        try {
            costRepository.save(cost);
            unify.setCode("200");
            unify.setMsg("添加成功");
        }catch (Exception e){
            unify.setCode("201");
            unify.setMsg("添加失败");
            e.printStackTrace();
        }
        return unify;
    }

}
