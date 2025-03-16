package com.td005.jobportal.services;

import com.td005.jobportal.entity.JobSeekerProfile;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface JobSeekerProfileService {

    Optional<JobSeekerProfile> getOne(Integer id);

    JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile);


    JobSeekerProfile getCurrentSeekerProfile();

}
