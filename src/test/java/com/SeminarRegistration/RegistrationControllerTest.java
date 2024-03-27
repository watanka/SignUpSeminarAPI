//package com.SeminarRegistration;
//
//import com.SeminarRegistration.RegistrationController;
//import com.SeminarRegistration.repository.JpaRegistrationRepository;
//import com.SeminarRegistration.service.RegistrationService;
//
//import net.minidev.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(RegistrationController.class)
//class RegistrationControllerTest {
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .alwaysDo(print())
//                .build();
//    }
//
//    @Test
//    @DisplayName("세미나 신청 성공 테스트")
//    public void signUpSeminar() throws Exception {
//
//        long seminarId = 1L;
//        String userId = "eunsung";
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("seminarId", seminarId);
//        jsonObject.put("userId", userId);
//        String requestBody = jsonObject.toString();
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/seminar/")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().string("요청 성공"));
//    }
//
//    @Test
//    @DisplayName("세미나 신청 실패")
//    public void failSignUpSeminar() throws Exception {
//        long seminarId = 1L;
//        String userId = "eunsung";
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("seminarId", seminarId);
//        jsonObject.put("userId", userId);
//        String requestBody = jsonObject.toString();
//
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/seminar/")
//                                .content(requestBody)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().is5xxServerError());
//
//
//    }
//
//
////    @Test
////    @DisplayName("세미나 등록 조회 - 리스트O")
////    public void successSeminar() throws Exception {
////
////        mockMvc.perform(
////                MockMvcRequestBuilders.get("/seminar/{id}", id)
////                        .content(String.valueOf(id))
////                        .contentType(MediaType.APPLICATION_JSON)
////        )
////                .andExpect(status().isOk())
////                .andExpect(content().string( "등록되었습니다."));
////
////    }
//
//
////    @Test
////    @DisplayName("세미나 등록 조회 - 리스트X")
//}