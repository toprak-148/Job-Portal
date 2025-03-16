package com.td005.jobportal.services;


import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.entity.Users;
import com.td005.jobportal.repository.JobSeekerProfileRepository;
import com.td005.jobportal.repository.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileServiceImpl implements  JobSeekerProfileService{

    private JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersRepository usersRepository;
    public JobSeekerProfileServiceImpl(JobSeekerProfileRepository jobSeekerProfileRepository,
                                       UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
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

    @Override
    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken))
        {
            String currentUserName = authentication.getName();
            Users users = usersRepository.findByEmail(currentUserName).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));
            Optional<JobSeekerProfile> seekerProfile = getOne(users.getUserId());
            return seekerProfile.orElse(null);

        }
        else{
            return null;
        }
    }
}
