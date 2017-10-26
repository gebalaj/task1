package com.hrs.hotels.infrastructure;

import com.hrs.hotels.model.Hotel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class HotelFactory {

	private static final Map<Integer, Hotel> hotelById =
			createHotels().stream().collect(toMap(Hotel::getId, identity()));

	private static List<Hotel> createHotels() {
		return Arrays.asList(
				new Hotel(1, "Bestern Hotel Warsaw"),
				new Hotel(2, "Carriot Berlin"),
				new Hotel(3, "Pheraton London Central")
		);
	}

	public static List<Hotel> getAll() {
		return hotelById.values().stream().collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	static Hotel getById(Integer id) {
		return hotelById.get(id);
	}

}
