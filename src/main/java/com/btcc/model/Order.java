package com.btcc.model;

import lombok.Data;

@Data
public class Order {
	private String id;
	private String type;
	private double price;
	private String currency;
	private double amount;
	private String date;
	private String status;
}
