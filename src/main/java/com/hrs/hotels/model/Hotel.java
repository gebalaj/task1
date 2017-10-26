package com.hrs.hotels.model;

import java.util.Objects;

public class Hotel {

	private Integer id;

	private String name;

	public Hotel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Hotel)) {
			return false;
		}
		Hotel hotel = (Hotel) o;
		return Objects.equals(getId(), hotel.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return name;
	}
}
