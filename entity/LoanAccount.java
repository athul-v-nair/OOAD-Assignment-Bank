package com.ilp.entity;

import java.util.ArrayList;

public final class LoanAccount extends Product {
	private double chequeDeposit;
	public LoanAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
		chequeDeposit = 0.03;
	}
	public double getChequeDeposit() {
		return chequeDeposit;
	}
	public void setChequeDeposit(double chequeDeposit) {
		this.chequeDeposit = chequeDeposit;
	}
}
