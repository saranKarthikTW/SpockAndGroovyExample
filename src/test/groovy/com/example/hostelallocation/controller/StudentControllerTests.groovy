package com.example.hostelallocation.controller

import com.example.hostelallocation.entity.Student
import com.example.hostelallocation.exception.InvalidStudentIdException
import com.example.hostelallocation.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.mockito.Mockito.when
import static org.mockito.ArgumentMatchers.any

@AutoConfigureWebMvc
@WebMvcTest(controllers = [StudentController])
class StudentControllerTests extends Specification {
    @Autowired
    MockMvc mvc

    @MockBean
    StudentService studentService

    def "getStudent must return status 200 and student after successfully updating the expense"() {
        given:
        when(studentService.getStudentFromId(any())).thenReturn(new Student(1L, 'Test Student', 7500))

        expect:
        mvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().json('{\"studentId\":1,\"name\":\"Test Student\",\"totalMonthlyExpense\":7500}'))
    }

    def "getStudent must return status 400 when student is not found for that Id"() {
        given:
        when(studentService.getStudentFromId(any())).thenThrow(new InvalidStudentIdException("Student id is invalid"))

        expect:
        mvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(status().isBadRequest())
                .andExpectAll(content().string("Student id is invalid"))
    }

    def "getStudent must return status 400 when id param is invalid"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get("/students/Hi"))
                .andExpect(status().isBadRequest())
    }

    def "getStudent must return status 400 when id param is not given"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get("/students/"))
                .andExpect(status().isNotFound())
    }
}
