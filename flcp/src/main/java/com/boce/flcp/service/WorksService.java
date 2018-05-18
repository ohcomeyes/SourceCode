package com.boce.flcp.service;

import com.boce.flcp.dao.WorksRepository;
import com.boce.flcp.domain.Works;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @program: flcp_official
 * @description: 作品
 * @author: Mr.Tang
 * @create: 2018-05-08 11:01
 **/
@Service
public class WorksService {
    @Autowired
    WorksRepository worksRepository;

    /***
     * 小程序首页作品
     * @param page
     * @param size
     * @return
     */
    public Page<Works> getWorks(int size,int page){
       return worksRepository.findAll(new PageRequest(page,size));
    }
}
