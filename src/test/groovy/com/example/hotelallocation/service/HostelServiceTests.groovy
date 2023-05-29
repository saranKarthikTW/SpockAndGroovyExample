package com.example.hotelallocation.service

import com.example.hotelallocation.entity.Hostel
import com.example.hotelallocation.exception.InvalidStudentIdException
import com.example.hotelallocation.repository.HostelRepository
import spock.lang.Specification

class HostelServiceTests extends Specification {
    def "getRoomIdForStudentId should return the room id for a student id"() {
        given:
        def hostelRepositoryMock = Mock(HostelRepository)
        def hostelService = new HostelService(hostelRepositoryMock)
        def hostel1 = new Hostel(2L, 1L)

        when:
        def roomId = hostelService.getRoomIdForStudentId(1L)

        then:
        roomId == hostel1.getRoomId()

        and:
        1 * hostelRepositoryMock.findByStudentId(1L) >> Optional.of(hostel1)
    }

    def "getRoomIdForStudentId should throw error when studentId is not found"() {
        given:
        def hostelRepositoryMock = Mock(HostelRepository)
        def hostelService = new HostelService(hostelRepositoryMock)
        hostelRepositoryMock.findByStudentId(1L) >> Optional.empty()

        when:
        hostelService.getRoomIdForStudentId(1L)

        then:
        def err = thrown(InvalidStudentIdException)
        err.message == "Student ID not found"
    }

}
