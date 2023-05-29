package com.example.hotelallocation.service

import com.example.hotelallocation.entity.Room
import com.example.hotelallocation.entity.amenitytypes.OccupancyType
import com.example.hotelallocation.entity.amenitytypes.PowerBackup
import com.example.hotelallocation.entity.amenitytypes.RoomType
import com.example.hotelallocation.exception.InvalidRoomIdException
import com.example.hotelallocation.repository.RoomRepository
import spock.lang.Specification

class RoomServiceTests extends Specification {
    def "getExpenseForRoomId should return cost for given room ID"(OccupancyType occupancyType, RoomType roomType, PowerBackup powerBackup, int expectedExpense) {
        given:
        def roomRepositoryMock = Mock(RoomRepository)
        def roomService = new RoomService(roomRepositoryMock)
        def roomId = 1L
        def room = new Room(roomId, occupancyType, roomType, powerBackup)

        when:
        def expenseForRoom = roomService.getExpenseForRoomId(roomId)

        then:
        expenseForRoom == expectedExpense

        and:
        1 * roomRepositoryMock.findById(roomId) >> Optional.of(room)

        where:
        occupancyType        | roomType        | powerBackup                   | expectedExpense
        OccupancyType.SINGLE | RoomType.AC     | PowerBackup.BACKUP_WITHOUT_AC | 7200
        OccupancyType.DOUBLE | RoomType.NON_AC | PowerBackup.NO_BACKUP         | 4000
        OccupancyType.TRIPLE | RoomType.AC     | PowerBackup.BACKUP_WITH_AC    | 6500
    }

    def "getExpenseForRoomId should throw error when no room exists for given room ID"() {
        given:
        def roomRepositoryMock = Mock(RoomRepository)
        def roomService = new RoomService(roomRepositoryMock)
        roomRepositoryMock.findById(1L) >> Optional.empty()

        when:
        roomService.getExpenseForRoomId(1L)

        then:
        def err = thrown(InvalidRoomIdException)
        err.message == "No room exists for that ID"
    }
}
