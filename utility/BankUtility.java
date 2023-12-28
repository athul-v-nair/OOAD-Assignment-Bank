package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.*;
import com.ilp.services.*;

public class BankUtility {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int mainMenuChoice;
		char dowhileChoice;
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Account> accountList = new ArrayList<Account>();
		Customer customer = null ;
		
		do {
			System.out.println("1.Create Service, 2.Create Product, 3.Create Customer, 4.Manage Account, 5.Display Customer, 6.Exit");
			System.out.println("Enter your choice: ");
			mainMenuChoice = scanner.nextInt();
			scanner.nextLine();
			switch(mainMenuChoice) {
			case 1: BankService.createService(serviceList);
			BankService.displayService(serviceList);
			break;
			case 2: BankService.createProduct(productList,serviceList);
			//BankService.displayProduct(productList);
			System.out.println(productList);
			break;
			case 3:customer = BankService.createCustomer(customer,productList);
			System.out.println("Customer Id\t\tCustomer Name\t\tAccounts");
			System.out.println(customer.getCustomerId()+"\t"+customer.getCustomerName()+"\t"+customer.getAccounts());
			break;
			case 4: BankService.manageCustomer(customer);
			break;
			case 5: BankService.displayCustomer(customer);
			break;
			case 6: System.exit(0);
			default: System.out.println("Wrong Choice!!");
			}
			System.out.println("\nDo you want to continue in main menu(y/n): ");
			dowhileChoice = scanner.next().charAt(0);
			scanner.nextLine();
		}while(dowhileChoice=='y'||dowhileChoice=='Y');
	}

}
