package com.line.store.constant;

public enum ApiState {
	
	SUCCESS("0", "Ok"),
	NO_APPLICATION_PROCESSED("9000", "El sistema no pudo procesar su solicitud"),
	RESOURCE_NOT_FOUND("9001", "Recurso no encontrado"),
	EMPTY_OR_NULL_PARAMETER("9002", "Uno o más parámetros están vacíos o nulos"),
	ALREADY_EXISTS("9003", "Los datos ya han sido registrados anteriormente"),
	MULTIPLES_SIMILAR_ELEMENTS("9004", "Hay más de 1 coincidencia (múltiples elementos encontrados)"),
	USER_DISABLED("9005", "Usuario inactivo"),
	LOGIN_FAILED("9006", "Usuario o contraseña incorrecta"),
	IMAGE_INVALID("9007", "Imagen no válida"),
	STORE_DISABLED("9008", "Establecimiento inactivo"),
	USER_NOT_CLIENT("9009", "El usuario no es cliente"),
	QUOTA_INCORRECT("9010", "Cantidad no disponible"),
	
	CATEGORY_NOT_FOUND("8001", "categoría desconocida"),
	STORE_NOT_FOUND("8003", "Establecimiento desconocido"),
	ROLE_NOT_FOUND("8004", "Rol desconocido"),
	LINE_NOT_FOUND("8005", "Cola desconocida"),
	USER_NOT_FOUND("8006", "Usuario desconocido"),
	SLOT_NOT_FOUND("8006", "Puesto desconocido"),
	SCHEDULE_NOT_FOUND("8007", "Agenda desconocida"),
	BOOKING_NOT_FOUND("8008", "Reserva desconocida"),
	USER_ALREADY_ASSIGNED("8009", "El usuario ya está asignado"),
	USER_ALREADY_REGISTERED("8009", "Esta cuenta de correo electrónico ya se encuentra registrada");

	private final String code;
	private final String message;

	private ApiState(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}


}
