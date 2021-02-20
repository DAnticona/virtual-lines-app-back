package com.line.store.constant;

public enum NotificationType {

	SLOT("8008", "Reserva desconocida"),
	BOOKING("8009", "El usuario ya está asignado");
	
	private final String code;
	private final String name;
	
	private NotificationType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
}
