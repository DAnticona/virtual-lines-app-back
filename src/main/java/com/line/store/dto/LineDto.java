package com.line.store.dto;

public class LineDto {

	private String lineId;
	private StoreDto store;
	private String name;
	private String activeFg;
	private Integer clientsCount;

	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public StoreDto getStore() {
		return store;
	}
	public void setStore(StoreDto store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActiveFg() {
		return activeFg;
	}
	public void setActiveFg(String activeFg) {
		this.activeFg = activeFg;
	}
	public Integer getClientsCount() {
		return clientsCount;
	}
	public void setClientsCount(Integer clientsCount) {
		this.clientsCount = clientsCount;
	}
	@Override
	public String toString() {
		return "LineDto [lineId=" + lineId + ", store=" + store + ", name=" + name + ", activeFg=" + activeFg
				+ ", clientsCount=" + clientsCount + "]";
	}
}
