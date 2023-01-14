package com.ganesh.jwt;

import com.ganesh.dao.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerUsersDetailsService implements UserDetailsService {

    private final UserRepository userDao;

    private com.ganesh.entity.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("::::Inside LoadUserByUsername {}::::"+ email );
        userDetail = userDao.findByEmailId(email);
        if(!Objects.isNull(userDetail))
        return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        else
            throw new UsernameNotFoundException("User not found with this email.");
    }

    public com.ganesh.entity.User getUserDetail(){
      return userDetail;
    }
}
