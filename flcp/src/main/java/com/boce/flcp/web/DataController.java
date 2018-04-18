package com.boce.flcp.web;

import com.boce.flcp.dao.DemandRepository;
import com.boce.flcp.domain.Demand;
import com.boce.flcp.util.RedisUtils;
import org.apache.tomcat.jni.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/caches")
public class DataController {


//    @Autowired
//    DemandRepository demandRepository;
//    @Autowired
//    RedisUtils redisUtils;
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @RequestMapping("/test")
//    public String  test(){
//        //redisUtils.set("123", "hello world");
//        System.out.println("进入了方法"+redisUtils.get("123").toString());
//        Set<String> set = new HashSet<>();
//        set.add("set1");
//        set.add("set2");
//        set.add("set3");
//        set.add("set4");
//        SetOperations<String, Set> vo = redisTemplate.opsForSet();
//        vo.add("set1",set);
//        System.out.println(redisTemplate.opsForSet().members("set1"));
//        return "成功";
//    }
//
//    @RequestMapping(value="/polls", method= RequestMethod.GET)
//    public ResponseEntity<Iterable<Demand>> getAllPolls() {
//        Iterable<Demand> allPolls = demandRepository.findAll();
//        for(Demand p : allPolls) {
//            updatePollResourceWithLinks(p);
//        }
//        return new ResponseEntity<>(allPolls, HttpStatus.OK);
////        return new ResponseEntity<Iterable<Poll>>(allPolls,HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
//    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
//        Demand p = demandRepository.findOne(pollId);
//        updatePollResourceWithLinks(p);
//        return new ResponseEntity<> (p, HttpStatus.OK);
//    }
//
//    private void updatePollResourceWithLinks(Demand demand) {
////        demand.add(linkTo(methodOn(DataController.class).getAllPolls()).slash("1").withSelfRel());
////        poll.add(linkTo(methodOn(VoteController.class).getAllVotes(poll.getPollId())).withRel("votes"));
////        poll.add(linkTo(methodOn(ComputeResultController.class).computeResult(poll.getPollId())).withRel("compute-result"));
//    }
}
