package com.btcc.model;

import lombok.Data;

@Data
public class Profile {
	private String username;
	private boolean trade_password_enabled;
	private boolean otp_enabled;
	private double trade_fee;
	private double trade_fee_cnylec;
	private double trade_fee_btcltc;
	private double daily_btc_limit;
	private double daily_ltc_limit;
	private String btc_deposit_address;
	private String btc_withdrawal_address;
	private String ltc_deposit_address;
	private String ltc_withdrawal_address;
	private String api_key_permission;
	
}
