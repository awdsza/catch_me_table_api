package com.app.catchmetable.controller;

import com.app.catchmetable.domain.Customer;
import com.app.catchmetable.dto.*;
import com.app.catchmetable.exception.LoginFailException;
import com.app.catchmetable.service.CustomerService;
import com.app.catchmetable.service.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    private final RedisService redisService;
    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        customerService.createUser(customerRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("회원가입이 완료되었습니다"), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        CustomerDto userInfo = customerService.login(loginDto);
        HttpSession session = request.getSession();
        session.setAttribute("userInfo",userInfo);
        return new ResponseEntity<>(new ResponseDto<>("로그인 되었습니다.",userInfo), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getCustomer(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        return new ResponseEntity<>(new ResponseDto<>("조회되었습니다",userInfo), HttpStatus.OK);
    }
    @PutMapping("/{customer_id}")
    public ResponseEntity<?> putCustomer(@PathVariable(name="customer_id") Long customerId, @Valid @RequestBody CustomerUpdateRequestDto updateDto){
        customerService.updateUser(customerId,updateDto);
        return new ResponseEntity<>(new ResponseDto<>("변경이 완료되었습니다."), HttpStatus.OK);
    }
    @DeleteMapping("/{customer_id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name="customer_id") Long customerId, HttpServletRequest request){
        customerService.deleteUser(customerId);
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>(new ResponseDto<>("삭제가 완료되었습니다."), HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutRestaurant(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        session.removeAttribute("userInfo");
        return new ResponseEntity<>(new ResponseDto<>("로그아웃 되었습니다."), HttpStatus.OK);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String message = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new FailResponseDto(message),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = LoginFailException.class)
    public Object methodLoginFailException(LoginFailException ex){
        return new ResponseEntity<>(new FailResponseDto(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
