package com.ilp.entity;

import java.util.ArrayList;

public final class SavingsMaxAccount extends Product {
	private double minimumBalance;
	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> services) {
		super(productCode, productName, services);
		minimumBalance = 1000;
	}
	public double getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
}
