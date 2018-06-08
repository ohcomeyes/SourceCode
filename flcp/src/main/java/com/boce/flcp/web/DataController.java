package com.boce.flcp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    public ResponseEntity<Iterable<WebDemand>> getAllPolls() {
//        Iterable<WebDemand> allPolls = demandRepository.findAll();
//        for(WebDemand p : allPolls) {
//            updatePollResourceWithLinks(p);
//        }
//        return new ResponseEntity<>(allPolls, HttpStatus.OK);
////        return new ResponseEntity<Iterable<Poll>>(allPolls,HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
//    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
//        WebDemand p = demandRepository.findOne(pollId);
//        updatePollResourceWithLinks(p);
//        return new ResponseEntity<> (p, HttpStatus.OK);
//    }
//
//    private void updatePollResourceWithLinks(WebDemand demand) {
////        demand.add(linkTo(methodOn(DataController.class).getAllPolls()).slash("1").withSelfRel());
////        poll.add(linkTo(methodOn(VoteController.class).getAllVotes(poll.getPollId())).withRel("votes"));
////        poll.add(linkTo(methodOn(ComputeResultController.class).computeResult(poll.getPollId())).withRel("compute-result"));
//    }
}
