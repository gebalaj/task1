package com.hrs.hotels;

public class Application {

	public static void main(String[] arg){
		System.out.println("************************** Report **************************");
		System.out.println("1) Total value of bookings, for each arrival date");
		System.out.println(ReportService.getTotalBookingValueByArrivalDate());
		System.out.println();
		System.out.println("2) Hotels ranked by total bookings value (sum of values of all bookings for that hotel)");
		System.out.println(ReportService.getTotalBookingValueByHotel());
		System.out.println();
		System.out.println("3) Dates, ranked by the highest amount of a single rejection with such arrival date");
		System.out.println(ReportService.getHighestAmountOfSingleRejectionByArrivalDate());
		System.out.println("**************************  End   **************************");
	}

}
