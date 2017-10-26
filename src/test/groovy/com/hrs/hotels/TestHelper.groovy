package com.hrs.hotels

import com.hrs.hotels.model.Hotel
import com.hrs.hotels.model.ReservationRequest

import java.time.LocalDate
import java.time.Month

class TestHelper {

	static ReservationRequest request(int fromDay, int toDay, int nrRooms) {
		new ReservationRequest(sampleHotel(), _2017_10_(fromDay), _2017_10_(toDay),	nrRooms)
	}

	static LocalDate _2017_10_(int fromDay) {
		LocalDate.of(2017, Month.OCTOBER, fromDay)
	}

	static Hotel sampleHotel() {
		new Hotel(1, "Bestern Hotel Warsaw")
	}

}
