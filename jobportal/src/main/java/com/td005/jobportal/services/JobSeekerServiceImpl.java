package com.td005.jobportal.services;

import com.td005.jobportal.entity.JobPostActivity;
import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.entity.JobSeekerSave;
import com.td005.jobportal.repository.JobSeekerSaveRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerServiceImpl implements JobSeekerSaveService{
    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerServiceImpl(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    @Override
    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccount) {
        return this.jobSeekerSaveRepository.findByUserId(userAccount);
    }

    @Override
    public List<JobSeekerSave> getJobCandidates(JobPostActivity job) {
        return this.jobSeekerSaveRepository.findByJob(job);
    }

    @Override
    public void addNew(JobSeekerSave jobSeekerSave)
    {
        jobSeekerSaveRepository.save(jobSeekerSave);

    }
}
