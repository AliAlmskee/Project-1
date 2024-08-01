package com.project1.profile;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkerProfileMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "jobTitle", target = "jobTitleDTO")
    @Mapping(source = "photos", target = "photoDTOs")
    @Mapping(source = "videos", target = "videoDTOs")
    @Mapping(source = "docs", target = "docDTOs")
    @Mapping(source = "skills", target = "skillDTOs")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "category", target = "categoryDTO")
    WorkerProfileDTO toDto(WorkerProfile workerProfile);

    WorkerProfile toEntity(WorkerProfileDTO workerProfileDTO);
    WorkerProfile toEntity(WorkerProfileRequest workerProfileRequest);

    default Long getId(WorkerProfile workerProfile){
        return workerProfile.getId();
    }
    default WorkerProfile map(Long id){
        return WorkerProfile.builder().id(id).build();
    }
}