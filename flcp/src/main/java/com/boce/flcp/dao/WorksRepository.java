package com.boce.flcp.dao;

import com.boce.flcp.domain.Works;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @program: flcp
 * @description: 作品
 * @author: Mr.Tang
 * @create: 2018-05-08 10:36
 **/
//@RepositoryRestResource( path = "work")
public interface WorksRepository extends JpaRepository<Works,Long>{

}
