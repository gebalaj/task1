package com.hrs.hotels.model;

import java.time.LocalDate;

public class ReservationRequest {
	private Hotel hotel;
	private LocalDate arrival;
	private LocalDate departure;
	private int nrOfRequestedRooms;

	public ReservationRequest(Hotel hotel, LocalDate arrival, LocalDate departure, int nrOfRequestedRooms) {
		this.hotel = hotel;
		this.arrival = arrival;
		this.departure = departure;
		this.nrOfRequestedRooms = nrOfRequestedRooms;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public LocalDate getArrival() {
		return arrival;
	}

	public LocalDate getDeparture() {
		return departure;
	}

	public int getNrOfRequestedRooms() {
		return nrOfRequestedRooms;
	}
}
