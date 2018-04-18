package com.boce.flcp.resource;

import com.boce.flcp.domain.Demand;
import com.boce.flcp.domain.User;
import com.boce.flcp.web.UserController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class UserResource extends Resource {

    public UserResource(User user) {
        super(user);
        user.setRest_id(user.getId());
        //这里增加链接
//        add(linkTo(methodOn(UserController.class).readList(user.getId())).withRel("items"));
    }
}
