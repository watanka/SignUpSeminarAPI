package com.SeminarRegistration.seminarRegistration;

import com.SeminarRegistration.seminarRegistration.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RegistrationControllerTest {

    @Autowired
    RegistrationController registrationController;

    @MockBean
    RegistrationService registrationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


//    @Test
//    @DisplayName("세미나 신청 성공 테스트")
//    public void signUpSeminar() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/seminar/")
//                        .content(String.valueOf(id))
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().string("요청 성공"));
//    }

    @Test
    @DisplayName("세미나 신청 실패")
    public void failSignUpSeminar() throws Exception {
        long seminarId = 1L;
        String userId = "eunsung";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("seminarId", seminarId);
        jsonObject.put("userId", userId);
        String requestBody = jsonObject.toString();


        mockMvc.perform(
                        MockMvcRequestBuilders.post("/seminar/")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is5xxServerError());


    }


//    @Test
//    @DisplayName("세미나 등록 조회 - 리스트O")
//    public void successSeminar() throws Exception {
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/seminar/{id}", id)
//                        .content(String.valueOf(id))
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andExpect(status().isOk())
//                .andExpect(content().string( "등록되었습니다."));
//
//    }


//    @Test
//    @DisplayName("세미나 등록 조회 - 리스트X")
}