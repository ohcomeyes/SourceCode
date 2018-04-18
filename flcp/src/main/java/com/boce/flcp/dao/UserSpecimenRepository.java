package com.boce.flcp.dao;

import com.boce.flcp.domain.User;
import com.boce.flcp.domain.UserSpecimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.dao
 * @Description: TODO
 * @date 2017/12/7 16:34
 */
@RepositoryRestResource(path ="userSpecimen")
public interface UserSpecimenRepository extends JpaRepository<UserSpecimen,Long> {


    @Query("select u from UserSpecimen u where u.account= :account")
    UserSpecimen withAccountQuery(@Param("account")String account);
}
