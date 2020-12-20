package com.line.store.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.constant.ApiState;
import com.line.store.dao.RoleDao;
import com.line.store.dao.StoreDao;
import com.line.store.dao.SubcategoryDao;
import com.line.store.dao.UserDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.StoreDto;
import com.line.store.dto.converter.StoreConverter;
import com.line.store.entity.Role;
import com.line.store.entity.Store;
import com.line.store.entity.Subcategory;
import com.line.store.entity.User;
import com.line.store.exception.ApiException;
import com.line.store.util.CryptPassword;

@Service
public class StoreService {

	@Autowired
	StoreDao storeDao;
	@Autowired
	SubcategoryDao subcategoryDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	StoreConverter storeConverter;
	@Autowired
	CryptPassword cryptPassword;

	public ApiResponse findAllActives() {

		List<StoreDto> stores = storeConverter.fromEntity(storeDao.findByActiveFg("S"));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), stores, stores.size());
	}

	public ApiResponse findById(String id) {

		StoreDto store = storeConverter.fromEntity(storeDao.findById(id).orElse(null));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), store, null);
	}

	public ApiResponse create(String request) throws ApiException {
		Store newStore;
		StoreDto store;

		User newUser;

		JsonNode root;

		// Store Data
		String storeId = null;
		String subcategoryId = null;
		String publicName = null;
		Double latitude = null;
		Double longitude = null;
		String description = null;
		String website = null;
		String phone = null;
		// User Data
		String userId = null;
		String name = null;
		String email = null;
		String password = null;
		String activeFg = null;

		try {
			root = new ObjectMapper().readTree(request);

			subcategoryId = root.path("subcategoryId").asText();
			publicName = root.path("publicName").asText();
			latitude = root.path("latitude").asDouble();
			longitude = root.path("longitude").asDouble();
			description = root.path("description").asText();
			website = root.path("website").asText();
			phone = root.path("phone").asText();

			name = root.path("name").asText();
			email = root.path("email").asText();
			password = root.path("password").asText();
			activeFg = "N";

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		Subcategory subcategory = subcategoryDao.findById(subcategoryId)
				.orElseThrow(() -> new ApiException(ApiState.SUBCATEGORY_NOT_FOUND.getCode(),
						ApiState.SUBCATEGORY_NOT_FOUND.getMessage()));

		try {

			newStore = new Store();

			storeId = UUID.randomUUID().toString();

			newStore.setStoreId(storeId);
			newStore.setSubcategory(subcategory);
			newStore.setActiveFg(activeFg);
			newStore.setPublicName(publicName);
			newStore.setLatitude(latitude);
			newStore.setLongitude(longitude);
			newStore.setDescription(description);
			newStore.setWebsite(website);
			newStore.setPhone(phone);

			store = storeConverter.fromEntity(storeDao.save(newStore));

			// Administrator Role
			Role role = roleDao.findById(1).orElseThrow(
					() -> new ApiException(ApiState.ROLE_NOT_FOUND.getCode(), ApiState.ROLE_NOT_FOUND.getMessage()));

			newUser = new User();
			userId = UUID.randomUUID().toString();

			newUser.setUserId(userId);
			newUser.setStore(newStore);
			newUser.setRole(role);
			// Always Store User
			newUser.setStoreFg("S");
			newUser.setName(name);
			newUser.setEmail(email);
			newUser.setPassword(cryptPassword.encode(password));
			newUser.setActiveFg(activeFg);

			userDao.save(newUser);

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), store);
	}

	public ApiResponse update(String request) throws ApiException {
		Store newStore;
		StoreDto store;

		JsonNode root;

		// Store Data
		String storeId = null;
		String publicName = null;
		Double latitude = null;
		Double longitude = null;
		String description = null;
		String website = null;
		String phone = null;

		try {
			root = new ObjectMapper().readTree(request);

			storeId = root.findPath("storeId").asText();
			publicName = root.path("publicName").asText();
			latitude = root.path("latitude").asDouble();
			longitude = root.path("longitude").asDouble();
			description = root.path("description").asText();
			website = root.path("website").asText();
			phone = root.path("phone").asText();

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		try {

			newStore = storeDao.findById(storeId).orElseThrow(
					() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

			storeId = UUID.randomUUID().toString();

			newStore.setPublicName(publicName);
			newStore.setLatitude(latitude);
			newStore.setLongitude(longitude);
			newStore.setDescription(description);
			newStore.setWebsite(website);
			newStore.setPhone(phone);

			store = storeConverter.fromEntity(storeDao.save(newStore));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), store);
	}

}
