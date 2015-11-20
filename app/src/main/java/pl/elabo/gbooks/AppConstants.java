package pl.elabo.gbooks;


public interface AppConstants {

	String BASE_URL = "https://www.googleapis.com/books/v1/";

	String THIS_APP_QUERY = "London";
	int MAX_RESULTS_SIZE = 40;

	interface Argument {
		String BOOK_ID = "book_id";
		String BOOK_NAME = "book_name";
	}
}
