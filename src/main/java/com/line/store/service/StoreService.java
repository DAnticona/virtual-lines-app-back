package com.line.store.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.line.store.constant.ApiState;
import com.line.store.dao.CategoryDao;
import com.line.store.dao.RoleDao;
import com.line.store.dao.StoreDao;
import com.line.store.dao.UserDao;
import com.line.store.dto.ApiResponse;
import com.line.store.dto.StoreDto;
import com.line.store.dto.converter.StoreConverter;
import com.line.store.entity.Category;
import com.line.store.entity.Role;
import com.line.store.entity.Store;
import com.line.store.entity.User;
import com.line.store.exception.ApiException;
import com.line.store.util.CryptPassword;
import com.line.store.util.Utils;

@Service
public class StoreService {

	@Value("${avatar.path}")
	private String avatarPath;
	@Value("${image.path}")
	private String imagePath;
	@Value("${pageLimit.store}")
	private Integer pageStoreLimit;

	@Autowired
	StoreDao storeDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	StoreConverter storeConverter;
	@Autowired
	CryptPassword cryptPassword;
	@Autowired
	Utils UTILS;

	public ApiResponse findAll() {

		List<StoreDto> stores = storeConverter.fromEntity(storeDao.findAll());

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), stores, stores.size());
	}

	public ApiResponse findAll(Integer pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber - 1, pageStoreLimit);

		Page<Store> storesPage = storeDao.findAll(pageable);

		List<StoreDto> stores = storeConverter.fromEntity(storesPage.getContent());

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), stores,
				Math.toIntExact(storesPage.getTotalElements()));
	}

	public ApiResponse searchByPublicName(String term) {

		List<StoreDto> stores = storeConverter.fromEntity(storeDao.searchByPublicName(term));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), stores, stores.size());
	}

	public ApiResponse findById(String id) {

		StoreDto store = storeConverter.fromEntity(storeDao.findById(id).orElse(null));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), store, null);
	}

	public ApiResponse findByCategory(Integer categoryId) throws ApiException {

		Category category = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ApiException(ApiState.CATEGORY_NOT_FOUND.getCode(),
						ApiState.CATEGORY_NOT_FOUND.getMessage()));

		List<StoreDto> stores = storeConverter.fromEntity(storeDao.findByCategory(category));

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), stores, stores.size());
	}

	public ApiResponse create(String request) throws ApiException {
		Store newStore;
		StoreDto store;

		User newUser;

		JsonNode root;

		// Store Data
		String storeId = null;
		Integer categoryId = null;
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

			categoryId = root.path("categoryId").asInt();
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

		Category category = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ApiException(ApiState.CATEGORY_NOT_FOUND.getCode(),
						ApiState.CATEGORY_NOT_FOUND.getMessage()));

		try {

			newStore = new Store();

			storeId = UUID.randomUUID().toString();

			newStore.setStoreId(storeId);
			newStore.setCategory(category);
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
			newUser.setActiveFg("S");

			userDao.save(newUser);

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), store);
	}

	public ApiResponse update(String request) throws ApiException {
		System.out.println(request);
		Store store;
		StoreDto storeDto;

		JsonNode root;

		String storeId = null;
		Integer categoryId = null;
		String publicName = null;
		Double latitude = null;
		Double longitude = null;
		String description = null;
		String website = null;
		String phone = null;
		String activeFg = null;

		try {
			root = new ObjectMapper().readTree(request);
			
			System.out.println(root.findPath("categoryId").asInt());

			storeId = root.findPath("storeId").asText();
			categoryId = root.findPath("categoryId").asInt();
			publicName = root.path("publicName").asText();
			latitude = root.path("latitude").asDouble();
			longitude = root.path("longitude").asDouble();
			description = root.path("description").asText();
			website = root.path("website").asText();
			phone = root.path("phone").asText();
			activeFg = root.path("activeFg").asText();
			
			System.out.println(categoryId);

		} catch (JsonProcessingException e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		try {

			store = storeDao.findById(storeId).orElseThrow(
					() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

			Category category = categoryDao.findById(categoryId)
					.orElseThrow(() -> new ApiException(ApiState.CATEGORY_NOT_FOUND.getCode(),
							ApiState.CATEGORY_NOT_FOUND.getMessage()));

			System.out.println(category);
			
			store.setPublicName(publicName);
			store.setCategory(category);
			store.setLatitude(latitude);
			store.setLongitude(longitude);
			store.setDescription(description);
			store.setWebsite(website);
			store.setPhone(phone);
			store.setActiveFg(activeFg);

			storeDto = storeConverter.fromEntity(storeDao.save(store));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), storeDto);
	}

	public ApiResponse changeAvatar(String storeId, MultipartFile multipartFile) throws ApiException {
		Store store;
		StoreDto storeDto;

		try {

			store = storeDao.findById(storeId).orElseThrow(
					() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

			if (!multipartFile.getContentType().equals("image/png")
					&& !multipartFile.getContentType().equals("image/jpeg")
					&& !multipartFile.getContentType().equals("image/jpg")) {
				throw new ApiException(ApiState.IMAGE_INVALID.getCode(), ApiState.IMAGE_INVALID.getMessage(), null);
			}

			String filename = UUID.randomUUID().toString() + ".png";

			UTILS.multipartFileToFile(multipartFile, filename, avatarPath);

			if (store.getAvatar() != null) {
				String oldFilename = store.getAvatar();
				UTILS.deleteFile(oldFilename, avatarPath);
			}

			store.setAvatar(filename);

			storeDto = storeConverter.fromEntity(storeDao.save(store));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), storeDto);
	}

	public ApiResponse changeImage(String storeId, MultipartFile multipartFile) throws ApiException {
		Store store;
		StoreDto storeDto;

		try {

			store = storeDao.findById(storeId).orElseThrow(
					() -> new ApiException(ApiState.STORE_NOT_FOUND.getCode(), ApiState.STORE_NOT_FOUND.getMessage()));

			if (!multipartFile.getContentType().equals("image/png")
					&& !multipartFile.getContentType().equals("image/jpeg")
					&& !multipartFile.getContentType().equals("image/jpg")) {
				throw new ApiException(ApiState.IMAGE_INVALID.getCode(), ApiState.IMAGE_INVALID.getMessage(), null);
			}

			String filename = UUID.randomUUID().toString() + ".png";

			UTILS.multipartFileToFile(multipartFile, filename, imagePath);

			if (store.getImage() != null) {
				String oldFilename = store.getImage();
				UTILS.deleteFile(oldFilename, imagePath);
			}

			store.setImage(filename);

			storeDto = storeConverter.fromEntity(storeDao.save(store));

		} catch (Exception e) {
			throw new ApiException(ApiState.NO_APPLICATION_PROCESSED.getCode(),
					ApiState.NO_APPLICATION_PROCESSED.getMessage(), e.getMessage());
		}

		return ApiResponse.of(ApiState.SUCCESS.getCode(), ApiState.SUCCESS.getMessage(), storeDto);
	}

}
