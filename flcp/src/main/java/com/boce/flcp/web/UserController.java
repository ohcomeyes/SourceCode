package com.boce.flcp.web;

import com.boce.flcp.assembler.UserAssembler;
import com.boce.flcp.domain.*;
import com.boce.flcp.resource.UserResource;
import com.boce.flcp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@Api(value="3_用户",position=3,description ="用户API")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;


    /**
     * 验证
     */
    @ApiOperation(value = "注册验证码",notes = "根据注册帐号发送验证码")
    @ApiImplicitParam(name = "account", value = "注册帐号", required = true, dataType = "String",paramType = "query")
    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public Validate validateAndSms(String account){
        System.out.println("进入验证方法");
        return userService.validateAndSms(account);
    }

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value = "获取用户列表",notes = "获取用户列表信息",response = User.class,responseContainer = "List")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Resources<UserResource> getUserList(){
       return new Resources<UserResource>(new UserAssembler().toResources(userService.getUserList()));
    }


    /**
     * 根据用户Id查询用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单个用户信息",notes = "根据用户Id查询用户信息",response = User.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserResource readList(@PathVariable Long id) {
        return new UserAssembler().toResource(userService.getUserById(id));
    }

    /**
     * @Title: save
     * @Description: TODO (设计师注册)
     * @Author xulovehua
     * @Date 2018/1/18 10:42
     * @Param [user]
     * @return com.boce.flcp.domain.Unify
     */
    @ApiOperation(value = "设计师注册",notes = "设计师根据注册信息进行注册")
    //接收为对象无需定义属性
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "account", value = "注册帐号", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "注册密码", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "name", value = "昵称", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "sex", value = "性别", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "design_industry", value = "设计行业", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "design_age", value = "设计年限", required = true, dataType = "String",paramType = "query")
//    })
    @RequestMapping(value = "/designRegister",method = RequestMethod.POST)
    public Unify designRegister(@RequestBody User user){
        return userService.designRegister(user);
    }

    /**
     * @Title: login
     * @Description: TODO(设计师登录)
     * @Author xulovehua
     * @Date 2018/1/18 10:43
     * @Param [request]
     * @return com.boce.flcp.domain.Content
     */
    @ApiOperation(value = "设计师登录",notes = "根据帐号进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "帐号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/designLogin",method = RequestMethod.POST)
    public Content designLogin(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        return userService.designLogin(account,password);
    }


    /**
     * @Title: save
     * @Description: TODO  (打板师注册)
     * @Author xulovehua
     * @Date 2018/1/18 10:43
     * @Param [user]
     * @return com.boce.flcp.domain.Unify
     */
    @ApiOperation(value = "打板师注册",notes = "根据注册信息进行注册")
    @RequestMapping(value = "/specimenRegister",method = RequestMethod.POST)
    public Unify specimenRegister(@RequestBody UserSpecimen userSpecimen){
        return userService.specimenRegister(userSpecimen);
    }

    /**
     * @Title: login
     * @Description: TODO （打板师登录）
     * @Author xulovehua
     * @Date 2018/1/18 10:43
     * @Param [request]
     * @return com.boce.flcp.domain.Content
     */
    @ApiOperation(value = "打板师登录",notes = "根据帐号进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "帐号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/specimenLogin",method = RequestMethod.POST)
    public Content specimenLogin(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        return userService.specimenLogin(account,password);
    }


    @ApiOperation(value = "获取打板师信息",notes = "查看打板师信息",response = UserSpecimen.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "打板师Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/getUserSpecimen",method = RequestMethod.GET)
    public UserSpecimen getUserSpecimen(Long id){
        return userService.getUserSpecimen(id);
    }

    @ApiOperation(value = "获取所有打板师信息",notes = "查看所有打板师信息",response = UserSpecimen.class)
    @RequestMapping(value = "/getAllUserSpecimen",method = RequestMethod.GET)
    public List<UserSpecimen> getAllUserSpecimen(){
        return userService.getAllUserSpecimen();
    }

//    /**
//     * 测试排序
//     */
//    @RequestMapping("/sort")
//    public List<User> sort(){
//
//        List<User> u = userRepository.findAll(new Sort(Sort.Direction.ASC,"id"));
//
//        return u;
//
//    }
//
//    /**
//     * 测试分页
//     */
//    @RequestMapping("/page")
//    public Page<User> page(){
//
//        Page<User> pageUser = userRepository.findAll(new PageRequest(1, 2));
//
//        return pageUser;
//
//    }
}
