package com.hrs.hotels

import com.hrs.hotels.infrastructure.RoomAvailabilityFactory
import com.hrs.hotels.model.Reservation
import spock.lang.Specification

import static com.hrs.hotels.TestHelper.request

class ReservationRequestHandlerTest extends Specification {

	def 'should process request and create reservation accepted or rejected'() {
		given:
		RoomAvailabilityFactory.getAll()
		def forRequest = request(1,2,5)

		when:
		Reservation reservation = ReservationRequestHandler.process(forRequest)

		then:
		!reservation.accepted
	}

}