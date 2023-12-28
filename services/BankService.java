package com.ilp.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankService {
	public static void createService(ArrayList<Service> serviceList) {
		char choice;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Service Code: ");
			String serviceCode = scanner.nextLine();
			System.out.println("Enter Service Name: ");
			String serviceName = scanner.nextLine();
			System.out.println("Enter the Rate: ");
			double rate = scanner.nextDouble();
			scanner.nextLine();
			Service service = new Service(serviceCode, serviceName, rate);
			serviceList.add(service);

			System.out.println("Do you want to add more services(y/n): ");
			choice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (choice == 'y' || choice == 'Y');
	}

	public static void displayService(ArrayList<Service> serviceList) {
		System.out.println("Service Code\tService Name\tRate");
		for (Service service : serviceList) {
			System.out.println(
					service.getServiceCode() + "\t\t" + service.getServiceName() + "\t\t" + service.getRate() + "\n");
		}
	}

	public static void createProduct(ArrayList<Product> productList, ArrayList<Service> serviceList) {
		char choice;
		char serviceChoice;
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter the Product Id: ");
			String productId = scanner.nextLine();
			System.out.println("Enter the Product Name: ");
			String productName = scanner.nextLine();
			do {
				System.out.println("Displaying available services");
				BankService.displayService(serviceList);
				System.out.println("Enter the service code you want the product to have: ");
				String serviceCode = scanner.nextLine();
				// adding services to the specified product
				for (Service service : serviceList) {
					if (serviceCode.equalsIgnoreCase(service.getServiceCode())) {
						productServiceList.add(service);
					} 
				}

				// checking the type of product
				if (productName.equals("Savings Max Account")) {
					Product product = new SavingsMaxAccount(productId, productName, productServiceList);
					productList.add(product);
				} else if (productName.equals("Current Account")) {
					Product product = new CurrentAccount(productId, productName, productServiceList);
					productList.add(product);
				} else if (productName.equals("Loan Account")) {
					Product product = new LoanAccount(productId, productName, productServiceList);
					productList.add(product);
				}

				System.out.println("Do you want to add more services to the product(y/n): ");
				serviceChoice = scanner.next().charAt(0);
				scanner.nextLine();
			} while (serviceChoice == 'y' || serviceChoice == 'Y');
			
			System.out.println("Do you want to add more products(y/n): ");
			choice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (choice == 'Y' || choice == 'y');
		
	}

	public static void displayProduct(ArrayList<Product> productList) {
		System.out.println("Product Code\tProduct Name\t\tServices\n");
		for (Product product : productList) {
			System.out.print(product.getProductCode() + "\t\t" + product.getProductName() + "\t\t"+ product.getserviceList() + "\n");
		}
	}
	
	public static Customer createCustomer(Customer customer, ArrayList<Product> productList) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Account> accountList = new ArrayList<Account>();
		char choice;
		int accountChoice ;
		String accountType;
		Account account;
		int accountIndex = 1;
		String accountNumber; 
		
		System.out.println("Enter Customer Id: ");
		String customerId = scanner.nextLine();
		if(customer==null) {
			do {
				accountNumber = "AC"+accountIndex;
				accountIndex++;
			
				System.out.println("Enter Account Type\n1.Savings Max Account, 2.Current Account, 3.Loan Account");
				accountChoice = scanner.nextInt();
				scanner.nextLine();
				switch(accountChoice) {
					case 1: accountType = "Savings Max Account";
					System.out.println("Enter account balance: ");
					double accountBalance = scanner.nextDouble();
					scanner.nextLine();
					account = new Account(accountNumber,accountType,accountBalance,productList.get(0));
					accountList.add(account);
					System.out.println("Account Created ");
					break;
					case 2: System.out.println("Enter account balance: ");
					accountBalance = scanner.nextDouble();
					scanner.nextLine(); 
					accountType = "Current Account";
					account = new Account(accountNumber,accountType,accountBalance,productList.get(1));
					accountList.add(account);
					System.out.println("Account Created ");
					break;
					case 3:System.out.println("Enter account balance: ");
					accountBalance = scanner.nextDouble();
					scanner.nextLine(); 
					accountType = "Loan Account";
					account = new Account(accountNumber,accountType,accountBalance,productList.get(2));
					accountList.add(account);
					System.out.println("Account Created ");
					break;
				}
				System.out.println("Do you want to create more accounts(y/n): ");
				choice = scanner.next().charAt(0);
				scanner.nextLine();
			}while(choice=='y'||choice=='Y');
			System.out.println("Enter customer name: ");
			String customerName = scanner.nextLine();
			customer = new Customer(customerId,customerName,accountList);
			System.out.println("Customer Created ");
		} else {
			System.out.println("Customer is already existing.\nPrinting Customer details. ");
		}
		return customer;
	}
	
	public static void manageCustomer(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		int serviceChoice;
		if(customer!=null) {
		ArrayList<Account> accountList = new ArrayList<Account>(); 
		System.out.println("Enter Customer id: ");
		String customerId = scanner.nextLine();
		System.out.println("Customer id: "+customer.getCustomerId());
		if(customerId.equalsIgnoreCase(customer.getCustomerId())) {
			for(Account account:customer.getAccounts()) {
				accountList = customer.getAccounts();
				System.out.println(customer.getCustomerName()+ " has following accounts: "+account.getAccountNumber()+"  "+account.getAccountType());
			}
			
			System.out.println("Choose the account number for account on which operations are to be performed: ");
			String accountNumber = scanner.nextLine();
			
			for(Account account:accountList) {
				if(accountNumber.equalsIgnoreCase(account.getAccountNumber())) {
					manageAccount(account);
				}	
			}
		} else {
			System.out.println("Customer not found!!");
		}
		} else {
			System.out.println("Create customer first");
		}
	}
	
	public static void manageAccount(Account account) {
		int serviceChoice;
		Scanner scanner = new Scanner(System.in);
		String accountType = account.getAccountType();
		if(account.getProduct() instanceof SavingsMaxAccount || account.getProduct() instanceof CurrentAccount) {
			System.out.println("The available services are \n 1.Deposit, 2.Withdraw, 3.Display Balance, 4.Exit");
			System.out.println("Enter your choice: ");
			serviceChoice = scanner.nextInt();
			
			switch(serviceChoice) {
			case 1: deposit(account);
			break;
			case 2: withdraw(account);
			break;
			case 3: displayBalance(account);
			break;
			case 4: break;
			default: System.out.println("Wrong Choice!!");
			}
		} else if(account.getProduct() instanceof LoanAccount) {
			System.out.println("The available services are \n 1.Deposit, 2.Display Balance, 3.Exit");
			System.out.println("Enter your choice: ");
			
			serviceChoice = scanner.nextInt();
			switch(serviceChoice) {
			case 1: deposit(account);
			break;
			case 2: displayBalance(account);
			break;
			case 3: break;
			default: System.out.println("Wrong Choice!!");
			}
		}
	}
	
	public static void deposit(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount you want to deposit: ");
		double depositAmount = scanner.nextDouble();
		double currentBalance = account.getAccountBalance();
		double chequeDeposit;
		
		if(account.getProduct() instanceof LoanAccount) {
			LoanAccount loanAccount = (LoanAccount) account.getProduct();
			System.out.println(" 1.Cash Deposit, 2.Cheque Deposit\nEnter your choice: ");
			int depositChoice = scanner.nextInt();
			scanner.nextLine();
			switch(depositChoice) {
			case 1:currentBalance += depositAmount;
			account.setAccountBalance(currentBalance);
			break;
			case 2:chequeDeposit = loanAccount.getChequeDeposit();
			double amountAfterTax = account.getAccountBalance();
			amountAfterTax *= chequeDeposit;
			currentBalance += amountAfterTax;
			account.setAccountBalance(currentBalance);
			}
			
		}else if(account.getProduct() instanceof SavingsMaxAccount || account.getProduct() instanceof CurrentAccount) {
			currentBalance += depositAmount;
			account.setAccountBalance(currentBalance);
		}
		
		System.out.println("Account Number\tAccount Type\t New Balance: ");
		System.out.println(account.getAccountNumber()+"\t\t"+account.getAccountType()+"\t"+account.getAccountBalance());
	}
	
	public static void withdraw(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount you want to withdraw: ");
		double withdrawAmount = scanner.nextDouble();
		double currentBalance = account.getAccountBalance();
		currentBalance -= withdrawAmount;
		if(account.getProduct() instanceof SavingsMaxAccount) {
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
			double minimumBalance = savingsMaxAccount.getMinimumBalance();
			if(currentBalance > minimumBalance) {
				System.out.println("Withdraw Successfull. Collect your money");
				account.setAccountBalance(currentBalance);
				System.out.println("Current Balance: "+ account.getAccountBalance());
			} else {
				System.out.println("Error! Your account will not have the minimum balance if this transaction is commited");
			}
		} else if(account.getProduct() instanceof CurrentAccount) {
			CurrentAccount currentAccount = (CurrentAccount) account.getProduct();
			if(currentBalance > 0) {
				System.out.println("Withdraw Successfull. Collect your money");
				account.setAccountBalance(currentBalance);
				System.out.println("Current Balance: "+ account.getAccountBalance());
			}else {
				System.out.println("Amount requested is more than the account balance");
			}
		}
	}

	public static void displayBalance(Account account) {
		System.out.println("Account No\tAccount Type\t\tBalance");
		System.out.println(account.getAccountNumber()+"\t"+account.getAccountType()+"\t"+account.getAccountBalance());
	}
	
	public static void displayCustomer(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer id: ");
		String customerId = scanner.nextLine();
		System.out.println("Customer id: "+customer.getCustomerId());
		if(customerId.equalsIgnoreCase(customer.getCustomerId())) {
			System.out.println("Customer id\tCustomer Name\tAccount Number\tAccount Type\tBalance ");
			for(Account account:customer.getAccounts()) {
				System.out.println(customer.getCustomerId()+ "\t\t"+customer.getCustomerName()+ "\t"+account.getAccountNumber()+"\t"+account.getAccountType()+"\t"+account.getAccountBalance());
			}
		} else {
			System.out.println("Customer not found!!");
		}
	}
}
