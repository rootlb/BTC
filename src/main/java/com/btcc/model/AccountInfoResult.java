package com.btcc.model;

import lombok.Data;

@Data
public class AccountInfoResult {
	private Profile profile;
	private Balance balance;
	private Frozen frozen;
	private Loan loan;
}
