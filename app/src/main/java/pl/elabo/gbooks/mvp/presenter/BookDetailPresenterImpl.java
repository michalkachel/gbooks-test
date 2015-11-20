package pl.elabo.gbooks.mvp.presenter;

import pl.elabo.gbooks.ApiConstants;
import pl.elabo.gbooks.model.Book;
import pl.elabo.gbooks.mvp.presenter.base.BasePresenter;
import pl.elabo.gbooks.mvp.view.BooksDetailView;
import pl.elabo.gbooks.network.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class BookDetailPresenterImpl extends BasePresenter<BooksDetailView> implements BookDetailPresenter {

	private Call<Book> mBookCall;
	private String mBookId;
	private Book mBook;

	public BookDetailPresenterImpl(BooksDetailView view, String bookId) {
		super(view);
		mBookId = bookId;
	}

	@Override
	public void onCreate() {
		getView().postponeTransition();
	}

	@Override
	public void onResume() {
		if (mBook == null && mBookCall == null) {
			requestForBook();
		}
	}

	private void requestForBook() {
		mBookCall = RestClient.getBooksApi().book(mBookId);
		mBookCall.enqueue(new Callback<Book>() {
			@Override
			public void onResponse(Response<Book> response, Retrofit retrofit) {
				if (response.isSuccess() && response.body() != null) {
					mBook = response.body();
					showBook(mBook);
				}
			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
				getView().startPostponedTransition();
				getView().showError(t);
			}
		});
	}

	private void showBook(Book book) {
		getView().setBookTitleAndDesc(book);
		if (book.getImageLinks() != null && book.getImageLinks().containsKey(ApiConstants.LARGE_IMAGE_KEY)) {
			getView().setBookImage(book.getImageLinks().get(ApiConstants.LARGE_IMAGE_KEY));
		} else {
			getView().startPostponedTransition();
		}
	}

	@Override
	public void onPause() {
		if (mBookCall != null) {
			mBookCall.cancel();
			mBookCall = null;
		}
	}

	@Override
	public void onBookImageLoaded() {
		getView().startPostponedTransition();
	}

	@Override
	public void onBookImageFailedToLoad() {
		getView().startPostponedTransition();
		getView().showError(new Throwable("Image loading failed"));
	}
}
