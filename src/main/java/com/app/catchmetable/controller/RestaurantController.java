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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    final private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> postRestaurant(@Valid @RequestBody RestaurantRequestDto registDto){
        restaurantService.createRestaurant(registDto);
        return new ResponseEntity<>(new ResponseDto<>("입점신청이 완료되었습니다."), HttpStatus.CREATED);
    }
    @GetMapping("/{restaurant_id}")
    public ResponseEntity<?> getRestaurant(@PathVariable(name="restaurant_id") Long restaurantId){
        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);
        RestaurantDto restaurantInfoDto = RestaurantDto.createSuccessDto(restaurant);
        return new ResponseEntity<>(new ResponseDto<>("조회 되었습니다.",restaurantInfoDto), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginRestaurant(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        RestaurantDto restaurantInfoDto = restaurantService.login(loginDto);
        HttpSession session = request.getSession();
        session.setAttribute("userInfo",restaurantInfoDto);

        return new ResponseEntity<>(new ResponseDto<>("로그인 되었습니다.",restaurantInfoDto), HttpStatus.OK);
    }

    @PutMapping("/{restaurant_id}")
    public ResponseEntity<?> putRestaurant(@PathVariable(name="restaurant_id") Long restaurantId, @Valid @RequestBody RestaurantUpdateRequestDto updateDto){
        restaurantService.updateRestaurant(restaurantId,updateDto);
        return new ResponseEntity<>(new ResponseDto<>("변경이 완료되었습니다."), HttpStatus.OK);
    }
    @DeleteMapping("/{restaurant_id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable(name="restaurant_id") Long restaurantId, HttpServletRequest request){
        restaurantService.deleteRestaurant(restaurantId);
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>(new ResponseDto<>("삭제가 완료되었습니다."), HttpStatus.OK);
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
