package pl.elabo.gbooks.mvp.view;

import pl.elabo.gbooks.model.Book;
import pl.elabo.gbooks.mvp.view.base.BaseView;

public interface BooksDetailView extends BaseView {

	void postponeTransition();

	void setBookImage(String path);

	void setBookTitleAndDesc(Book book);

	void startPostponedTransition();

}
