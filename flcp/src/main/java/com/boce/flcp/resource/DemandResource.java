package com.boce.flcp.resource;

import com.boce.flcp.domain.Demand;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class DemandResource  extends Resource {

    public DemandResource(Demand demand) {
        super(demand);
        demand.setRest_id(demand.getId());
    }
}
