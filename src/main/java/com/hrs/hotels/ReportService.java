package com.hrs.hotels;

import com.hrs.hotels.infrastructure.HotelFactory;
import com.hrs.hotels.infrastructure.ReservationRequestFactory;
import com.hrs.hotels.model.Hotel;
import com.hrs.hotels.model.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class ReportService {

	private static List<Reservation> reservations = processReservationRequest();

	private static List<Reservation> processReservationRequest() {
		return ReservationRequestFactory.getAll().stream()
				.map(ReservationRequestHandler::process)
				.collect(toList());
	}

	static Map<LocalDate, BigDecimal> getTotalBookingValueByArrivalDate() {
		Map<LocalDate, BigDecimal> result = reservations.stream()
				.filter(Reservation::isAccepted)
				.collect(groupingBy(Reservation::getArrivalDate, TreeMap::new, mapping(Reservation::getTotalValue, reducing(BigDecimal.ZERO, BigDecimal::add))));

		reservations.forEach(reservation -> result.computeIfAbsent(reservation.getArrivalDate(), value -> BigDecimal.ZERO));
		return result;
	}

	static Map<Hotel, BigDecimal> getTotalBookingValueByHotel() {
		Map<Hotel, BigDecimal> result = reservations.stream()
				.filter(Reservation::isAccepted)
				.collect(groupingBy(Reservation::getHotel, mapping(Reservation::getTotalValue, reducing(BigDecimal.ZERO, BigDecimal::add))));

		HotelFactory.getAll().forEach(hotel -> result.computeIfAbsent(hotel, value -> BigDecimal.ZERO));
		return result;
	}

	static Map<LocalDate, BigDecimal> getHighestAmountOfSingleRejectionByArrivalDate() {
		Map<LocalDate, BigDecimal> result = reservations.stream()
				.filter(Reservation::isRejected)
				.collect(toMap(Reservation::getArrivalDate, Reservation::getTotalValue, BinaryOperator.maxBy(BigDecimal::compareTo), TreeMap::new));

		reservations.forEach(reservation -> result.computeIfAbsent(reservation.getArrivalDate(), value -> BigDecimal.ZERO));
		return result;
	}

}
