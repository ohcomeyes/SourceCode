package com.boce.flcp.dao;

import com.boce.flcp.api.xiaochengxu.entity.WebDemand;
import com.boce.flcp.domain.Demand;
import com.boce.flcp.domain.list.DemandList;
import com.boce.flcp.domain.list.DesignList;
import com.boce.flcp.domain.list.SpecimenList;
import com.boce.flcp.domain.model.DemandAnalyze;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//@RepositoryRestResource(path = "demands")
public interface DemandRepository extends JpaRepository<Demand,Long> {
        @RestResource(path = "phone",rel = "phone")
        Demand findByPhoneStartsWith(@Param("phone") String phone);

        /**小程序web列表*/
        @Query("select new com.boce.flcp.api.xiaochengxu.entity.WebDemand(d.id,d.demand_employer,d.demand_name,d.demand_money,d.demand_describe,d.end_date) from Demand d order by d.demand_time asc")
        Page<WebDemand> getWebList(Pageable pageable);

        //hql不支持UNION连接查询，在in或者or查询遇到瓶颈时需要优化这段
        /**后台列表 start**/
        @Query("select  new com.boce.flcp.domain.list.DemandList(d.id,d.demand_id,d.demand_name,d.demand_employer,d.phone,d.demand_trade,d.demand_type,d.demand_money,d.demand_describe,d.start_date,d.end_date,d.demand_status,d.demand_making_send,d.demand_time,d.demand_petitive_user) " +
                " from  Demand d  where d.demand_status in ('issue','done','using') order by d.demand_time desc")
        List<DemandList> findListByDemand();

        @Query("select new com.boce.flcp.domain.list.DesignList(d.id,d.demand_id,d.demand_name,d.demand_employer,d.phone,d.demand_type,d.demand_money,d.demand_describe,d.classify_type,d.demand_bid_winner,d.demand_bid_winner_name,d.demand_bid_winner_time,d.demand_status,d.demand_time,d.demand_making_send) " +
                " from  Demand d  where d.demand_status in ('bid','audit','approve','reject') order by d.demand_bid_winner_time desc")
        List<DesignList> findListByDesign();

        @Query("select new com.boce.flcp.domain.list.SpecimenList(d.id,d.demand_id,d.demand_name,d.demand_employer,d.phone,d.demand_type,d.demand_money,d.demand_describe,d.classify_type,d.demand_bid_winner,d.demand_bid_winner_name,d.demand_status,d.demand_time,d.design_time,d.demand_making_send) " +
                " from  Demand d  where d.demand_status in ('making','affirm','specimenAudit','specimenReject','specimenApprove','send') order by d.design_time desc")
        List<SpecimenList> findListBySpecimen();
        /**后台列表 end**/

        /**用户列表 start*/
        @Query("select  new com.boce.flcp.domain.list.DemandList(d.id,d.demand_id,d.demand_name,d.demand_employer,d.phone,d.demand_trade,d.demand_type,d.demand_money,d.demand_describe,d.start_date,d.end_date,d.demand_status,d.demand_making_send,d.demand_time,d.demand_petitive_user) " +
                " from  Demand d  where d.demand_status ='issue' order by d.demand_time desc")
        List<DemandList> findMyDemandList();

        @Query("select new com.boce.flcp.domain.list.SpecimenList(d.id,d.demand_id,d.demand_name,d.demand_employer,d.phone,d.demand_type,d.demand_money,d.demand_describe,d.classify_type,d.demand_bid_winner,d.demand_bid_winner_name,d.demand_status,d.demand_time,d.design_time,d.demand_making_send) " +
                " from  Demand d  where d.demand_status in ('making','affirm','specimenApprove') and d.specimen_user_id=?1 order by d.design_time desc")
        List<SpecimenList> findMyToSpecimenList(@Param("specimen_user_id") Long user_id);

        @Query("select d  from  Demand d  where d.demand_bid_winner= ?1 and d.demand_status in ('bid','audit','reject')")
        List<Demand> getMyToDesignList(@Param("demand_bid_winner") Long user_id);

        @Query("select d  from  Demand d  where d.demand_bid_winner= ?1 and d.demand_status = 'approve'")
        List<Demand> getMyPendingList(@Param("demand_bid_winner") Long user_id);

