package com.line.store.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.constant.ApiState;
import com.line.store.dao.LineDao;
import com.line.store.dao.StoreDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.LineDto;
import com.line.store.dto.converter.LineConverter;
import com.line.store.entity.Line;
import com.line.store.entity.Store;
import com.line.store.exception.ApiException;

@Service
public class LineService {

	@Autowired
	LineDao lineDao;
	@Autowired
	StoreDao storeDao;

	@Autowired
	LineConverter lineConverter;

	public ApiResponse findAllByStoreId(String storeId) throws ApiException {

		Store store = storeDao.findById(storeId).orElseThrow(
				() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

		List<LineDto> lines = lineConverter.fromEntity(lineDao.findByStore(store).stream()
				.filter(x -> x.getActiveFg().equals("S")).collect(Collectors.toList()));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), lines, lines.size());
	}

	public ApiResponse findById(String id) throws ApiException {

		LineDto line = lineConverter.fromEntity(lineDao.findById(id).orElseThrow(
				() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage())));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), line, null);
	}

	public ApiResponse create(String request) throws ApiException {
		Line newLine;
		LineDto line;

		JsonNode root;

		String lineId = null;
		String storeId = null;
		String activeFg = null;
		String name = null;

		try {
			root = new ObjectMapper().readTree(request);

			lineId = root.path("lineId").asText();
			storeId = root.path("storeId").asText();
			activeFg = root.path("activeFg").asText();
			name = root.path("name").asText();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		Store store = storeDao.findById(storeId).orElseThrow(
				() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

		if (StringUtils.isBlank(lineId)) {
			newLine = new Line();
			lineId = UUID.randomUUID().toString();
		} else {
			newLine = lineDao.findById(lineId).orElseThrow(
					() -> new ApiException(ApiState.LINE_NOT_FOUND.getCode(), ApiState.LINE_NOT_FOUND.getMessage()));
		}

		try {

			newLine.setLineId(lineId);
			newLine.setStore(store);
			newLine.setActiveFg(activeFg);
			newLine.setName(name);

			line = lineConverter.fromEntity(lineDao.save(newLine));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), line);
	}

	public ApiResponse delete(String id) throws ApiException {

		Line line = lineDao.findById(id).orElseThrow(
				() -> new ApiException(ApiState.LINE_NOT_FOUND.getCode(), ApiState.LINE_NOT_FOUND.getMessage()));

		lineDao.delete(line);

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), line, null);
	}

}
