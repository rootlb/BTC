package com.btcc.model;

import lombok.Data;

@Data
public class Balance {
	private double amount;
	private String symbol;
	private String currency;
}
