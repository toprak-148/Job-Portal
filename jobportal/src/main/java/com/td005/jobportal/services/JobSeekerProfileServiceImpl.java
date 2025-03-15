package com.td005.jobportal.services;


import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileServiceImpl implements  JobSeekerProfileService{

    private JobSeekerProfileRepository jobSeekerProfileRepository;
    public JobSeekerProfileServiceImpl(JobSeekerProfileRepository jobSeekerProfileRepository)
    {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }
    @Override
    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    @Override
    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return  jobSeekerProfileRepository.save(jobSeekerProfile);
        
    }
}
