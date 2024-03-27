package com.app.catchmetable.service;

import com.app.catchmetable.domain.Customer;
import com.app.catchmetable.domain.User;
import com.app.catchmetable.dto.*;
import com.app.catchmetable.exception.DuplicateUserException;
import com.app.catchmetable.exception.LoginFailException;
import com.app.catchmetable.exception.NotExistUserException;
import com.app.catchmetable.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long createUser(CustomerRequestDto registDto){
        customerRepository.findByUserName(registDto.getEmail())
                          .orElseThrow(()-> new DuplicateUserException("중복된 사용자 입니다."));
        registDto.changeEncodePw(passwordEncoder.encode(registDto.getUserPw()));
        Customer customer = Customer.createCustomer(registDto);
        customerRepository.save(customer);
        return customer.getId();
    }



    public CustomerDto login(LoginDto loginDto) {
        return customerRepository.findByUserName(loginDto.getUserId())
                .filter(customer-> passwordEncoder.matches(loginDto.getUserPw(),customer.getUserPw()))
                .map(customer -> CustomerDto.createSuccessDto(customer))
                .orElseThrow(()-> new LoginFailException("사용자가 없거나 로그인 정보가 틀린 대상입니다."));
    }

    public Customer findUser(Long userId) {
        return customerRepository.findUser(userId)
        .orElseThrow(()-> new NotExistUserException("해당 ID를 가진 사용자가 존재하지않습니다."));
    }

    @Transactional
    public void updateUser(Long userId,CustomerUpdateRequestDto updateDto) {
        findUser(userId).updateCustomer(updateDto);
    }

    @Transactional
    public void updateUserPw(Long userId,CustomerUpdateRequestDto updateDto) {
        updateDto.changeEncodePw(passwordEncoder.encode(updateDto.getUserPw()));
        findUser(userId).updateCustomerPassword(updateDto);
    }
    @Transactional
    public void deleteUser(Long userId) {
        findUser(userId).deleteCustomer();

    }
}
