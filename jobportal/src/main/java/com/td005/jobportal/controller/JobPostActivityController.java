package com.td005.jobportal.controller;

import com.td005.jobportal.entity.JobPostActivity;
import com.td005.jobportal.entity.RecruiterJobsDto;
import com.td005.jobportal.entity.RecruiterProfile;
import com.td005.jobportal.entity.Users;
import com.td005.jobportal.services.JobPostActivityService;
import com.td005.jobportal.services.UsersService;
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

import java.util.Date;
import java.util.List;

@Controller
public class JobPostActivityController {
    private final UsersService usersService;
    private final JobPostActivityService jobPostActivityService;

    @Autowired
    public JobPostActivityController(UsersService usersService,
                                     JobPostActivityService jobPostActivityService) {
        this.usersService = usersService;
        this.jobPostActivityService = jobPostActivityService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model) {

        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("recruiter"))){
                List<RecruiterJobsDto> recruiterJobs = jobPostActivityService.getRecruiterJobs(((RecruiterProfile)
                currentUserProfile).getUserAccountId());
                model.addAttribute("jobPost",recruiterJobs);
            }
        }

        model.addAttribute("user", currentUserProfile);

        return "dashboard";
    }

    @GetMapping("/dashboard/add")
    public String addJobs(Model model)
    {
        model.addAttribute("jobPostActivity",new JobPostActivity());
        model.addAttribute("user",usersService.getCurrentUserProfile());

        return  "add-jobs";
    }

    @PostMapping("/dashboard/addNew")
    public String addNew(JobPostActivity jobPostActivity, Model model)
    {
        Users users = usersService.getCurrentUser();
        if(users!=null)
        {
             jobPostActivity.setPostedById((users));
        }
        jobPostActivity.setPostedDate(new Date());
        model.addAttribute("jobPostActivity",jobPostActivity);

        JobPostActivity savedJobPostActivity = jobPostActivityService.addNew(jobPostActivity);
        return "redirect:/dashboard/";
    }

    @PostMapping("dashboard/edit/{id}")
    public String editJob(@PathVariable("id") int id , Model model)
    {
       JobPostActivity jobPostActivity =  jobPostActivityService.getOne(id);
       model.addAttribute("jobPostActivity",jobPostActivity);

       model.addAttribute("user",usersService.getCurrentUserProfile());
       return "add-jobs";

    }

}
