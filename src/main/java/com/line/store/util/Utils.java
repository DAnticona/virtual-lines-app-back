package com.line.store.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Utils {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public LocalDateTime longToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
	}
	
	public LocalDate longToLocalDate(Long timestamp) {
		return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public Long LocalDateTimeTolong(LocalDateTime date) {
		return ZonedDateTime.of(date, ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	public Long LocalDateToLong(LocalDate date) {
		return ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	public void multipartFileToFile(MultipartFile multipartFile, String filename, String path) {

		File file = new File(path + filename);

		try {

			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();

		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error on process file", e);
		}
	}

	public Boolean deleteFile(String filename, String path) {
		filename = path + filename;

		File file = new File(filename);

		return file.delete();
	}
}
