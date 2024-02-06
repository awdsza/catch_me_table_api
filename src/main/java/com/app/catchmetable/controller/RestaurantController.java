package com.app.catchmetable.controller;

import com.app.catchmetable.domain.Restaurant;
import com.app.catchmetable.dto.*;
import com.app.catchmetable.exception.DuplicateRestaurantNumberException;
import com.app.catchmetable.exception.LoginFailException;
import com.app.catchmetable.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    final private RestaurantService service;

    @PostMapping("")
    public ResponseEntity<?> postRestaurant(@Valid @RequestBody RestaurantRegistRequestDto registDto){
        service.createRestaurant(registDto);
        return new ResponseEntity<>(new ResponseDto<>("입점신청이 완료되었습니다.",null), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginRestaurant(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        Restaurant restaurant = service.login(loginDto);
        LoginSuccessDto successDto = LoginSuccessDto.createSuccessDto(restaurant);
        HttpSession session = request.getSession();
        session.setAttribute("loginID",restaurant.getUserId());
        return new ResponseEntity<>(new ResponseDto<>("로그인 되었습니다.",successDto), HttpStatus.OK);
    }

    @ExceptionHandler(value= IllegalArgumentException.class)
    public Object methoodIllegalArgumentException(IllegalArgumentException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(new FailResponseDto(message),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResponseEntity<>(new FailResponseDto(objectError.getDefaultMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = LoginFailException.class)
    public Object methodLoginFailException(LoginFailException ex){
        return new ResponseEntity<>(new FailResponseDto(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DuplicateRestaurantNumberException.class)
    public Object methodDuplicateRestaurantNumberException(DuplicateRestaurantNumberException ex){
        return new ResponseEntity<>(new FailResponseDto(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
