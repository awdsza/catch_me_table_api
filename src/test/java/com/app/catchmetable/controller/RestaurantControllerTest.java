package com.app.catchmetable.controller;

import com.app.catchmetable.dto.RestaurantDto;
import com.app.catchmetable.service.RedisService;
import com.app.catchmetable.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedisService redisService;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantDto restaurantInfoDto;

    private MockHttpSession mockHttpSession;
    @BeforeEach
    void beforeEach(){
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("userInfo",restaurantInfoDto);
    }


    @Test
    public void 레스토랑_조회_전송_테스트() throws Exception {
        //given

        //when&then
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/restaurants/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

    }
    @Test
    public void 레스토랑_로그인_전송_테스트() throws Exception {
        //given
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId","5630101996");
        paramMap.put("userPw","1234");
        String contents = new ObjectMapper().writeValueAsString(paramMap);
        //when&then
        this.mockMvc.perform(
        post("/restaurants/login")
        .content(contents)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void 레스토랑_등록_전송_테스트() throws Exception {
        //given
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("restaurantNumber","5630101996");
        paramMap.put("userPW","1111");
        paramMap.put("restaurantName","마이니치라멘 선정릉점");
        paramMap.put("restaurantAddress","서울특별시 강남구 봉은사로61길 17); 1층 우측호(삼성동); 각산빌딩)");
        paramMap.put("restaurantIntroduce", "멸치베이스로 하는 맛있는 라멘을 파는 곳");
        paramMap.put("hasBreakTime",true);
        paramMap.put("breakStartTime","15:00");
        paramMap.put("breakEndTime","17:00");
        paramMap.put("foodMinPrice",9000);
        paramMap.put("thisfoodMaxPrice",10000);
        paramMap.put("openTime","11:30");
        paramMap.put("restaurantLimitPeople", 30);
        paramMap.put("closeTime","21:00");
        paramMap.put("waitReservationMinLimitPeople","1");
        paramMap.put("waitReservationMaxLimitPeople","3");
        paramMap.put("enterShopType","WAITING");
        String contents = new ObjectMapper().writeValueAsString(paramMap);
        //when&then
        this.mockMvc.perform(
                post("/restaurants")
                        .content(contents)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated());
    }
    @Test
    public void 레스토랑_수정_전송_테스트() throws Exception {
        //given
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("restaurantNumber","5630101996");
        paramMap.put("hasBreakTime",false);
        paramMap.put("foodMinPrice",10000);
        paramMap.put("foodMaxPrice",15000);
        paramMap.put("openTime","12,00");
        paramMap.put("restaurantLimitPeople", 30);
        paramMap.put("closeTime","20,00");
        String contents = new ObjectMapper().writeValueAsString(paramMap);

        //when&then
        this.mockMvc.perform(
                put("/restaurants/1")
                        .content(contents)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());
    }
}