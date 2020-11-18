package com.line.store.constant;

public enum ApiState {
	
	SUCCESS("0", "Ok"),
	NO_APPLICATION_PROCESSED("9000", "El sistema no pudo procesar su solicitud"),
	RESOURCE_NOT_FOUND("9001", "Recurso no encontrado"),
	EMPTY_OR_NULL_PARAMETER("9002", "Uno o más parámetros están vacíos o nulos"),
	ALREADY_EXISTS("9003", "Los datos ya han sido registrados anteriormente"),
	MULTIPLES_SIMILAR_ELEMENTS("9004", "Hay más de 1 coincidencia (múltiples elementos encontrados)"),
	USER_DISABLED("9005", "Usuario deshabilitado"),
	LOGIN_FAILED("9006", "Usuario o contraseña incorrecta"),
	
	CATEGORY_NOT_FOUND("8001", "categoría desconocida"),
	SUBCATEGORY_NOT_FOUND("8002", "Subcategoría desconocida"),
	STORE_NOT_FOUND("8003", "Establecimiento desconocido"),
	ROLE_NOT_FOUND("8004", "Rol desconocido"),
	
	RUC_LENGTH("9005", "RUC debe tener longitud 11"),
	DNI_LENGTH("9006", "DNI debe tener longitud 8"),
	CE_LENGTH("9007", "CE debe tener longitud máxima de 12"),
	QUOTATION_LINES("9008", "No se ha enviado líneas de cotización"),
	ADUANA_NOT_FOUND("8000", "No se encontró Aduana"),
	AGENCIA_ADUANA_NOT_FOUND("8001", "No se encontró Agencia Aduana"),
	DEPOSITO_TEMPORAL_NOT_FOUND("8002", "No se encontró Depósito temporal"),
	REGIMEN_NOT_FOUND("8003", "No se encontró Régimen"),
	TIPO_BULTO_NOT_FOUND("8004", "No se encontró Tipo Bulto"),
	DAM_NOT_FOUND("8005", "No se encontró DAM"),
	SERIE_NOT_FOUND("8006", "No se encontró Serie Comprobante"),
	TIPO_COMPROBANTE_NOT_FOUND("8007", "No se encontró Tipo Comprobante"),
	VENDEDOR_NOT_FOUND("8008", "No se encontró Vendedor"),
	CLIENTE_NOT_FOUND("8009", "No se encontró Cliente"),
	MONEDA_NOT_FOUND("8010", "No se encontró Moneda"),
	COTIZACION_NOT_FOUND("8011", "No se encontró Cotización"),
	SERVICIO_NOT_FOUND("8012", "No se encontró Servicio"),
	EMPRESA_NOT_FOUND("8013", "No se encontró Empresa"),
	CHOFER_NOT_FOUND("8014", "No se encontró Chofer"),
	UBICACION_NOT_FOUND("8015", "No se encontró Ubicación"),
	TIPO_MERCANCIA_NOT_FOUND("8016", "No se encontró Tipo Mercancía"),
	TARJETA_NOT_FOUND("8017", "No se encontró Tarjeta"),
	VEHICULO_NOT_FOUND("8018", "No se encontró Vehículo"),
	TICKET_NOT_FOUND("8019", "No se encontró Ticket"),
	MOVIMIENTO_NOT_FOUND("8020", "No se encontró Movimiento"),
	GRUPO_SERVICIO_NOT_FOUND("8021", "No se encontró Grupo de servicios"),
	SUBSERVICIO_NOT_FOUND("8022", "No se encontró Grupo de Subservicio");

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
