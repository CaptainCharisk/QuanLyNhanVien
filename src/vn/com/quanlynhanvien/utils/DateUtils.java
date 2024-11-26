package vn.com.quanlynhanvien.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateUtils {

    // List of input formatters to handle multiple date formats
    private static final List<DateTimeFormatter> inputFormatters = List.of(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/M/yyyy")
    );

    // Formatter for the output format yyyy-MM-dd (MySQL format)
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts a date string from various formats to yyyy-MM-dd format.
     * @param dateStr the date string to convert
     * @return the converted date in yyyy-MM-dd format or an empty string if conversion fails
     */
    public static String convertDateFormat(String dateStr) {
        // Check if the dateStr is null or empty
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return ""; // Return empty string if date is missing
        }

        // Try parsing the date using each formatter
        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                LocalDate date = LocalDate.parse(dateStr, formatter);
                return date.format(outputFormatter);
            } catch (DateTimeParseException e) {
                // Continue trying with the next formatter
                continue;
            }
        }

        // If none of the formats matched, print an error and return the original string
        System.err.println("Error parsing date: " + dateStr);
        return dateStr;
    }
}
