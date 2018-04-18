package com.boce.flcp.dao;

import com.boce.flcp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
    //List<User> findByAccount(String account);
    //屏蔽自动化方法，防止暴露出来
    @RestResource(exported = false)
    @Override
    void delete(Long id);

    @RestResource(exported = false)
    @Override
    User findOne(Long aLong);

    @Query("select u from User u where u.account= :account")
    User withAccountQuery(@Param("account")String account);

    List<User> findByIdIn(@Param("ids") Collection<Long> ids);
}
