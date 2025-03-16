package com.td005.jobportal.services;

import com.td005.jobportal.entity.JobPostActivity;
import com.td005.jobportal.entity.JobSeekerApply;
import com.td005.jobportal.entity.JobSeekerProfile;

import java.util.List;

public interface JobSeekerApplyService {

    List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId);

    List<JobSeekerApply> getJobCandidates(JobPostActivity jobPostActivity);

    void addNew(JobSeekerApply jobSeekerApply);
}
