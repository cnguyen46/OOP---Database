package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to input the data from csv file as a List, or HashMap.
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class DataLoading {

	/**
	 * 
	 * This method is used to print out line by line for csv files
	 * 
	 * @param fileName
	 * @param fileLocation
	 */

	public static void parseData(String fileName, String fileLocation) {

		System.out.println(fileName);
		File f = new File(fileLocation);
		Scanner s = null;
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				System.out.println(line);
			}

			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		System.out.println("");
	}

	/**
	 * This method is used to input the data from {@link Person}.csv as a HashMap
	 *
	 * @return HashMap<String,Person>
	 */
	public static HashMap<String, Person> hashMapPersonData() {

		HashMap<String, Person> hashMapPerson = new HashMap<>();

		File f = new File("data/Persons.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idPerson = tokens[0];
				String lastName = tokens[1];
				String firstName = tokens[2];
				String street = tokens[3];
				String city = tokens[4];
				String state = tokens[5];
				String zipCode = tokens[6];
				String country = tokens[7];
				List<String> email = new ArrayList<>();
				for (int i = 8; i < tokens.length; i++) {
					email.add(tokens[i]);
				}
				Address addressPerson = new Address(street, city, state, zipCode, country);
				Person person = new Person(idPerson, lastName, firstName, addressPerson, email);

				hashMapPerson.put(idPerson, person);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return hashMapPerson;

	}

	/**
	 * This method is used to input the data from {@link Store}.csv as a HashMap
	 * 
	 * @return HashMap<String,Store>
	 */
	public static HashMap<String, Store> hashMapStoreData() {

		// Call out the method to create a HashMap for Person
		HashMap<String, Person> hashMapPerson = hashMapPersonData();

		// Using Scan method to input the csv data as the Hashmap
		HashMap<String, Store> hashMapStore = new HashMap<>();
		File f = new File("data/Stores.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idStore = tokens[0];
				Person managerInfo = hashMapPerson.get(tokens[1]);
				String street = tokens[2];
				String city = tokens[3];
				String state = tokens[4];
				String zipCode = tokens[5];
				String country = tokens[6];
				Address addressStore = new Address(street, city, state, zipCode, country);
				Store store = new Store(idStore, managerInfo, addressStore);

				hashMapStore.put(idStore, store);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashMapStore;
	}

	/**
	 * This method is used to input the data from {@link Item}.csv as a HashMap
	 * 
	 * @return HashMap<String,Item>
	 */
	public static HashMap<String, Item> hashMapItemData() {

		HashMap<String, Item> hashMapItem = new HashMap<>();

		File f = new File("data/Items.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				Item item = null;
				String idItem = tokens[0];
				String typeItem = tokens[1];
				String nameItem = tokens[2];

				if (typeItem.equals("E")) {
					String model = tokens[3];
					item = new Equipment(idItem, typeItem, nameItem, model);

				}
				if (typeItem.equals("P")) {
					String unit = tokens[3];
					double unitPrice = Double.parseDouble(tokens[4]);
					item = new Product(idItem, typeItem, nameItem, unit, unitPrice);

				}
				if (typeItem.equals("S")) {
					double hourlyRate = Double.parseDouble(tokens[3]);
					item = new Service(idItem, typeItem, nameItem, hourlyRate);
				}

				hashMapItem.put(idItem, item);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashMapItem;
	}

	/**
	 * This method is used to input the data from {@link Invoice}.csv as a HashMap
	 * 
	 * @return HashMap<String, Invoice>
	 */
	public static HashMap<String, Invoice> hashMapInvoiceData() {

		// Call out the method to create a HashMap for Store, Customer, and Salesperson
		HashMap<String, Store> hashMapStore = hashMapStoreData();
		HashMap<String, Person> hashMapCustomer = hashMapPersonData();
		HashMap<String, Person> hashMapSale = hashMapPersonData();

		// Using Scan method to input the csv data as the Hashmap
		HashMap<String, Invoice> hashMapInvoice = new HashMap<>();

		File f = new File("data/Invoices.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				String idInvoice = tokens[0];
				Store storeInfo = hashMapStore.get(tokens[1]);
				Person customerInfo = hashMapCustomer.get(tokens[2]);
				Person salesPersonInfo = hashMapSale.get(tokens[3]);
				LocalDate invoiceDate = LocalDate.parse(tokens[4]);

				Invoice invoice = new Invoice(idInvoice, storeInfo, customerInfo, salesPersonInfo, invoiceDate);
				hashMapInvoice.put(idInvoice, invoice);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashMapInvoice;

	}

	/**
	 * This method is used to convert a HashMap into a List for {@link Store} data
	 * 
	 * @return List<Invoice>
	 */
	public static List<Store> listStoreData() {

		HashMap<String, Store> hashMapStore = hashMapStoreData();
		List<Store> listStore = new ArrayList<>();
		for (String s : hashMapStore.keySet()) {
			listStore.add(hashMapStore.get(s));
		}
		return listStore;
	}

	/**
	 * This method is used to convert a HashMap into a List for {@link Invoice} data
	 * 
	 * @return List<Invoice>
	 */
	public static List<Invoice> listInvoiceData() {

		HashMap<String, Invoice> hashMapInvoice = hashMapInvoiceData();
		List<Invoice> listInvoice = new ArrayList<>();
		for (String s : hashMapInvoice.keySet()) {
			listInvoice.add(hashMapInvoice.get(s));
		}
		return listInvoice;
	}

	/**
	 * This method is used to input the data from {@link InvoiceItem}.csv as a List
	 * 
	 * @return List<InvoiceItem>
	 */
	public static List<InvoiceItem<?>> listInvItemData() {

		// Call out the method to create a TreeMap for Item, and Invoice
		HashMap<String, Item> hashMapItem = hashMapItemData();
		HashMap<String, Invoice> hashMapInvoice = hashMapInvoiceData();

		// Using Scan method to input the csv data as the List
		List<InvoiceItem<?>> listInvItem = new ArrayList<>();

		File f = new File("data/InvoiceItems.csv");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			int num0fElements = Integer.parseInt(sc.nextLine());
			for (int index = 0; index < num0fElements; index++) {
				String str = sc.nextLine();
				String[] tokens = str.split(",");

				InvoiceItem<?> invItem = null;
				Invoice invoiceInfo = hashMapInvoice.get(tokens[0]);
				Item itemInfo = hashMapItem.get(tokens[1]);

				if (itemInfo.getTypeItem().equals("E")) {
					if (tokens[2].equals("P")) {
						String agreement = tokens[2];
						double price = Double.parseDouble(tokens[3]);
						invItem = new InvoicePurchase(invoiceInfo, (Equipment) itemInfo, agreement, price);

					} else if (tokens[2].equals("L")) {
						String agreement = tokens[2];
						double price = Double.parseDouble(tokens[3]);
						LocalDate beginLease = LocalDate.parse(tokens[4]);
						LocalDate endLease = LocalDate.parse(tokens[5]);
						invItem = new InvoiceLease(invoiceInfo, (Equipment) itemInfo, agreement, price, beginLease,
								endLease);
					}
				} else if (itemInfo.getTypeItem().equals("P")) {
					double quantity = Double.parseDouble(tokens[2]);
					invItem = new InvoiceProduct(invoiceInfo, (Product) itemInfo, quantity);
				} else if (itemInfo.getTypeItem().equals("S")) {
					double hourService = Double.parseDouble(tokens[2]);
					invItem = new InvoiceService(invoiceInfo, (Service) itemInfo, hourService);
				}

				listInvItem.add(invItem);
			}

			sc.close();
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}
		return listInvItem;
	}

}
