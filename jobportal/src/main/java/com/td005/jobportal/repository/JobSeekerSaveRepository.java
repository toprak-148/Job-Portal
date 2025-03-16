package com.td005.jobportal.repository;

import com.td005.jobportal.entity.JobPostActivity;
import com.td005.jobportal.entity.JobSeekerApply;
import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave,Integer> {


    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
    List<JobSeekerSave> findByJob(JobPostActivity job);

}
