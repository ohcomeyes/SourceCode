package com.boce.flcp.api.xiaochengxu.data;

import com.boce.flcp.api.xiaochengxu.entity.WebDemand;
import com.boce.flcp.service.DemandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: flcp
 * @description: 小程序首页任务
 * @author: Mr.Tang
 * @create: 2018-05-07 10:11
 **/
@Api(value="6_小程序首页任务",position=6,description ="小程序任务API")
@RestController
@RequestMapping("/api/data/web")
public class WebController {
    @Autowired
    DemandService demandService;

    @ApiOperation(value = "小程序首页任务",notes = "小程序首页任务")
    @RequestMapping(value = "/{size}/{page}",method = RequestMethod.GET)
    public Page<WebDemand> getWebList(@PathVariable int size, @PathVariable int page){
        return demandService.getWebList(size,page-1);
    }

}
