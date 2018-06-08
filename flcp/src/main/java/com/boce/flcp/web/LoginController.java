package com.boce.flcp.web;

import com.boce.flcp.domain.Content;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value="1_后端登陆",position=1,description ="后端登陆API")
@RequestMapping("/system")
@RestController
public class LoginController {

    /***
     * 登录
     */
    @ApiOperation(value = "登录",notes = "协调设计平台登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "帐号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Content Login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Content content = new Content();
        content.setCode("200");
        content.setMsg("登录成功");
        return content;
    }

}
