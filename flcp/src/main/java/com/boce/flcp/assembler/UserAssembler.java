package com.boce.flcp.assembler;

import com.boce.flcp.domain.User;
import com.boce.flcp.resource.UserResource;
import com.boce.flcp.web.UserController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource resource = createResourceWithId(user.getId(), user);
        return resource;
    }

    @Override
    protected UserResource instantiateResource(User entity) {
        return new UserResource(entity);
    }
}
