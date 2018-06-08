package com.boce.flcp.service;

import com.boce.flcp.api.xiaochengxu.entity.Superior;
import com.boce.flcp.dao.UserRepository;
import com.boce.flcp.dao.UserSpecimenRepository;
import com.boce.flcp.data.Adept;
import com.boce.flcp.data.DesignIndustry;
import com.boce.flcp.domain.*;
import com.boce.flcp.sms.SmsService;
import com.boce.flcp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    //1 Spring Data JPA已自动为你注册bean，所以可自动注入
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSpecimenRepository userSpecimenRepository;

    /**
     * 发送验证码
     * @param account
     * @return
     */
    public Validate validateAndSms(String account){
        Validate v = new Validate();
        User u = userRepository.withAccountQuery(account);
        if(u==null){
            String validate_id = (int)((Math.random()*9+1)*100000)+"";
            SmsService.sendSmg(account,validate_id);
            v.setCode("200");
            v.setValidate_id(validate_id);
            v.setMsg("验证码已发送");
        }else{
            v.setCode("201");
            v.setMsg("帐号已存在");
        }
        return v;
    }

    /**
     * @Title: designRegister
     * @Description: TODO (设计师注册)
     * @Author xulovehua
     * @Date 2018/1/18 10:49
     * @Param [user]
     * @return com.boce.flcp.domain.Unify
     */
    public Unify designRegister(User user){
        Unify u = new Unify();
        try {
            user.setTime(CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            user.setPassword(CommonUtils.EncoderByMd5(user.getPassword()));
            userRepository.save(user);
            u.setCode("200");
            u.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            u.setCode("201");
            u.setMsg("密码异常");
        }
        return u;
    }

    /**
     * @Title: designLogin
     * @Description: TODO (设计师登录)
     * @Author xulovehua
     * @Date 2018/1/18 10:49
     * @Param [account, password]
     * @return com.boce.flcp.domain.Content
     */
    public Content designLogin(String account, String password){
        Content content = new Content();
        User user = userRepository.withAccountQuery(account);
        try {
            if(user != null){
                if(CommonUtils.checkpassword(password,user.getPassword())){
                    content.setCode("200");
                    content.setMsg("登录成功");
                    content.setObject(user);
                }else{
                    content.setCode("201");
                    content.setMsg("账号与密码不匹配");
                }
            }else{
                content.setCode("202");
                content.setMsg("用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            content.setCode("203");
            content.setMsg("登录失败，请联系系统管理员");
        }
        return content;
    }

    /**
     * @Title: specimenRegister
     * @Description: TODO (打板师注册)
     * @Author xulovehua
     * @Date 2018/1/18 10:51
     * @Param [userSpecimen]
     * @return com.boce.flcp.domain.Unify
     */
    public Unify specimenRegister(UserSpecimen userSpecimen){
        Unify u = new Unify();
        try{
            userSpecimen.setPassword(CommonUtils.EncoderByMd5(userSpecimen.getPassword()));
            userSpecimen.setTime(CommonUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            userSpecimenRepository.save(userSpecimen);
            u.setCode("200");
            u.setMsg("注册成功");
        }catch (Exception e){
            u.setCode("201");
            u.setMsg("密码异常");
            e.printStackTrace();
        }
        return u;
    }

    /**
     * @Title: specimenLogin
     * @Description: TODO  (打板师登录)
     * @Author xulovehua
     * @Date 2018/1/18 10:55
     * @Param [account, password]
     * @return com.boce.flcp.domain.Content
     */
    public Content specimenLogin(String account, String password){
        Content content = new Content();
        try {
            UserSpecimen userSpecimen = userSpecimenRepository.withAccountQuery(account);
            if(userSpecimen != null){
                if(CommonUtils.checkpassword(password,userSpecimen.getPassword())){
                    content.setCode("200");
                    content.setMsg("登录成功");
                    content.setObject(userSpecimen);
                }else{
                    content.setCode("201");
                    content.setMsg("账户名与密码不匹配");
                }
            }else{
                content.setCode("202");
                content.setMsg("用户不存在");
            }

        }catch (Exception e){
            content.setCode("203");
            content.setMsg("登录失败，请联系系统管理员");
        }
        return content;
    }

    /**
     * 查询设计师列表
     * @return
     */
    public Collection<User> getUserList(){
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            String design_industry = user.getDesign_industry();
            for(String industry : design_industry.split("#")){
                user.getIndustrys().add(DesignIndustry.getName(Integer.valueOf(industry)));
            }
        }
        return userRepository.findAll();
    }

    /**
     * 根据用户Id查询用户信息
     * @param id
     * @return
     */
    public User getUserById(Long id){
        return userRepository.findOne(id);
    }


    /**
     * @Title: getUserSpecimen
     * @Description: TODO
     * @Author xulovehua
     * @Date 2017/12/28 14:28
     * @Param [id]
     * @return com.boce.flcp.domain.UserSpecimen
     */
    public UserSpecimen getUserSpecimen(Long id){
        UserSpecimen userSpecimen = userSpecimenRepository.findOne(id);
        String adept = userSpecimen.getAdept();
        List<Skill> skillList = new ArrayList<>();
        for (Adept c : Adept.values()) {
            Skill skill = new Skill();
            skill.setId(String.valueOf(c.getIndex()));
            skill.setChecked(false);
            if(CommonUtils.isNotBlank(adept)){
                String[] adepts = adept.split("#");
                if(Arrays.binarySearch(adepts,String.valueOf(c.getIndex())) >= 0){//二分查找需要排序
                    skill.setChecked(true);
                }
            }
            skill.setName(c.getName());
            skillList.add(skill);
        }
        userSpecimen.setSkillList(skillList);
        return userSpecimen;
    }

    /**
     * @Title: getAllUserSpecimen
     * @Description: TODO
     * @Author xulovehua
     * @Date 2017/12/28 14:37
     * @Param []
     * @return java.util.List<com.boce.flcp.domain.UserSpecimen>
     */
    public List<UserSpecimen> getAllUserSpecimen(){
        return userSpecimenRepository.findAll();
    }






    /**xiaochengxu*/

    public Page<Superior> getSuperiorList(int size, int page){
        return userRepository.getSuperiorList(new PageRequest(page,size));
    }

}
