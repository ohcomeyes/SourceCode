package com.boce.flcp.api.xiaochengxu.data;

import com.boce.flcp.api.xiaochengxu.entity.Superior;
import com.boce.flcp.service.UserService;
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
 * @description: 小程序首页大牛
 * @author: Mr.Tang
 * @create: 2018-05-07 10:15
 **/
@Api(value="7_小程序首页大牛",position=7,description ="小程序大牛API")
@RestController
@RequestMapping("/api/data/superior")
public class SuperiorController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "小程序首页大牛",notes = "小程序首页大牛")
    @RequestMapping(value = "/{size}/{page}",method = RequestMethod.GET)
    public Page<Superior> getSuperiorList(@PathVariable int size, @PathVariable int page){
        return userService.getSuperiorList(size,page-1);
    }
}
