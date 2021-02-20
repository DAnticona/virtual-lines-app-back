package com.line.store.util;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.line.store.constant.NotificationType;
import com.line.store.entity.Booking;
import com.line.store.entity.Push;
import com.line.store.entity.Slot;

@Component
public class PushNotification {

	@Value("${push.notification.oneSignal.url}")
	private String url;
	@Value("${push.notification.oneSignal.apiKey}")
	private String apiKey;
	@Value("${push.notification.oneSignal.appId}")
	private String appId;

	@Value("${push.notification.slot.title.en}")
	private String enSlotTitle;
	@Value("${push.notification.slot.title.es}")
	private String esSlotTitle;
	@Value("${push.notification.slot.message.en}")
	private String enSlotMessage;
	@Value("${push.notification.slot.message.es}")
	private String esSlotMessage;

	@Value("${push.notification.booking.title.en}")
	private String enBookingTitle;
	@Value("${push.notification.booking.title.es}")
	private String esBookingTitle;
	@Value("${push.notification.booking.message.en}")
	private String enBookingMessage;
	@Value("${push.notification.booking.message.es}")
	private String esBookingMessage;

	@Autowired
	Utils utils;

	private void send(Map<String, String> headings, Map<String, String> contents, Map<String, String> data,
			String subscriptorId) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "basic " + this.apiKey);

		List<String> include_player_ids = new ArrayList<>();
		include_player_ids.add(subscriptorId);

		Push push = new Push();
		push.setApp_id(appId);
		push.setData(data);
		push.setContents(contents);
		push.setHeadings(headings);
		push.setInclude_player_ids(include_player_ids);
		push.setAndroid_accent_color("5a162e");

		HttpEntity<Push> entity = new HttpEntity<>(push, headers);
		restTemplate.postForEntity(url, entity, Push.class);
	}

	public void slotNotification(Slot slot) {

		Map<String, String> headings = new HashMap<>();
		headings.put("en", enSlotTitle);
		headings.put("es", esSlotTitle);

		Map<String, String> contents = new HashMap<>();
		contents.put("en",
				slot.getUser().getName().concat(enSlotMessage).concat("\nLine: ").concat(slot.getLine().getName())
						.concat("\nStore: ").concat(slot.getLine().getStore().getPublicName()));
		contents.put("es",
				slot.getUser().getName().concat(esSlotMessage).concat("\nFila: ").concat(slot.getLine().getName())
						.concat("\nEstablecimiento: ").concat(slot.getLine().getStore().getPublicName()));

		Map<String, String> data = new HashMap<>();
		data.put("type", NotificationType.SLOT.getCode());

		this.send(headings, contents, data, slot.getUser().getSubscriptorId());

	}

	public void bookingNotification(Booking booking) {

		Map<String, String> headings = new HashMap<>();
		headings.put("en", booking.getUser().getName().concat(enBookingTitle));
		headings.put("es", booking.getUser().getName().concat(esBookingTitle));

		Map<String, String> contents = new HashMap<>();
		contents.put("en",
				enBookingMessage.concat("\nStore: ").concat(booking.getSchedule().getStore().getPublicName())
						.concat("\nEvent: ").concat(booking.getSchedule().getName()).concat("\nDate: ")
						.concat(booking.getSchedule().getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
						.concat("\nTime: ").concat(booking.getSchedule().getStart()));
		contents.put("es",
				esBookingMessage.concat("\nEstablecimiento: ").concat(booking.getSchedule().getStore().getPublicName())
						.concat("\nEvento: ").concat(booking.getSchedule().getName()).concat("\nFecha: ")
						.concat(booking.getSchedule().getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
						.concat("\nHora: ").concat(booking.getSchedule().getStart()));

		Map<String, String> data = new HashMap<>();
		data.put("type", NotificationType.BOOKING.getCode());

		this.send(headings, contents, data, booking.getUser().getSubscriptorId());

	}

}
