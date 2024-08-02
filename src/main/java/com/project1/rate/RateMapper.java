package com.project1.rate;

import com.project1.offer.data.CreateOfferRequest;
import com.project1.offer.data.Offer;
import com.project1.offer.data.OfferResponse;
import com.project1.offer.data.UpdateOfferRequest;
import com.project1.profile.ClientProfileMapper;
import com.project1.profile.WorkerProfile;
import com.project1.profile.WorkerProfileMapper;
import com.project1.project.data.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkerProfileMapper.class, ClientProfileMapper.class})
public abstract class RateMapper {
    @Mapping(source = "clientProfileId", target = "client")
    @Mapping(source = "workerProfileId", target = "worker")
    abstract Rate dtoToEntity(RateDTO dto);
    @Mapping(source = "clientProfileId", target = "client")
    @Mapping(source = "workerProfileId", target = "worker")
    abstract Rate createEntity(RateCreateDTO dto);
}

