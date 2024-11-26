package vn.com.quanlynhanvien.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

	private FileUtils() {
	}

	/**
	 * Reads data from a CSV file at the specified file path.
	 *
	 * @param filePath the path of the CSV file to read
	 * @return a list of String arrays, where each array represents a row of data
	 *         from the CSV file
	 */
	public static List<String[]> readDataFromCSVFile(String filePath) {

		// List to hold each row of data from the CSV file
		List<String[]> data = new ArrayList<>();

		// Try-with-resources to ensure BufferedReader is closed after reading
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			// Read each line from the file until end of file
			while ((line = br.readLine()) != null) {
				// Split the line by commas to separate individual values
				String[] values = Arrays.stream(line.split(",")).map(String::trim).toArray(String[]::new);
				// Assuming the birth date is at index 1 (adjust if needed)
                if (values.length > 1) {
                    values[1] = DateUtils.convertDateFormat(values[1]);
                    values[7] = DateUtils.convertDateFormat(values[7]);
                }
				// Add the array of values to the data list
				data.add(values);
			}
		} catch (IOException e) {
			// Print stack trace if an IOException occurs
			e.printStackTrace();
		}

		// Return the list containing rows of data
		return data;
	}

	/**
	 * Write data to a CSV.
	 * 
	 * @param filePath the path of the CSV file to write.
	 * @param data     List a list of String arrays, where each array represents a
	 *                 row of data from the CSV file.
	 */
	public static void writeDataToCSVFile(String filePath, List<String[]> data) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

			for (String[] row : data) {

				String line = String.join(",", row);

				bw.write(line);
				bw.newLine();
			}
			System.out.println("Data has been successfully written to the CSV file.");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
