package com.app.catchmetable.service;

import com.app.catchmetable.domain.*;
import com.app.catchmetable.dto.*;
import com.app.catchmetable.exception.LoginFailException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @SpringBootTest : 애플리케이션 테스트에 필요한 모든 의존성 제공.(주로 통합 테스트에 사용) 그만큼 시간이 오래걸림.
 * */
@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder encoder;
    @Test
    public void 사용자_등록테스트() {
        //given
        CustomerRequestDto customerRequestDto = CustomerRequestDto.createRequestDto("test@naver.com","1234","맛장금","오마카세를 좋아하는 맛장급입니다.");

        //when
        Long id = customerService.createUser(customerRequestDto);
        //then
        assertNotNull(id);
    }

    @Test
    void 로그인() {
        //given
        LoginDto loginDto = LoginDto.createLoginDto(    "test@naver.com","1234");
        //when
        CustomerDto login = customerService.login(loginDto);
        //then
        assertNotNull(login);
    }

    @Test
    void 사용자업데이트_테스트(){
        CustomerUpdateRequestDto customerUpdateRequestDto = CustomerUpdateRequestDto.createUpdateRequestDto("김오마카세","오마카세를 좋아하는 사람이었다가 돈카츠도 좋아하고 여튼 이래저래한 사람입니다.");
        Long customerId = 9L;
        //when
        customerService.updateUser(customerId,customerUpdateRequestDto);
        Customer customer = customerService.findUser(customerId);

        //then
        assertEquals(customerUpdateRequestDto.getIntroduce(),customer.getIntroduce());
    }

    @Test
    void 사용자_비밀번호_변경_테스트(){
        String rawPassword = "Test@1234";
        CustomerUpdateRequestDto customerUpdateRequestDto = CustomerUpdateRequestDto.createUpdatePasswordRequestDto(rawPassword);
        Long customerId = 9L;
        //when
        customerService.updateUserPw(customerId,customerUpdateRequestDto);
        Customer customer = customerService.findUser(customerId);
        assertTrue(encoder.matches(rawPassword,customer.getUserPw()));
    }
    @Test
    void 사용자삭제_테스트(){
        //given
        Long customerId = 9L;
        //when
        customerService.deleteUser(customerId);
        Customer customer = customerService.findUser(customerId);
        customer.deleteCustomer();

        //then
        assertEquals(customer.getIsUse(),UserState.N);
    }
}