package com.hrs.hotels;

import com.hrs.hotels.infrastructure.RoomAvailabilityFactory;
import com.hrs.hotels.model.Reservation;
import com.hrs.hotels.model.ReservationRequest;

import java.math.BigDecimal;

class ReservationRequestHandler {

	static Reservation process(ReservationRequest request) {
		boolean accepted = false;
		if (RoomAvailabilityFactory.checkRoomAvailability(request)) {
			RoomAvailabilityFactory.updateRoomAvailability(request);
			accepted = true;
		}
		BigDecimal totalValue = RoomAvailabilityFactory.calculateTotalValue(request);
		return new Reservation(request, totalValue, accepted);
	}

}
