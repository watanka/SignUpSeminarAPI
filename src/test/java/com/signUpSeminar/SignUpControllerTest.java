package com.signUpSeminar;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SignUpController.class)
class SignUpControllerTest {

    @Autowired
    SignUpController signUpController;

    @MockBean
    SignUpService signUpService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @AfterEach
    public void setUp(){
        WebApplicationContext webApplicationContext;
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public long id = 1L;

    @Test
    @DisplayName("세미나 신청 성공 테스트")
    public void signUpSeminar() throws Exception {

        long id = 1L;

        when(signUpService.register(id)).thenReturn("요청 성공");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/seminar/")
                        .content(String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("요청 성공"));
    }

    @Test
    @DisplayName("세미나 신청 실패")
    public void failSignUpSeminar() throws Exception {
        when(signUpService.register(id)).thenThrow(new IllegalStateException("요청 실패"));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/seminar/")
                                .content(String.valueOf(id))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is5xxServerError());


    }


    @Test
    @DisplayName("세미나 등록 조회 - 성공")
    public void successSeminar() throws Exception {
        when(signUpService.checkSignUp(id)).thenReturn("등록되었습니다.");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/seminar/{id}", id)
                        .content(String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string( "등록되었습니다."));

    }


//    @Test
//    @DisplayName("세미나 등록 조회 - 실패")
}