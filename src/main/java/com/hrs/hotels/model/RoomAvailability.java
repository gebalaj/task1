package com.hrs.hotels.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class RoomAvailability {
	private Hotel hotel;
	private LocalDate date;
	private int roomsAvailable;
	private BigDecimal roomPricePerNight;

	public RoomAvailability(Hotel hotel, LocalDate date) {
		this.hotel = hotel;
		this.date = date;
		this.roomsAvailable = 0;
		this.roomPricePerNight = BigDecimal.ZERO;
	}

	public RoomAvailability(Hotel hotel, LocalDate date, int roomsAvailable, BigDecimal roomPricePerNight) {
		this.hotel = hotel;
		this.date = date;
		this.roomsAvailable = roomsAvailable;
		this.roomPricePerNight = roomPricePerNight;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getRoomsAvailable() {
		return roomsAvailable;
	}

	public BigDecimal getRoomPricePerNight() {
		return roomPricePerNight;
	}

	public BigDecimal getReservationPricePerNight(int nrOfRooms) {
		return roomPricePerNight.multiply(new BigDecimal(nrOfRooms));
	}

	public void decreaseRooms(int rooms) {
		roomsAvailable -= rooms;
	}

	public class Key {
		private Integer hotelId;
		private LocalDate date;

		Key(Integer hotelId, LocalDate date) {
			this.hotelId = hotelId;
			this.date = date;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Key)) {
				return false;
			}
			Key key = (Key) o;
			return Objects.equals(hotelId, key.hotelId) &&
					Objects.equals(date, key.date);
		}

		@Override
		public int hashCode() {
			return Objects.hash(hotelId, date);
		}
	}

	public RoomAvailability.Key getKey() {
		return new RoomAvailability.Key(hotel.getId(), date);
	}

}
