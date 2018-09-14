package com.example.THG_hotel.model;

public class HotelDetail {

	int hotelId;
	String hotelName;
	String description;
	String city;
	int rating;
	double electricityUsage;
	String electricityUnit;
	double waste;
	String wasteUnit;
	double waterUsage;
	String waterUnit;

	public HotelDetail(int hotelId, String hotelName, String description, String city, int rating,
			double electricityUsage, String electricityUnit, double waste, String wasteUnit, double waterUsage,String waterUnit) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.description = description;
		this.city = city;
		this.rating = rating;
		this.electricityUsage = electricityUsage;
		this.electricityUnit = electricityUnit;
		this.waste = waste;
		this.wasteUnit = wasteUnit;
		this.waterUsage = waterUsage;
		this.waterUnit = waterUnit;
	}

	public int getHotelId() {
		return hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public double getElectricityUsage() {
		return electricityUsage;
	}

	public String getElectricityUnit() {
		return electricityUnit;
	}

	public double getWaste() {
		return waste;
	}

	public String getWasteUnit() {
		return wasteUnit;
	}

	public double getWaterUsage() {
		return waterUsage;
	}

	public String getWaterUnit() {
		return waterUnit;
	}

	public String getDescription() {
		return description;
	}

	public String getCity() {
		return city;
	}

	public int getRating() {
		return rating;
	}

}
