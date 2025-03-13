package com.td005.jobportal.services;

import com.td005.jobportal.entity.*;
import com.td005.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity)
    {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter)
    {
        List<IRecruiterJob> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for(IRecruiterJob rec:recruiterJobsDtos)
        {
            JobLocation loc = new JobLocation(rec.getLocation(),rec.getCity(),rec.getState(),rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(),rec.getName(),"");

            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(),rec.getJob_pot_id(),rec.getJob_title(),loc,comp));

        }
        return recruiterJobsDtoList;



    }


    public JobPostActivity getOne(int id) {



        return jobPostActivityRepository.findById(id).orElseThrow(()-> new RuntimeException("Job not found" ));

    }
}
