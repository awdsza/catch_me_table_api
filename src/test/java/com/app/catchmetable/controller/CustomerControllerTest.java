package com.app.catchmetable.controller;

import com.app.catchmetable.service.CustomerService;
import com.app.catchmetable.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
* @WebMvcTest:  스캔하는 컴포넌트의 범위가 @Controller,@RestController,@ControllerAdvice 등 Web Layer 계층의 테스트를 위해 사용하는 어노테이션이다.
* 여기서는 CustomerController의 정상적인 데이터 전송을 확인하는데 사용하기위해, 어노테이션에 해당 컨드롤러를 지정
  주로 필수값 전송,양식에 맞는 값 전송 확인하는데 사용.
 *  전체적인 컴포넌트들을 스캔하지않기때문에 속도가 빠르다는 장점이 있음.
 *  요청, 응답을 그냥 가짜 객체(Mock)이 대신하기때문에 실제 운영과는 다를수도 있다. 하지만, 여기선 컨트롤러 데이터 전송이 정상적으로 되었는지만 확인하기 떄문에 문제될 부분은 없다고 판다.
* */
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RedisService redisService;
    @MockBean
    private CustomerService customerService;

    private MockHttpSession mockHttpSession;
    @BeforeEach
    void beforeEach(){
        mockHttpSession = new MockHttpSession();
        //mockHttpSession.setAttribute("userInfo",new );
    }
    @Test
    void 일반사용자_정상전송_테스트() throws Exception {
        //given
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email","test1111@33221.com");
        paramMap.put("userPw","123333");

        String contents = new ObjectMapper().writeValueAsString(paramMap);
        //when&then
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .content(contents)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isBadRequest());

    }
    @Test
    void 일반사용자_로그인_전송_테스트() throws Exception{
        //given
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId","test@naver.com");
        paramMap.put("userPw","1234");

        String contents = new ObjectMapper().writeValueAsString(paramMap);
        //when&then
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/customers/login")
                        .content(contents)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 일반사용자_정보_변경_전송_테스트() throws Exception{
        //given
        Long customerId = 9L;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("nickname","김라멘");
        paramMap.put("introduce","안녕하세요. 일식을 좋아합니다.");

        String contents = new ObjectMapper().writeValueAsString(paramMap);
        //when&then
        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/customers/"+customerId)
                        .content(contents)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void 일반사용자_정보_삭제_전송_테스트() throws Exception{
        //given
        Long customerId = 9L;
        //when&then
        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/customers/"+customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

}