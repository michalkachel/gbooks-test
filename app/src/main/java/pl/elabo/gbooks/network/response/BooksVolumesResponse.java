package pl.elabo.gbooks.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pl.elabo.gbooks.model.Book;

public class BooksVolumesResponse {

	@SerializedName("totalItems")
	int mTotalItemsCount;

	@SerializedName("items")
	List<Book> mBooks;

	public BooksVolumesResponse() {
	}

	public int getTotalItemsCount() {
		return mTotalItemsCount;
	}

	public List<Book> getBooks() {
		return mBooks;
	}
}
