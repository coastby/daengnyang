package com.daengnyangffojjak.dailydaengnyang.service;

import com.daengnyangffojjak.dailydaengnyang.domain.entity.User;
import com.daengnyangffojjak.dailydaengnyang.exception.ErrorCode;
import com.daengnyangffojjak.dailydaengnyang.exception.UserException;
import com.daengnyangffojjak.dailydaengnyang.repository.UserRepository;
import com.daengnyangffojjak.dailydaengnyang.utils.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final Validator validator;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //유저가 없는 경우 예외발생
        return validator.getUserByUserName(username);
    }
}
