package com.example.hostelallocation.repository

import com.example.hostelallocation.entity.Hostel
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

//DataJpaTest will automatically use the embedded database, but it wont create one. So dont forget to configure one in test's application.properties

@RunWith(SpringRunner.class)
@DataJpaTest
class HostelRepositoryTests extends Specification {
    @Autowired
    private HostelRepository hostelRepository

    def "findByStudentId returns hostel based on student ID"() {
        given:
        def hostel = new Hostel(1L, 1L)
        hostelRepository.save(hostel)

        when:
        def hostelOfStudent = hostelRepository.findByStudentId(1L)

        then:
        hostelOfStudent.get() == hostel
    }

    def "findByStudentId should return empty when no hostel is found for the student ID"() {
        given:
        def hostel = hostelRepository.findByStudentId(1L)

        expect:
        hostel.isEmpty()
    }

    def "findByStudentId should return empty when student ID is null"() {
        given:
        def hostel = hostelRepository.findByStudentId(null)

        expect:
        hostel.isEmpty()
    }
}
