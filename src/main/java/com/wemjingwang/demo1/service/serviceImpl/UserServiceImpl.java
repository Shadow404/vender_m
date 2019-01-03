package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.repository.UserRepository;
import com.wemjingwang.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
}
