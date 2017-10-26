package com.hrs.hotels

import com.hrs.hotels.model.Hotel
import spock.lang.Specification

import java.time.LocalDate

import static com.hrs.hotels.TestHelper._2017_10_
import static com.hrs.hotels.TestHelper.sampleHotel

class ReportServiceTest extends Specification {

	def 'should return total value of bookings for each arrival date'() {
		when:
		Map<LocalDate, BigDecimal> result = ReportService.getTotalBookingValueByArrivalDate()

		then:
		result.get(_2017_10_(1)) == 148.5
	}

	def 'should return hotels ranked by total bookings value'() {
		when:
		Map<Hotel, BigDecimal> result = ReportService.getTotalBookingValueByHotel()

		then:
		result.get(sampleHotel()) == 157
	}

	def 'should return highest amount of a single rejection by arrival date'() {
		when:
		Map<LocalDate, BigDecimal> result = ReportService.getHighestAmountOfSingleRejectionByArrivalDate()

		then:
		result.get(_2017_10_(1)) == 367.74
	}

}