package com.fmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is used to convert the data from csv file into json file for
 * {@link DataConverter}
 * 
 * @author Cong Nguyen
 * @author Yashraj Purbey
 *
 */
public class DataConverterJson {

	/**
	 * This method is used to create a Person.json.
	 */
	public static void personJson() {

		// Call out the method to create a List for Person
		List<Person> listPerson = DataLoading.listPersonData();

		// Using Gson to convert the List into json file
		GsonBuilder builer = new GsonBuilder();
		builer.setPrettyPrinting();
		Gson gson = builer.create();

		String dataJson = gson.toJson(listPerson);

		// Output the json file
		File output = new File("data/Persons.json");
		try {
			PrintWriter pw = new PrintWriter(output);
			pw.println("{");
			pw.println("\"persons\": [");
			pw.println(dataJson);
			pw.println("]}");

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create a Store.json.
	 */
	public static void storeJson() {

		// Call out the method to create a List for Store
		List<Store> listStore = DataLoading.listStoreData();

		// Using Gson to convert the List into json file
		GsonBuilder builer = new GsonBuilder();
		builer.setPrettyPrinting();
		Gson gson = builer.create();

		String dataJson = gson.toJson(listStore);

		// Output the json file
		File output = new File("data/Stores.json");
		try {
			PrintWriter pw = new PrintWriter(output);
			pw.println("{");
			pw.println("\"stores\": [");
			pw.println(dataJson);
			pw.println("]}");

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create a Item.json.
	 */
	public static void itemJson() {

		// Call out the method to create a List for Item
		List<Item> listItem = DataLoading.listItemData();

		// Using Gson to convert the List into json file
		GsonBuilder builer = new GsonBuilder();
		builer.setPrettyPrinting();
		Gson gson = builer.create();

		String dataJson = gson.toJson(listItem);

		// Output the json file
		File output = new File("data/Items.json");
		try {
			PrintWriter pw = new PrintWriter(output);
			pw.println("{");
			pw.println("\"items\": [");
			pw.println(dataJson);
			pw.println("]}");

			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
