package com.example.THG_hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HotelData {
	private String name;
	private String description;
	String city;
	private int rating;

	public HotelData(String name, String description, String city, int rating) {
		this.name = name;
		this.description = description;
		this.city = city;
		this.rating = rating;
	}



	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("rating")
	public int getRating() {
		return rating;
	}


}
