package com.boce.flcp.api.xiaochengxu.data;

import com.boce.flcp.domain.Works;
import com.boce.flcp.service.WorksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: flcp
 * @description: 作品
 * @author: Mr.Tang
 * @create: 2018-05-08 11:00
 **/
@RestController
@RequestMapping("/api/data/works")
public class WorksController {
    @Autowired
    WorksService worksService;

    @ApiOperation(value = "小程序首页作品",notes = "小程序首页作品")
    @RequestMapping(value = "/{size}/{page}",method = RequestMethod.GET)
    public Page<Works> getWorks(@PathVariable int size, @PathVariable int page){
        return worksService.getWorks(size,page-1);
    }
}
