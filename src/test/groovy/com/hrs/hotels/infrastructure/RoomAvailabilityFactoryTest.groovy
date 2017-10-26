package com.hrs.hotels.infrastructure

import spock.lang.Specification

import static com.hrs.hotels.TestHelper.request

class RoomAvailabilityFactoryTest extends Specification {

	def 'should check room availability for reservation request'() {
		when:
		def value = RoomAvailabilityFactory.checkRoomAvailability(forRequest)

		then:
		value == expectedAvailability

		where:
		forRequest                              | expectedAvailability
		request(1,2,1)  | true
		request(1,2,4)  | false
		request(2,2,4)  | true
	}

	def 'should calculate total value for reservation request'() {
		when:
		def value = RoomAvailabilityFactory.calculateTotalValue(forRequest)

		then:
		value == expectedValue

		where:
		forRequest                | expectedValue
		request(1,2,1) | 45.5
		request(1,2,2)            | 91
		request(1,2,1)            | 45.5
		request(2,2,1)            | 20.5
	}

}