package com.td005.jobportal.services;

import com.td005.jobportal.entity.JobPostActivity;
import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.entity.JobSeekerSave;
import com.td005.jobportal.repository.JobSeekerSaveRepository;

import java.util.List;

public interface JobSeekerSaveService {

    List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccount);
    List<JobSeekerSave> getJobCandidates(JobPostActivity job);



}
