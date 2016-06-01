package com.btcc.model;

import lombok.Data;

@Data
public class Frozen {
	private double amount;
	private String symbol;
	private String currency;
	private String amount_integer;
	private String amount_decimal;
}
