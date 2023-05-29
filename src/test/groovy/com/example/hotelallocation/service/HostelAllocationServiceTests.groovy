package com.example.hotelallocation.service


import spock.lang.Specification

class HostelAllocationServiceTests extends Specification {
    def "updateExpensesForStudentId should update the expenses for student using its ID"() {
        given:
        def hostelServiceMock = Mock(HostelService)
        def studentServiceMock = Mock(StudentService)
        def roomServiceMock = Mock(RoomService)
        def hostelAllocationService = new HostelAllocationService(hostelServiceMock, studentServiceMock, roomServiceMock)
        def roomId = 1L
        def studentId = 5L
        def expense = 5700

        when:
        hostelAllocationService.updateExpensesForStudentId(studentId)

        then:
        1 * hostelServiceMock.getRoomIdForStudentId(studentId) >> roomId
        1 * roomServiceMock.getExpenseForRoomId(roomId) >> expense
        1 * studentServiceMock.updateExpenseForStudentId(studentId, expense)
    }

    def "updateExpensesForStudentId should update the expenses to zero when student is not mapped to a room"() {
        given:
        def hostelServiceMock = Mock(HostelService)
        def studentServiceMock = Mock(StudentService)
        def roomServiceMock = Mock(RoomService)
        def hostelAllocationService = new HostelAllocationService(hostelServiceMock, studentServiceMock, roomServiceMock)
        def studentId = 1L

        hostelServiceMock.getRoomIdForStudentId(studentId) >> null

        when:
        hostelAllocationService.updateExpensesForStudentId(studentId)

        then:
        1 * studentServiceMock.updateExpenseForStudentId(studentId, 0)
        0 * roomServiceMock.getExpenseForRoomId(_)
    }

}