        @Query("select d  from  Demand d  where d.demand_bid_winner= ?1 and d.demand_status in ('making','affirm','send','specimenAudit','specimenReject','specimenApprove')")
        List<Demand> getMySpecimenTailList(@Param("demand_bid_winner") Long user_id);

        @Query("select d  from  Demand d  where d.demand_bid_winner= ?1 and d.demand_status in ('done','using')")
        List<Demand> getMyDoneList(@Param("demand_bid_winner") Long user_id);
        /**用户列表 end*/

        /**业务分析 start**/
        @Query("select count(d)as demand_count,sum(d.demand_money)as demand_money from  Demand d")
        Map findDemandTotal();
        //首页数据统计分析，待优化
        @Query("select d.demand_time  from  Demand d  where d.demand_status= 'issue' order by d.design_time asc")
        LinkedList<String> findPendingByDemand();

        @Query("select d.design_time  from  Demand d  where d.demand_status= 'audit' order by d.design_time asc")
        LinkedList<String> findPendingByDesign();

        @Query("select d.design_audit_time  from  Demand d  where d.demand_status= 'making' order by d.design_audit_time asc")
        LinkedList<String> findPendingBySpecimen();

        @Query("select count(d)as size,sum(d.demand_money) as money,count(d.demand_bid_winner)as winner  from  Demand d  where d.demand_time>=?1 and d.demand_time<=?2")
        Map findCountDemandTable(@Param("begin_time")String begin_time,@Param("end_time")String end_time);

        @Query(value = "select top 5 d.demand_name,d.demand_describe,d.demand_petitive_user from demand d order by d.demand_petitive_user desc",nativeQuery=true)
        List<Object> findTopFiveDemand();
        /**业务分析 end**/

        /**业务操作 start**/
        //可以通过自定义的 JPQL 完成 UPDATE 和 DELETE 操作。 注意： JPQL 不支持使用 INSERT
        @Modifying
        @Query("update Demand d set d.demand_petitive_user=?2 where d.id= ?1")
        void updatePetitiveQuery(@Param("id")Long id,@Param("demand_petitive_user")int demand_petitive_user);

        @Modifying
        @Query("update Demand d set d.demand_bid_winner=?2,d.demand_bid_winner_name=?3,d.demand_bid_winner_time=?4,d.demand_status=?5 where d.id= ?1")
        void updateBidWinnerQuery(@Param("id")Long id,@Param("demand_bid_winner")Long demand_bid_winner,@Param("demand_bid_winner_name")String demand_bid_winner_name,@Param("demand_bid_winner_time")String demand_bid_winner_time,@Param("demand_status")String demand_status);

        @Modifying
        @Query("update Demand d set d.design_id=?2, d.design_imgs=?3,d.design_describe=?4,d.design_accessorys=?5,d.demand_status=?6,d.design_time=?7,d.design_audit_time=?8 where d.id= ?1")
        void updateDesignQuery(@Param("id")Long id,@Param("design_id")String design_id,@Param("design_imgs")String design_imgs,@Param("design_describe")String design_describe,@Param("design_accessorys")String design_accessorys,@Param("demand_status")String demand_status,@Param("design_time")String design_time,@Param("design_audit_time")String design_audit_time);

        @Modifying
        @Query("update Demand d set d.demand_status=?2,d.specimen_user_id=?3,d.specimen_user_name=?4 where d.id= ?1")
        void updateSpecimenUserQuery(@Param("id")Long id,@Param("demand_status")String demand_status,@Param("specimen_user_id")Long specimen_user_id,@Param("specimen_user_name")String specimen_user_name);

        @Modifying
        @Query("update Demand d set d.specimen_id=?2, d.specimen_imgs=?3,d.specimen_describe=?4,d.demand_status=?5,d.specimen_time=?6,d.specimen_audit_time=?7 where d.id= ?1")
        void updateSpecimenQuery(@Param("id")Long id,@Param("specimen_id")String specimen_id,@Param("specimen_imgs")String specimen_imgs,@Param("specimen_describe")String specimen_describe,@Param("demand_status")String demand_status,@Param("specimen_time")String specimen_time,@Param("specimen_audit_time")String specimen_audit_time);

        @Modifying
        @Query("update Demand d set d.demand_status=?2 where d.id= ?1")
        void updateStatusQuery(@Param("id")Long id,@Param("demand_status")String demand_status);
        /**业务操作 end**/

}
