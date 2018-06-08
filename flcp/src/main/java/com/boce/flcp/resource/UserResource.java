package com.boce.flcp.resource;

import com.boce.flcp.domain.User;
import org.springframework.hateoas.Resource;

public class UserResource extends Resource {

    public UserResource(User user) {
        super(user);
        user.setRest_id(user.getId());
        //这里增加链接
//        add(linkTo(methodOn(UserController.class).readList(user.getId())).withRel("items"));
    }
}
