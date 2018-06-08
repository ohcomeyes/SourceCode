package com.boce.flcp.assembler;

import com.boce.flcp.domain.Demand;
import com.boce.flcp.resource.DemandResource;
import com.boce.flcp.web.DemandController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class DemandAssembler extends ResourceAssemblerSupport<Demand,DemandResource>{

    public DemandAssembler() {
        super(DemandController.class, DemandResource.class);
    }

    @Override
    public DemandResource toResource(Demand demand) {
        DemandResource resource = createResourceWithId(demand.getId(), demand);
        return resource;
    }

    @Override
    protected DemandResource instantiateResource(Demand entity) {
        return new DemandResource(entity);
    }


//    @Override
//    public BeanResource toResource(WebDemand demand) {
////        BeanResource beanResource = createResourceWithId(demand.getId(),demand);
//        BeanResource demandResource = new BeanResource(demand);
//        if(demand!=null){
//            try {
//                Link selfLink = linkTo(
//                        methodOn(DemandController.class).getDemandById(demand.getId()))
//                        .withSelfRel();
//                demandResource.add(selfLink);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return demandResource;
//    }

}
