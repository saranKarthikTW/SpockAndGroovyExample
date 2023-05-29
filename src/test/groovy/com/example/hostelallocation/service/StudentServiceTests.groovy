package com.example.hostelallocation.service

import com.example.hostelallocation.entity.Student
import com.example.hostelallocation.exception.InvalidStudentIdException
import com.example.hostelallocation.repository.StudentRepository
import spock.lang.Specification

class StudentServiceTests extends Specification {
    def "getStudentFromId must return a student object with correct ID"() {
        given:
        def studentRepoMock = Mock(StudentRepository)
        def expectedStudent = new Student(1L, "Dummy User 1", 1000)
        def service = new StudentService(studentRepoMock)

        when:
        def actualStudent = service.getStudentFromId(1L)

        then:
        actualStudent == expectedStudent

        and:
        1 * studentRepoMock.findById(1L) >> Optional.of(expectedStudent)
    }

    def "getStudentFromId must throw error if student id is not available"() {
        given:
        def studentRepoMock = Mock(StudentRepository)
        def service = new StudentService(studentRepoMock)
        studentRepoMock.findById(1) >> Optional.empty()

        when:
        service.getStudentFromId(1)

        then:
        def err = thrown(Exception)
        err.message == "No student exists for that ID"
    }

    def "updateExpenseForStudentId should update the expense for a student using its ID"() {
        given:
        def studentRepoMock = Mock(StudentRepository)
        def studentService = new StudentService(studentRepoMock)
        def studentBeforeUpdation = new Student(1L, "test user 1", 0)
        def studentAfterUpdation = new Student(1L, "test user 1", 5000)

        when:
        studentService.updateExpenseForStudentId(1L, 5000)

        then:
        1 * studentRepoMock.findById(1L) >> Optional.of(studentBeforeUpdation)
        1 * studentRepoMock.save(studentAfterUpdation)
    }

    def "updateExpenseForStudentId should not update the expense when student ID is not present"() {
        given:
        def studentRepoMock = Mock(StudentRepository)
        def studentService = new StudentService(studentRepoMock)
        studentRepoMock.findById(1L) >> Optional.empty()

        when:
        studentService.updateExpenseForStudentId(1L, 5000)

        then:
        thrown(InvalidStudentIdException)
        0 * studentRepoMock.save(_)
    }

}
