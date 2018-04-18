package com.boce.flcp.dao;

import com.boce.flcp.domain.Demand;
import com.boce.flcp.domain.Person;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource( path = "people")
public interface PersonRepository extends JpaRepository<Person, Long> {

@ApiOperation(value = "获取需求列表",notes = "查看所有需求信息",response = Person.class)
@RestResource(path = "nameStartsWith",rel = "nameStartsWith")
Person findByNameStartsWith(@Param("name")String name);
}

