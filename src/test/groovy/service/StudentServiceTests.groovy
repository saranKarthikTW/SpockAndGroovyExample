package service

import com.example.hotelallocation.entity.Student
import com.example.hotelallocation.repository.StudentRepository
import com.example.hotelallocation.service.StudentService
import spock.lang.Specification

class StudentServiceTests extends Specification {
    def "getStudentFromId must return a student object with correct ID"() {
        given:
        def studentRepoMock = Mock(StudentRepository)
        def expectedStudent = new Student(1, "User 1", 1000)
        studentRepoMock.findById(1) >> Optional.of(expectedStudent)
        def service = new StudentService(studentRepoMock)

        when:
        def actualStudent = service.getStudentFromId(1)

        then:
        actualStudent == expectedStudent;
    }

    def "getStudentFromId must throw error if student id is not available"() {
        given:
        def studentRepoMock = Mock(StudentRepository)
        studentRepoMock.findById(1) >> Optional.empty()
        def service = new StudentService(studentRepoMock)

        when:
        service.getStudentFromId(1)

        then:
        thrown(Exception)
    }
}
