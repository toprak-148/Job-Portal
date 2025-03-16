package com.td005.jobportal.services;

import com.td005.jobportal.entity.JobPostActivity;
import com.td005.jobportal.entity.JobSeekerApply;
import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyServiceImpl implements JobSeekerApplyService {
    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyServiceImpl(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    @Override
    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId) {
        return this.jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    @Override
    public List<JobSeekerApply> getJobCandidates(JobPostActivity jobPostActivity) {
        return this.jobSeekerApplyRepository.findByJob(jobPostActivity);
    }

    @Override
    public void addNew(JobSeekerApply jobSeekerApply) {
        jobSeekerApplyRepository.save(jobSeekerApply);

    }
}
