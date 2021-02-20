package com.line.store.entity;

import java.util.List;
import java.util.Map;

public class Push {

	private String app_id;
	private Map<String, String> data;
	private Map<String, String> contents;
	private Map<String, String> headings;
	private List<String> include_player_ids;
	private String android_accent_color;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getContents() {
		return contents;
	}

	public void setContents(Map<String, String> contents) {
		this.contents = contents;
	}

	public Map<String, String> getHeadings() {
		return headings;
	}

	public void setHeadings(Map<String, String> headings) {
		this.headings = headings;
	}

	public List<String> getInclude_player_ids() {
		return include_player_ids;
	}

	public void setInclude_player_ids(List<String> include_player_ids) {
		this.include_player_ids = include_player_ids;
	}

	public String getAndroid_accent_color() {
		return android_accent_color;
	}

	public void setAndroid_accent_color(String android_accent_color) {
		this.android_accent_color = android_accent_color;
	}

	@Override
	public String toString() {
		return "Push [app_id=" + app_id + ", data=" + data + ", contents=" + contents + ", headings=" + headings
				+ ", include_player_ids=" + include_player_ids + ", android_accent_color=" + android_accent_color + "]";
	}

}
