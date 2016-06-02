package com.btcc.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Ticker {
	@Id
	private String id;
	public String open;
	private String vwap;
	private String vol;
	private String market;
	private String last;
	private String sell;
	private String buy;
	private String high;
	private String date;
	private String low;
	private String prev_close;
}
