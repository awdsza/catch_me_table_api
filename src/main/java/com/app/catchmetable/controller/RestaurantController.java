package com.app.catchmetable.controller;

import com.app.catchmetable.dto.ResponseDto;
import com.app.catchmetable.dto.RestaurantRegistRequestDto;
import com.app.catchmetable.service.RestaurantService;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    final private RestaurantService service;

    @PostMapping("/restaurant")
    public ResponseEntity<?> postRestaurant(@Valid @RequestBody RestaurantRegistRequestDto registDto){
        service.createRestaurant(registDto);
        return new ResponseEntity<>(new ResponseDto<>("입점신청이 완료되었습니다.",null), HttpStatus.CREATED);
    }
    @ExceptionHandler(value= IllegalArgumentException.class)
    public Object methoodIllegalArgumentException(IllegalArgumentException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ResponseDto<>(message,null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResponseEntity<>(new ResponseDto<>(objectError.getDefaultMessage(),null),HttpStatus.BAD_REQUEST);
    }
}
