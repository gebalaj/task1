package com.hrs.hotels.infrastructure;

import com.hrs.hotels.model.Hotel;
import com.hrs.hotels.model.ReservationRequest;
import com.hrs.hotels.model.RoomAvailability;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.LongStream;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class RoomAvailabilityFactory {

	private static final Map<RoomAvailability.Key, RoomAvailability> roomAvailabilityMap =
			createRoomAvailabilities().stream().collect(toMap(RoomAvailability::getKey, identity()));

	private static List<RoomAvailability> createRoomAvailabilities() {
		return Arrays.asList(
				new RoomAvailability(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.SEPTEMBER, 30),
						1,
						price("30")),
				new RoomAvailability(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.OCTOBER, 1),
						3,
						price("25")),
				new RoomAvailability(
						HotelFactory.getById(1),
						LocalDate.of(2017, Month.OCTOBER, 2),
						4,
						price("20.5")),
				new RoomAvailability(
						HotelFactory.getById(2),
						LocalDate.of(2017, Month.OCTOBER, 1),
						7,
						price("12")),
				new RoomAvailability(
						HotelFactory.getById(2),
						LocalDate.of(2017, Month.OCTOBER, 2),
						0,
						price("10.25")),
				new RoomAvailability(
						HotelFactory.getById(2),
						LocalDate.of(2017, Month.OCTOBER, 3),
						10,
						price("100.33")),
				new RoomAvailability(
						HotelFactory.getById(3),
						LocalDate.of(2017, Month.OCTOBER, 2),
						2,
						price("54"))
		);
	}

	public static List<RoomAvailability> getAll() {
		return roomAvailabilityMap.values().stream().collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	public static boolean checkRoomAvailability(ReservationRequest request) {
		return ReservationDayStream(request)
				.allMatch(i -> {
					Optional<RoomAvailability> roomAvailability =
							findByHotelAndDate(request.getHotel(), request.getArrival().plusDays(i));
					return roomAvailability.isPresent()
							&& roomAvailability.get().getRoomsAvailable() >= request.getNrOfRequestedRooms();
				});
	}

	public static void updateRoomAvailability(ReservationRequest request) {
		ReservationDayStream(request)
				.forEach(i -> {
					Optional<RoomAvailability> roomAvailability =
							findByHotelAndDate(request.getHotel(), request.getArrival().plusDays(i));
					roomAvailability.ifPresent(item -> item.decreaseRooms(request.getNrOfRequestedRooms()));
				});
	}

	public static BigDecimal calculateTotalValue(ReservationRequest request) {
		return ReservationDayStream(request)
				.mapToObj(i -> {
					Optional<RoomAvailability> roomAvailability =
							findByHotelAndDate(request.getHotel(), request.getArrival().plusDays(i));
					int nrOfRooms = request.getNrOfRequestedRooms();
					return roomAvailability.isPresent() ? roomAvailability.get().getReservationPricePerNight(nrOfRooms) : BigDecimal.ZERO;
				})
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private static LongStream ReservationDayStream(ReservationRequest request) {
		// requirement: Arrival and departure dates are inclusive
		return LongStream.range(0, DAYS.between(request.getArrival(), request.getDeparture()) + 1);
	}

	private static Optional<RoomAvailability> findByHotelAndDate(Hotel hotel, LocalDate date) {
		return Optional.ofNullable(roomAvailabilityMap.get(createRoomAvailabilityKey(hotel, date)));
	}

	private static RoomAvailability.Key createRoomAvailabilityKey(Hotel hotel, LocalDate date) {
		return new RoomAvailability(hotel, date).getKey();
	}

	private static BigDecimal price(String price) {
		return new BigDecimal(price);
	}
}
