package com.line.store.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.line.store.entity.Booking;
import com.line.store.service.BookingService;
import com.line.store.util.PushNotification;

@Configuration
@EnableScheduling
public class ScheduledConfig {

	@Autowired
	PushNotification pushNotification;

	@Autowired
	BookingService bookingService;

	@Scheduled(cron = "${push.notification.booking.cron}", zone = "America/Lima")
	public void schedulePushBookingTask() {

		List<Booking> bookings = bookingService.getBookingsToPush();

		for (Booking booking : bookings) {
			if (booking.getUser().getSubscriptorId() != null && !booking.getUser().getSubscriptorId().isEmpty()) {
				System.out.println(booking.getUser());
				pushNotification.bookingNotification(booking);
			}
		}

	}

}
