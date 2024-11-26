package vn.com.quanlynhanvien.utils;

import java.util.regex.Pattern;

import vn.com.quanlynhanvien.exception.BirthDayException;
import vn.com.quanlynhanvien.exception.EmailException;
import vn.com.quanlynhanvien.exception.FullNameException;
import vn.com.quanlynhanvien.exception.PhoneException;

public class CommonUtils {

	// Private constructor to prevent instantiation
	private CommonUtils() {
	}

	/**
	 * Validates the given date of birth against the required format. The format
	 * should be yyyy-MM-dd.
	 *
	 * @param dateOfBirth the date of birth string to validate
	 * @return the validated date of birth string
	 * @throws BirthDayException if the date of birth is invalid
	 */
	public static String dateOfBirthValidator(String dateOfBirth) throws BirthDayException {
		String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"; // Regex for yyyy-MM-dd
		if (!Pattern.matches(regex, dateOfBirth)) {
			throw new BirthDayException("Ngày tháng năm sinh không hợp lệ."); // Invalid date exception
		}
		return dateOfBirth; // Return validated date
	}

	/**
	 * Validates the given phone number against the required formats.
	 *
	 * @param phoneNumber the phone number string to validate
	 * @return the validated phone number string
	 * @throws PhoneException if the phone number is invalid
	 */
	public static String phoneNumberValidator(String phoneNumber) throws PhoneException {
		String regex = "^(090|098|091|031|035|038|033|076)\\d{7}$"; // Regex for valid phone numbers
		if (!Pattern.matches(regex, phoneNumber)) {
			throw new PhoneException("Số điện thoại không hợp lệ."); // Invalid phone number exception
		}
		return phoneNumber; // Return validated phone number
	}

	/**
	 * Validates the given full name against the length requirement. The full name
	 * should be between 10 to 50 characters.
	 *
	 * @param fullName the full name string to validate
	 * @return the validated full name string
	 * @throws FullNameException if the full name is invalid
	 */
	public static String fullNameValidator(String fullName) throws FullNameException {
		String regex = "^.{10,50}$"; // Regex for length between 10 and 50 characters
		if (!Pattern.matches(regex, fullName)) {
			throw new FullNameException("Họ và tên nhân viên không hợp lệ."); // Invalid full name exception
		}
		return fullName; // Return validated full name
	}

	/**
	 * Validates the given email address against a standard email format.
	 *
	 * @param email the email string to validate
	 * @return the validated email string
	 * @throws EmailException if the email is invalid
	 */
	public static String emailValidator(String email) throws EmailException {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"; // Regex for valid email format
		if (!Pattern.matches(regex, email)) {
			throw new EmailException("Email không hợp lệ."); // Invalid email exception
		}
		return email; // Return validated email
	}

}
