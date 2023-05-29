package com.example.hotelallocation.controller


import com.example.hotelallocation.exception.InvalidStudentIdException
import com.example.hotelallocation.service.HostelAllocationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureWebMvc
@WebMvcTest(controllers = [HostelAllocationController])
class HostelAllocationControllerTests extends Specification {
    @Autowired
    MockMvc mvc

    @MockBean
    HostelAllocationService hostelAllocationService

    def "updateExpenseForStudent must return status 200"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.post("/updateExpense/1"))
                .andExpect(status().isOk())
    }

    def "updateExpenseForStudent must return status 400 when student Id is invalid"() {
        given:
        when(hostelAllocationService.updateExpensesForStudentId(any())).thenThrow(new InvalidStudentIdException("Student id is invalid"))

        expect:
        mvc.perform(MockMvcRequestBuilders.post("/updateExpense/1"))
                .andExpect(status().isBadRequest())
                .andExpectAll(content().string("Student id is invalid"))
    }

    def "getStudent must return status 400 when id param is invalid"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.post("/updateExpense/Hi"))
                .andExpect(status().isBadRequest())
    }
}

