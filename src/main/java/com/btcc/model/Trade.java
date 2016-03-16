package com.btcc.model;

import java.util.Date;

import lombok.Data;

@Data
public class Trade {
	private double amount;
	private String date;
	private String market;
	private double price;
	private long trade_id;
	private String type;
	
}
