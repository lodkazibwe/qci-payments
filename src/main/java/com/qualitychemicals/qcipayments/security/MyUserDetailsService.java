package com.qualitychemicals.qcipayments.security;

import com.qualitychemicals.qcipayments.user.doa.UserDao;
import com.qualitychemicals.qcipayments.user.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo user =userDao.findByUserName(userName);
        MyUserDetails userDetails=new MyUserDetails(user);
        if(user !=null){
            return userDetails;

        }else{
            throw new UsernameNotFoundException("UserInfo not exist with name : " + userName);
        }
    }
}
