package pl.elabo.gbooks.mvp.view;

import java.util.List;

import pl.elabo.gbooks.model.Book;
import pl.elabo.gbooks.mvp.view.base.BaseView;

public interface BooksListView extends BaseView {

	void setupList();

	void enableScrollListener();

	void disableScrollListener();

	void addBooks(List<Book> books);

}
