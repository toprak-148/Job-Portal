package com.td005.jobportal.controller;

import com.td005.jobportal.entity.*;
import com.td005.jobportal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class JobSeekerApplyController {
    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final RecruiterProfileService recruiterProfileService;
    private final JobSeekerProfileService jobSeekerProfileService;

    @Autowired
    public JobSeekerApplyController(JobPostActivityService jobPostActivityService,
                                    UsersService usersService,
                                    JobSeekerApplyService jobSeekerApplyService,
                                    JobSeekerSaveService jobSeekerSaveService,
                                    RecruiterProfileService recruiterProfileService,
                                    JobSeekerProfileService jobSeekerProfileService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.recruiterProfileService = recruiterProfileService;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }




    @GetMapping("job-details-apply/{id}")

    public String display(@PathVariable("id") int id , Model model)
    {
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);
        List<JobSeekerApply> jobSeekerApplies = jobSeekerApplyService.getJobCandidates(jobDetails);
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getJobCandidates(jobDetails);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken))
        {
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter")))
            {
                RecruiterProfile user = recruiterProfileService.getCurrenRecruiterProfile();
                if(user != null)
                {
                    model.addAttribute("applyList",jobSeekerApplies);
                }

            }else {
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileService.getCurrentSeekerProfile();
                if(jobSeekerProfile != null)
                {
                    boolean exists = false;
                    boolean saved = false;
                    for(JobSeekerApply jobSeekerApply : jobSeekerApplies)
                    {
                        if(jobSeekerApply.getUserId().getuserAccountId() ==  jobSeekerProfile.getuserAccountId()){
                             exists = true;
                             break;
                        }
                    }
                    for(JobSeekerSave jobSeekerSave : jobSeekerSaveList)
                    {
                        if(jobSeekerSave.getUserId().getuserAccountId() == jobSeekerProfile.getuserAccountId())
                        {
                            saved = true;
                            break;
                        }
                    }
                    model.addAttribute("alreadyApplied",exists);
                    model.addAttribute("alreadySaved",saved);

                }
            }
        }


        model.addAttribute("jobDetails",jobDetails);
        model.addAttribute("user",usersService.getCurrentUserProfile());

        return "job-details";
    }

    @PostMapping("job-details/apply/{id}")
    public String apply(@PathVariable("id") int id , JobSeekerApply jobSeekerApply)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof  AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersService.findByEmail(currentUsername);
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(users.getUserId());

            JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
            if(seekerProfile.isPresent() && jobPostActivity != null)
            {
                jobSeekerApply = new JobSeekerApply();
                jobSeekerApply.setUserId(seekerProfile.get());
                jobSeekerApply.setJob(jobPostActivity);
                jobSeekerApply.setApplyDate(new Date());
            }
            else throw  new RuntimeException("user not found");

            jobSeekerApplyService.addNew(jobSeekerApply);

        }

        return "redirect/dashboard/";
    }






}
