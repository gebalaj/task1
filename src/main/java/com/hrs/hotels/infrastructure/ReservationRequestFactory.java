package com.hrs.hotels.infrastructure;

import com.hrs.hotels.model.ReservationRequest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReservationRequestFactory {

	private static final List<ReservationRequest> requests = createReservationRequests();

	private static List<ReservationRequest> createReservationRequests() {
		return Arrays.asList(
				new ReservationRequest(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.OCTOBER, 1),
						LocalDate.of(2017, Month.OCTOBER, 2),
						1),
				new ReservationRequest(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.OCTOBER, 1),
						LocalDate.of(2017, Month.OCTOBER, 2),
						2),
				new ReservationRequest(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.OCTOBER, 1),
						LocalDate.of(2017, Month.OCTOBER, 2),
						1),
				new ReservationRequest(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.OCTOBER, 2),
						LocalDate.of(2017, Month.OCTOBER, 2),
						1),
				new ReservationRequest(
						HotelFactory.getById(2),
						LocalDate.of(2017, Month.OCTOBER, 1),
						LocalDate.of(2017, Month.OCTOBER, 1),
						1),
				new ReservationRequest(
						HotelFactory.getById(2),
						LocalDate.of(2017, Month.OCTOBER, 1),
						LocalDate.of(2017, Month.OCTOBER, 3),
						3),
				new ReservationRequest(
						HotelFactory.getById(2),
						LocalDate.of(2017, Month.OCTOBER, 3),
						LocalDate.of(2017, Month.OCTOBER, 3),
						3),
				new ReservationRequest(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.SEPTEMBER, 30),
						LocalDate.of(2017, Month.OCTOBER, 1),
						1)
		);
	}

	public static List<ReservationRequest> getAll() {
		return Collections.unmodifiableList(requests);
	}
}
