package com.abcbank.voucherservice.service.rest.impl;

import com.abcbank.voucherservice.repository.mysql.UserRepository;
import com.abcbank.voucherservice.service.rest.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


}
