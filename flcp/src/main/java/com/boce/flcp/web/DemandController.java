package com.boce.flcp.web;

import com.boce.flcp.assembler.DemandAssembler;
import com.boce.flcp.assembler.UserAssembler;
import com.boce.flcp.dao.DemandRepository;
import com.boce.flcp.domain.*;
import com.boce.flcp.domain.list.DemandList;
import com.boce.flcp.domain.list.DesignList;
import com.boce.flcp.domain.list.SpecimenList;
import com.boce.flcp.domain.list.UserSpecimenList;
import com.boce.flcp.resource.BeanResource;
import com.boce.flcp.resource.DemandResource;
import com.boce.flcp.resource.UserResource;
import com.boce.flcp.service.DemandService;
import com.boce.flcp.util.CommonUtils;
import io.swagger.annotations.*;
import org.apache.tomcat.jni.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api/demands")
public class DemandController {
    @Autowired
    DemandService demandService;

    @ApiOperation(value = "需求发布",notes = "雇主发布需求信息")
    //接收为对象无需定义属性
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "demand_name", value = "需求内容", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "demand_employer", value = "雇主", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "demand_type", value = "需求类型", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "demand_money", value = "需求金额", required = true, dataType = "Long",paramType = "query"),
//            @ApiImplicitParam(name = "demand_imgs", value = "参考图",  dataType = "MultipartFile",paramType = "file"),
//            @ApiImplicitParam(name = "demand_describe", value = "需求描述", required = true, dataType = "String",paramType = "body"),
//            @ApiImplicitParam(name = "demand_accessory", value = "需求附件",  dataType = "MultipartFile",paramType = "file"),
//            @ApiImplicitParam(name = "start_date", value = "开始时间", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "end_date", value = "截至时间", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "classify_type", value = "任务类型", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "phone", value = "联系电话", required = true, dataType = "String",paramType = "query")
//    })
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Unify save(@RequestBody Demand demand){
        return demandService.save(demand);
    }


    @ApiOperation(value = "获取所有需求",notes = "查看所有需求信息",response = Demand.class,responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功")
    })
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Resources<DemandResource> getDemandList(){
        return new Resources<DemandResource>(new DemandAssembler().toResources(demandService.findAll()));
    }

    @ApiOperation(value = "获取需求列表",notes = "查看需求列表信息")
    @RequestMapping(value = "/findListByDemand",method = RequestMethod.GET)
    public ResponseEntity<List<DemandList>> findListByDemand(){
        return  new ResponseEntity<List<DemandList>>(demandService.findListByDemand(),HttpStatus.OK);
    }

    @ApiOperation(value = "获取设计列表",notes = "查看设计列表信息")
    @RequestMapping(value = "/findListByDesign",method = RequestMethod.GET)
    public ResponseEntity<List<DesignList>> findListByDesign(){
        return new ResponseEntity<List<DesignList>>(demandService.findListByDesign(),HttpStatus.OK);
    }

    @ApiOperation(value = "获取打板列表",notes = "查看打板列表信息")
    @RequestMapping(value = "/findListBySpecimen",method = RequestMethod.GET)
    public ResponseEntity<List<SpecimenList>> findListBySpecimen(){
        return new ResponseEntity<List<SpecimenList>>(demandService.findListBySpecimen(),HttpStatus.OK);
    }



    @ApiOperation(value = "获取需求设计列表(用户)",notes = "查看可以竞标需求设计列表信息，登录or未登录")
    @RequestMapping(value = "/findMyDemandList",method = RequestMethod.GET)
    public ResponseEntity<List<DemandList>> findMyDemandList(){
        return  new ResponseEntity<List<DemandList>>(demandService.findMyDemandList(),HttpStatus.OK);
    }

    @ApiOperation(value = "获取设计待打板列表(用户)",notes = "用户查看待打板列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/findMyToSpecimenList",method = RequestMethod.GET)
    public ResponseEntity<List<SpecimenList>> findMyToSpecimenList(Long user_id){
        return new ResponseEntity<List<SpecimenList>>(demandService.findMyToSpecimenList(user_id),HttpStatus.OK);
    }


    @ApiOperation(value = "获取待设计列表(用户)",notes = "查看待设计需求信息【已中标需求_bid、设计审核中_audit、设计驳回需求_reject】",response = Demand.class,responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/getMyToDesignList",method = RequestMethod.GET)
    public Iterable<Demand> getMyToDesignList(Long user_id){
        return demandService.getMyToDesignList(user_id);
    }

    @ApiOperation(value = "获取设计完成列表(用户)",notes = "查看待处理需求信息【设计审核通过_approve】",response = Demand.class,responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/getMyPendingList",method = RequestMethod.GET)
    public Iterable<Demand> getMyPendingList(Long user_id){
        return demandService.getMyPendingList(user_id);
//        return new Resources<DemandResource>(new DemandAssembler().toResources(demandService.getMyPendingList(user_id)));
    }

    @ApiOperation(value = "获取打板跟踪列表(用户)",notes = "查看打板跟踪需求信息【待打板_making、送样_affirm、送样审核中_send、打板送样确认_affirm、样品驳回_specimenReject】",response = Demand.class,responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/getMySpecimenTailList",method = RequestMethod.GET)
    public Iterable<Demand> getMySpecimenTailList(Long user_id){
        return demandService.getMySpecimenTailList(user_id);
    }

    @ApiOperation(value = "获取已完成列表(用户)",notes = "查看已完成需求信息【完成的设计/完成的样品_done、启用_using】",response = Demand.class,responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/getMyDoneList",method = RequestMethod.GET)
    public Iterable<Demand> getMyDoneList(Long user_id){
        return demandService.getMyDoneList(user_id);
    }


    @ApiOperation(value = "获取单个需求",notes = "根据需求Id获取需求信息,用户Id可为空",response = Demand.class)
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public DemandResource getDemandById(@PathVariable Long id){
        return new DemandAssembler().toResource(demandService.getDemandById(id));
    }

    @ApiOperation(value = "获取单个需求(用户)",notes = "根据需求Id和用户Id获取需求信息",response = Demand.class)
    @RequestMapping(value = "/{id}/{user_id}",method = RequestMethod.GET)
    public DemandResource getDemandByIdAndUser(@PathVariable Long id,@PathVariable Long user_id){
        return new DemandAssembler().toResource(demandService.getDemandByIdAndUser(id,user_id));
    }




    @ApiOperation(value = "需求竞标",notes = "设计师参与需求竞标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/applyPetitive",method = RequestMethod.GET)
    public Unify applyPetitiveUser(Long demand_id, Long user_id){
        return demandService.applyPetitiveUser(demand_id,user_id);
    }

    @ApiOperation(value = "竞标人信息",notes = "所有参加当前需求竞标人的信息")
    @RequestMapping(value = "/petitive/{demand_id}",method = RequestMethod.GET)
    public ResponseEntity<List<PetitiveUser>> petitiveUsers(@PathVariable Long demand_id){
        return  new ResponseEntity<List<PetitiveUser>>(demandService.petitiveUsers(demand_id),HttpStatus.OK);
    }

    @ApiOperation(value = "中标",notes = "选择竞标人中标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "user_id", value = "用户Id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/bidWinner",method = RequestMethod.GET)
    public Unify selectBidWinner(Long demand_id,Long user_id){
        return demandService.selectBidWinner(demand_id,user_id);
    }

    @ApiOperation(value = "提交设计",notes = "设计师提交设计")
    //接收为对象无需定义属性
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "imgs", value = "设计缩略图(name%url#name%url)", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "describe", value = "设计描述", required = true, dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "accessorys", value = "附件(name%url#name%url)", required = true, dataType = "String",paramType = "query")
//    })
    @RequestMapping(value = "/submitDesign",method = RequestMethod.POST)
    public Unify submitDesign(@RequestBody Design design){
        return demandService.submitDesign(design);
    }

    @ApiOperation(value = "审核设计",notes = "需求设计审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "design_id", value = "设计id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/designAudit",method = RequestMethod.GET)
    public Unify designAudit(Long demand_id,String design_id, String status){
        return demandService.designAudit(demand_id,design_id,status);
    }

    @ApiOperation(value = "获取打板师",notes = "iframe列表",response = UserSpecimenList.class)
    @RequestMapping(value = "/userSpecimenList",method = RequestMethod.GET)
    public UserSpecimenList userSpecimenList(){
        return demandService.userSpecimenList();
    }

    @ApiOperation(value = "选中打板师",notes = "打板师竞标或者主动分发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "specimen_user_id", value = "打板师id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/selectSpecimenUser",method = RequestMethod.GET)
    public Unify selectSpecimenUser(Long demand_id,Long specimen_user_id){
        return demandService.selectSpecimenUser(demand_id,specimen_user_id);
    }

    @ApiOperation(value = "提交打板",notes = "打板师提交打板")
    @RequestMapping(value = "/submitSpecimen",method = RequestMethod.POST)
    public Unify submitSpecimen(@RequestBody Specimen specimen){
        return demandService.submitSpecimen(specimen);
    }

    @ApiOperation(value = "设计师确认",notes = "设计师确认打板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "specimen_id", value = "打板明细id", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/affirmSpecimen",method = RequestMethod.GET)
    public Unify affirmSpecimen(Long demand_id,String specimen_id){
        return demandService.affirmSpecimen(demand_id,specimen_id);
    }

    @ApiOperation(value = "审核打板",notes = "设计打板审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "specimen_id", value = "打板id", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/specimenAudit",method = RequestMethod.GET)
    public Unify specimenAudit(Long demand_id,String specimen_id, String status){
        return demandService.specimenAudit(demand_id,specimen_id,status);
    }

    @ApiOperation(value = "确定收样",notes = "后台确定收到样品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/doneSendSpecimen",method = RequestMethod.GET)
    public Unify doneSendSpecimen(Long demand_id){
        return demandService.doneSendSpecimen(demand_id);
    }

    @ApiOperation(value = "启用",notes = "启用需求设计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/usingDemandDesign",method = RequestMethod.GET)
    public Unify usingDemandDesign(Long demand_id){
        return demandService.usingDemandDesign(demand_id);
    }

    @ApiOperation(value = "完成",notes = "完成需求设计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demand_id", value = "需求id", required = true, dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/doneDemandDesign",method = RequestMethod.GET)
    public Unify doneDemandDesign(Long demand_id){
        return demandService.doneDemandDesign(demand_id);
    }

//    @RequestMapping(value = "/search",method = RequestMethod.GET)
//    public Resource<Demand> findPhone(){
////        return new ListResourceAssembler().toResource(demandService.findPhone());
//    return null;
//    }


}
