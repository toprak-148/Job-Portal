package com.td005.jobportal.services;

import com.td005.jobportal.entity.JobSeekerProfile;
import com.td005.jobportal.entity.RecruiterProfile;
import com.td005.jobportal.entity.Users;
import com.td005.jobportal.repository.JobSeekerProfileRepository;
import com.td005.jobportal.repository.RecruiterProfileRepository;
import com.td005.jobportal.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
        private final UsersRepository usersRepository;
        private final JobSeekerProfileRepository jobSeekerProfileRepository;
        private final RecruiterProfileRepository recruiterProfileRepository;


        @Autowired
        public UsersService(UsersRepository usersRepository,JobSeekerProfileRepository jobSeekerProfileRepository,
                            RecruiterProfileRepository recruiterProfileRepository)
        {
            this.usersRepository = usersRepository;
            this.recruiterProfileRepository = recruiterProfileRepository;
            this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        }

        public Users addNew(Users users)
        {
            users.setActive(true);
            users.setRegistrationDate(new Date(System.currentTimeMillis()));
            Users savedUser = usersRepository.save(users);
            int userTypeId = users.getUserTypeId().getUserTypeId();
            if(userTypeId == 1)
            {
                recruiterProfileRepository.save(new RecruiterProfile(savedUser));
            }
            else{
                jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
            }
            return usersRepository.save(users);
        }

        public Optional<Users> getUserByEmail(String email)
        {
            return usersRepository.findByEmail(email);
        }


}
