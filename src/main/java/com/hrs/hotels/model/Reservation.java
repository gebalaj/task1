package com.hrs.hotels.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {

	private ReservationRequest request;
	private BigDecimal totalValue;
	private boolean accepted;

	public Reservation(ReservationRequest request, BigDecimal totalValue, boolean accepted) {
		this.request = request;
		this.totalValue = totalValue;
		this.accepted = accepted;
	}

	public Hotel getHotel() {
		return request.getHotel();
	}

	public LocalDate getArrivalDate() {
		return request.getArrival();
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public boolean isRejected() {
		return !accepted;
	}
}