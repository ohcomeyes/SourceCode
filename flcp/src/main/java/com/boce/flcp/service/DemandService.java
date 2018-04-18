package com.boce.flcp.service;

import com.boce.flcp.dao.DemandRepository;
import com.boce.flcp.dao.UserRepository;
import com.boce.flcp.dao.UserSpecimenRepository;
import com.boce.flcp.domain.*;
import com.boce.flcp.domain.list.DemandList;
import com.boce.flcp.domain.list.DesignList;
import com.boce.flcp.domain.list.SpecimenList;
import com.boce.flcp.domain.list.UserSpecimenList;
import com.boce.flcp.util.Arith;
import com.boce.flcp.util.CommonUtils;
import com.boce.flcp.util.RedisUtils;
import org.apache.tomcat.jni.Poll;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class DemandService {
    private static final String KEY_DEMAND_SET = "demand:set:";//需求竞标信息
    private static final String KEY_DESIGN_SET = "design:set:";//设计信息
    private static final String KEY_SPECIMEN_SET = "specimen:set:";//打板信息
    private static final String KEY_IMAGE_LIST = "image:list:";
    private static final String KEY_ACCESSORYS_LIST = "as:list:";
    //需求管理
    private static final String DEMAND_STATUS_ISSUE = "issue";//已发布
    //设计管理
    private static final String DEMAND_STATUS_BID = "bid";//已中标 ：选中竞标人
    private static final String DEMAND_STATUS_AUDIT = "audit";//待审核 ：上传作品审核
    private static final String DEMAND_STATUS_REJECT = "reject";//已驳回 ：驳回作品
    private static final String DEMAND_STATUS_APPROVE = "approve";//已通过 ：批准作品，设计师选择打板师
    //打板管理
    private static final String DEMAND_STATUS_MAKING = "making";//待打板 ：打板师打板
    private static final String DEMAND_STATUS_AFFIRM = "affirm";// 设计师待确认
    private static final String DEMAND_STATUS_SEND = "send";//待送样 ：
    private static final String DEMAND_STATUS_SPECIMEN_AUDIT = "specimenAudit";// : 待审核
    private static final String DEMAND_STATUS_SPECIMEN_REJECT = "specimenReject";// ：已驳回
    private static final String DEMAND_STATUS_SPECIMEN_APPROVE = "specimenApprove";// ：已通过
    //使用
    private static final String DEMAND_STATUS_DONE = "done"; //已完成
    private static final String DEMAND_STATUS_USING = "using";//已启用

    @Autowired
    DemandRepository demandRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserSpecimenRepository userSpecimenRepository;

    /**
     * @Title: save
     * @Description: 需求发布
     * @Author xulovehua
     * @Date 2017/11/21 16:37
     * @Param [demand]
     * @return com.boce.flcp.domain.Unify
     */
    public Unify save(Demand demand){
        Unify u = new Unify();
        u.setCode("201");
        u.setMsg("发布失败");
        try {
            demand.setDemand_id(String.valueOf(System.currentTimeMillis()));
            demand.setDemand_petitive_user(0);
            demand.setDemand_status(DEMAND_STATUS_ISSUE);
            demand.setDemand_time(CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            demandRepository.save(demand);
            demandStatistics(demand.getDemand_money().toString());
            u.setCode("200");
            u.setMsg("发布成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    /**
     * @Title: demandStatistics
     * @Description: TODO  (需求预统计)
     * @Author xulovehua
     * @Date 2017/12/21 15:48
     * @Param []
     * @return void
     */
    public void demandStatistics(String money){
        String key = IndexService.KEY_DEMAND_STATISTICS + CommonUtils.getDate("yyyyMMdd");
        Map<Object,Object> map = redisUtils.getStringMap(key);
        if(map!=null && !map.isEmpty()){
            map.put("size",String.valueOf(Integer.valueOf(map.get("size").toString())+1));
            map.put("money",String.valueOf(Arith.add(Double.valueOf(money),Double.valueOf(map.get("money").toString()))));
        }else{
            map.put("size","1");
            map.put("money",money);
        }
        redisUtils.saveStringMap(key,map);
    }

    /**
     * @Title: findAll
     * @Description: 查询所有
     * @Author xulovehua
     * @Date 2017/11/21 16:37
     * @Param []
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public Iterable<Demand> findAll(){
        return demandRepository.findAll();
    }

    /**
     * @Title: findListByDemand
     * @Description: TODO  (查询需求列表)
     * @Author xulovehua
     * @Date 2017/12/6 15:03
     * @Param []
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public List<DemandList> findListByDemand(){
        return demandRepository.findListByDemand();
    }

    /**
     * @Title: findListByDesign
     * @Description: TODO  (查询设计列表)
     * @Author xulovehua
     * @Date 2017/12/6 15:04
     * @Param []
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public List<DesignList> findListByDesign(){return demandRepository.findListByDesign();}

    /**
     * @Title: findListBySpecimen
     * @Description: TODO  (查询打板列表)
     * @Author xulovehua
     * @Date 2017/12/6 15:04
     * @Param []
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public List<SpecimenList> findListBySpecimen(){return demandRepository.findListBySpecimen();}




    //设计

    /**
     * @Title: findMyDemandList
     * @Description: TODO  (用户查询设计需求列表,登录or未登录)
     * @Author xulovehua
     * @Date 2018/1/18 11:25
     * @Param []
     * @return java.util.List<com.boce.flcp.domain.list.DemandList>
     */
    public List<DemandList> findMyDemandList(){
        return demandRepository.findMyDemandList();
    }

    /**
     * @Title: findAllByUserId
     * @Description: TODO  (用户查询待设计需求)
     * @Author xulovehua
     * @Date 2017/11/27 10:14
     * @Param [user_id]
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public Iterable<Demand> getMyToDesignList(Long user_id){
        return demandRepository.getMyToDesignList(user_id);
    }

    /**
     * @Title: findMakingByUserId
     * @Description: TODO  (用户查询待处理需求)
     * @Author xulovehua
     * @Date 2017/12/26 14:37
     * @Param [user_id]
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public Iterable<Demand> getMyPendingList(Long user_id){
        return demandRepository.getMyPendingList(user_id);
    }

    /**
     * @Title: findUsingByUserId
     * @Description: TODO  (用户查询打板跟踪需求)
     * @Author xulovehua
     * @Date 2017/12/26 14:37
     * @Param [user_id]
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public Iterable<Demand> getMySpecimenTailList(Long user_id){
        return  demandRepository.getMySpecimenTailList(user_id);
    }

    /**
     * @Title: getMyDoneList
     * @Description: TODO  (用户查询打板跟踪需求)
     * @Author xulovehua
     * @Date 2017/12/26 14:37
     * @Param [user_id]
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public Iterable<Demand> getMyDoneList(Long user_id){
        return  demandRepository.getMyDoneList(user_id);
    }



    //打板

    /**
     * @Title: findListBySpecimen
     * @Description: TODO  (用户查询待打板列表)
     * @Author xulovehua
     * @Date 2017/12/6 15:04
     * @Param []
     * @return java.lang.Iterable<com.boce.flcp.domain.Demand>
     */
    public List<SpecimenList> findMyToSpecimenList(Long user_id){return demandRepository.findMyToSpecimenList(user_id);}

    /**
     * @Title: getDemandById
     * @Description: 根据需求ID查询需求
     * @Author xulovehua
     * @Date 2017/11/21 16:38
     * @Param [id]
     * @return com.boce.flcp.domain.Demand
     */
    public Demand getDemandById(Long id){
        Demand demand = demandRepository.findOne(id);
        demand.setRest_id(demand.getId());
        demand.setDesigns(getDemandDesignDetail(id));//组装设计明细
        demand.setSpecimens(getDemandSpecimenDetail(demand.getDesign_id()));//组装打板明细
        return demand;
    }

    /**
     * @Title: getDemandDesignDetail
     * @Description: TODO (获取需求设计明细)
     * @Author xulovehua
     * @Date 2017/11/23 16:23
     * @Param [demand_id]
     * @return com.boce.flcp.domain.Design
     */
    public List<Design> getDemandDesignDetail(Long demand_id){
        String key = KEY_DESIGN_SET+demand_id;
        List<Design> designList = new ArrayList<>();
        try{
            Set<Object> set =  redisUtils.getSet(key);
            for(Object object : set){
                Design design = (Design) object;
                if(CommonUtils.isNotBlank(design.getDesign_imgs())){
                    design.setImgs(analysisDesign(design.getDesign_imgs()));
                    design.setDesign_imgs("");
                }
                if(CommonUtils.isNotBlank(design.getDesign_accessorys())){
                    design.setAccessorys(analysisDesign(design.getDesign_accessorys()));
                    design.setDesign_accessorys("");
                }
                designList.add(design);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return designList;
    }

    /**
     * @Title: analysisDesign
     * @Description: TODO  (解析设计组装的数据)
     * @Author xulovehua
     * @Date 2017/11/24 11:03
     * @Param [strs]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    private List<File> analysisDesign(String strs){
        List<File> listMap = new ArrayList<>();
        if(strs.split("#").length>1){
            for(String str : strs.split("#")){
                File file = new File();
                file.setFile_name(str.split("%")[0]);
                file.setFile_url(str.split("%")[1]);
                listMap.add(file);
            }
        }else{
            if(CommonUtils.isNotBlank(strs)){
                File file = new File();
                file.setFile_name(strs.split("%")[0]);
                file.setFile_url(strs.split("%")[1]);
                listMap.add(file);
            }
        }
        return listMap;
    }

    /**
     * @Title: getDemandByIdAndUser
     * @Description: 根据需求ID和当前用户查询需求
     * @Author xulovehua
     * @Date 2017/11/22 11:16
     * @Param [id, user_id]
     * @return com.boce.flcp.domain.Demand
     */
    public Demand getDemandByIdAndUser(Long id,Long user_id){
        String key = KEY_DEMAND_SET+id;
        Set<Object> set = redisUtils.getSet(key);
        Demand demand = demandRepository.findOne(id);
        demand.setRest_id(demand.getId());
        if(set.contains(String.valueOf(user_id))){
            demand.setIs_petitive(true);
        }
        demand.setDesigns(getDemandDesignDetail(id));//组装设计明细
        demand.setSpecimens(getDemandSpecimenDetail(demand.getDesign_id()));//组装打板明细
        return demand;
    }

    /**
     * @Title: getDemandSpecimenDetail
     * @Description: TODO (获取需求打板明细)
     * @Author xulovehua
     * @Date 2017/11/23 16:23
     * @Param [demand_id]
     * @return com.boce.flcp.domain.Design
     */
    public List<Specimen> getDemandSpecimenDetail(String design_id){
        String key = KEY_SPECIMEN_SET+design_id;
        List<Specimen> specimenList = new ArrayList<>();
        try{
            Set<Object> set =  redisUtils.getSet(key);
            for(Object object : set){
                Specimen specimen = (Specimen) object;
                if(CommonUtils.isNotBlank(specimen.getSpecimen_imgs())){
                    specimen.setImgs(analysisDesign(specimen.getSpecimen_imgs()));
                    specimen.setSpecimen_imgs("");
                }
                specimenList.add(specimen);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return specimenList;
    }


    /**
     * @Title: applyPetitiveUser
     * @Description: 用户进行投标
     * @Author xulovehua
     * @Date 2017/11/21 16:38
     * @Param [demand_id, user_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional //修改或删除是需要添加事务注解
    public Unify applyPetitiveUser(Long demand_id, Long user_id){
        Unify u = new Unify();
        u.setCode("201");
        u.setMsg("招标失败");

        String key = KEY_DEMAND_SET+demand_id;
        boolean result = redisUtils.saveSet(key,String.valueOf(user_id));
        if(result){
            //写入sql表
            Long size = redisUtils.getSetSize(key);
            try{
                demandRepository.updatePetitiveQuery(demand_id,size.intValue());
                u.setCode("200");
                u.setMsg("招标成功");
            }catch (Exception e){
                e.printStackTrace();
                return u;
            }
        }

        return u;
    }

    /**
     * @Title: petitiveUsers
     * @Description: 根据需求id获取竞标用户信息
     * @Author xulovehua
     * @Date 2017/11/21 16:39
     * @Param [demand_id]
     * @return java.util.List<com.boce.flcp.domain.PetitiveUser>
     */
    public List<PetitiveUser> petitiveUsers(Long demand_id){
        String key = KEY_DEMAND_SET+demand_id;
        Set<Object> set = redisUtils.getSet(key);
        Set<Long> ids = new HashSet<>();
        for(Object id : set){
            ids.add(Long.valueOf(id.toString()));
        }
        List<User> userList = userRepository.findByIdIn(ids);
        List<PetitiveUser> petitiveUserList = new ArrayList<>();
        for(User user : userList){
            PetitiveUser petitiveUser = new PetitiveUser();
            petitiveUser.setDemand_id(demand_id);
            petitiveUser.setUser_id(user.getId());
            petitiveUser.setUser_photo_size(0);
            petitiveUser.setUser_name(user.getName());
            petitiveUser.setUser_phone(user.getAccount());
            petitiveUserList.add(petitiveUser);
        }
        return petitiveUserList;
    }

    /**
     * @Title: selectBidWinner
     * @Description: 选择竞标人
     * @Author xulovehua
     * @Date 2017/11/21 16:39
     * @Param [demand_id, user_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify selectBidWinner(Long demand_id,Long user_id){
        Unify u = new Unify();
        u.setCode("201");
        u.setMsg("中标失败");
        try{
             User user = userRepository.findOne(user_id);
             if(user!=null){
                 demandRepository.updateBidWinnerQuery(demand_id,user_id,user.getName(),CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"),DEMAND_STATUS_BID);
                 u.setCode("200");
                 u.setMsg("中标成功");
             }
        }catch (Exception e){
            e.printStackTrace();
            return u;
        }
        return u;
    }

    /**
     * @Title: submitDesign
     * @Description: 设计师提交设计
     * @Author xulovehua
     * @Date 2017/11/21 17:19
     * @Param [design]
     * @return void
     */
    @Transactional
    public Unify submitDesign(Design design){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("提交失败");
        String key = KEY_DESIGN_SET+design.getDemand_id();
        design.setDesign_time(CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        design.setId(String.valueOf(System.currentTimeMillis()));
        try{
            redisUtils.saveSet(key,design);
            relevanceUserDesign(design);//用户关联上传信息
            demandRepository.updateStatusQuery(design.getDemand_id(),DEMAND_STATUS_AUDIT);
            unify.setCode("200");
            unify.setMsg("提交成功");
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }

    /**
     * @Title: submitDesign
     * @Description: 打板师提交打板
     * @Author xulovehua
     * @Date 2017/11/21 17:19
     * @Param [design]
     * @return void
     */
    @Transactional
    public Unify submitSpecimen(Specimen specimen){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("提交失败");
        String key = KEY_SPECIMEN_SET+specimen.getDesign_id();
        specimen.setSpecimen_time(CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        specimen.setId(String.valueOf(System.currentTimeMillis()));
        try{
            redisUtils.saveSet(key,specimen);
            relevanceUserSpecimen(specimen);//用户关联打板信息
            demandRepository.updateStatusQuery(specimen.getDemand_id(),DEMAND_STATUS_AFFIRM);
            unify.setCode("200");
            unify.setMsg("提交成功");
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }
    /**
     * @Title: affirmSpecimen
     * @Description: TODO  设计师确认打板
     * @Author xulovehua
     * @Date 2018/1/3 14:58
     * @Param [demand_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify affirmSpecimen(long demand_id,String specimen_id){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("确定失败");
        try {
            demandRepository.updateStatusQuery(demand_id,DEMAND_STATUS_SEND);
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }

    /**
     * @Title: doneSendSpecimen
     * @Description: TODO  后台确认收样
     * @Author xulovehua
     * @Date 2018/1/3 14:58
     * @Param [demand_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify doneSendSpecimen(long demand_id){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("收样失败");
        try {
            demandRepository.updateStatusQuery(demand_id,DEMAND_STATUS_SPECIMEN_AUDIT);
            unify.setCode("200");
            unify.setMsg("收样成功");
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }

    /**
     * @Title: relevanceUserDesign
     * @Description: TODO  (用户关联上传作品)
     * @Author xulovehua
     * @Date 2017/12/26 14:00
     * @Param [user_id, imgs]
     * @return void
     */
    private void relevanceUserDesign(Design design){
        String img_key = KEY_IMAGE_LIST + design.getUser_id();
        String accessorys_key = KEY_ACCESSORYS_LIST + design.getUser_id();
        try {
            redisUtils.saveList(img_key,design.getDesign_imgs());
            redisUtils.saveList(accessorys_key,design.getDesign_accessorys());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Title: relevanceUserDesign
     * @Description: TODO  (用户关联打板信息)
     * @Author xulovehua
     * @Date 2017/12/26 14:00
     * @Param [user_id, imgs]
     * @return void
     */
    private void relevanceUserSpecimen(Specimen specimen){
        String img_key = KEY_IMAGE_LIST + specimen.getSpecimen_user_id();
        try {
            redisUtils.saveList(img_key,specimen.getSpecimen_imgs());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Title: designAudit
     * @Description: TODO (审核设计)
     * @Author xulovehua
     * @Date 2017/11/23 14:21
     * @Param [demand_id,design_id,status]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify designAudit(Long demand_id,String design_id,String status){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("审核驳回");
        String key = KEY_DESIGN_SET+demand_id;
        try{
            //通过
            if(status.equals(DEMAND_STATUS_APPROVE)){
                    Set<Object> set = redisUtils.getSet(key);
                    for(Object d : set){
                        Design design = (Design) d;
                        if(design_id.equals(design.getId())){
                            demandRepository.updateDesignQuery(design.getDemand_id(),design.getId(),design.getDesign_imgs(),
                                    design.getDesign_describe(),design.getDesign_accessorys(),status,
                                    design.getDesign_time(),CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                            unify.setCode("200");
                            unify.setMsg("审核通过");
                            break;
                        }
                    }
            }else{
                //驳回
                demandRepository.updateStatusQuery(demand_id,DEMAND_STATUS_REJECT);
                return unify;
            }
        }catch (Exception e){
            e.printStackTrace();
            unify.setCode("202");
            unify.setMsg("审核失败");
            return unify;
        }
        return null;
    }

    /**
     * @Title: userSpecimenList
     * @Description: TODO  (打板师iframe列表)
     * @Author xulovehua
     * @Date 2017/12/12 15:38
     * @Param []
     * @return com.boce.flcp.domain.list.UserSpecimenList
     */
    public UserSpecimenList userSpecimenList(){
        UserSpecimenList userSpecimenList = new UserSpecimenList();
        List<UserSpecimen> userSpecimens = userSpecimenRepository.findAll();
        userSpecimenList.setCode(0);
        userSpecimenList.setCount(userSpecimens.size());
        userSpecimenList.setMsg("");
        userSpecimenList.setData(userSpecimens);
        return userSpecimenList;
    }

    /**
     * @Title: selectSpecimenUser
     * @Description: TODO  (选中打板师)
     * @Author xulovehua
     * @Date 2017/12/12 15:40
     * @Param [demand_id, specimen_user_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify selectSpecimenUser(Long demand_id,Long specimen_user_id){
        System.out.println("需求id:"+demand_id+"   打板师id:"+demand_id);
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("选中失败");
        UserSpecimen userSpecimen = userSpecimenRepository.findOne(specimen_user_id);
        try{
            if(userSpecimen != null){
                demandRepository.updateSpecimenUserQuery(demand_id,DEMAND_STATUS_MAKING,specimen_user_id,userSpecimen.getName());
                unify.setCode("200");
                unify.setMsg("选中成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }


    /**
     * @Title: specimenAudit
     * @Description: TODO (审核打板)
     * @Author xulovehua
     * @Date 2017/11/23 14:21
     * @Param [demand_id,specimen_id,status]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify specimenAudit(Long demand_id,String specimen_id,String status){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("审核驳回");
        String key = KEY_SPECIMEN_SET+specimen_id;
        try{
            //通过
            if(status.equals(DEMAND_STATUS_SPECIMEN_APPROVE)){
                Set<Object> set = redisUtils.getSet(key);
                for(Object d : set){
                    Specimen specimen = (Specimen) d;
                    if(specimen_id.equals(specimen.getId())){
                        demandRepository.updateSpecimenQuery(specimen.getDemand_id(),specimen.getId(),specimen.getSpecimen_imgs(),
                                specimen.getSpecimen_describe(),status,
                                specimen.getSpecimen_time(),CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
                        unify.setCode("200");
                        unify.setMsg("审核通过");
                        break;
                    }
                }
            }else{
                //驳回
                demandRepository.updateStatusQuery(demand_id,DEMAND_STATUS_SPECIMEN_REJECT);
                return unify;
            }
        }catch (Exception e){
            e.printStackTrace();
            unify.setCode("202");
            unify.setMsg("审核失败");
            return unify;
        }
        return null;
    }


    /**
     * @Title: usingDemandDesign
     * @Description: TODO  (启用需求设计)
     * @Author xulovehua
     * @Date 2017/12/13 16:17
     * @Param [demand_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify usingDemandDesign(Long demand_id){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("启用失败");
        try{
            demandRepository.updateStatusQuery(demand_id,DEMAND_STATUS_USING);
            unify.setCode("200");
            unify.setMsg("启用成功");
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }

    /**
     * @Title: doneDemandDesign
     * @Description: TODO  (完成需求设计)
     * @Author xulovehua
     * @Date 2017/12/13 16:17
     * @Param [demand_id]
     * @return com.boce.flcp.domain.Unify
     */
    @Transactional
    public Unify doneDemandDesign(Long demand_id){
        Unify unify = new Unify();
        unify.setCode("201");
        unify.setMsg("操作失败");
        try{
            demandRepository.updateStatusQuery(demand_id,DEMAND_STATUS_DONE);
            unify.setCode("200");
            unify.setMsg("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return unify;
        }
        return unify;
    }
}
