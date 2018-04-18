package com.boce.flcp.dao;

import com.boce.flcp.domain.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: flcp
 * @description: 规则DAO
 * @author: Mr.Tang
 * @create: 2018-03-28 15:15
 **/
public interface CostRepository extends JpaRepository<Cost,Long> {

}
