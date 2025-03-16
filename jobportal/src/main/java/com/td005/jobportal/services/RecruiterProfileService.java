package com.td005.jobportal.services;

import com.td005.jobportal.entity.RecruiterProfile;
import com.td005.jobportal.entity.Users;
import com.td005.jobportal.repository.RecruiterProfileRepository;
import com.td005.jobportal.repository.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RecruiterProfileService {
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UsersRepository usersRepository;

    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository,
                                   UsersRepository usersRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id)
    {
        return recruiterProfileRepository.findById(id);

    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrenRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Users users = usersRepository.findByEmail(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            return recruiterProfile.orElse(null);
        }
        return null;

    }
}
