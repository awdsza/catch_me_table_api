package com.app.catchmetable.controller;

import com.app.catchmetable.dto.ResponseDto;
import com.app.catchmetable.dto.RestaurantRegistRequestDto;
import com.app.catchmetable.service.RestaurantService;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Object methoodIllegalArgumentException(IllegalArgumentException exception){
        String message = exception.getMessage();
        return new ResponseEntity<>(new ResponseDto<>(message,null),HttpStatus.BAD_REQUEST);
    }

}
