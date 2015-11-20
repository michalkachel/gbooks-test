package pl.elabo.gbooks.mvp.presenter;

import pl.elabo.gbooks.AppConstants;
import pl.elabo.gbooks.mvp.presenter.base.BasePresenter;
import pl.elabo.gbooks.mvp.view.BooksListView;
import pl.elabo.gbooks.network.RestClient;
import pl.elabo.gbooks.network.response.BooksVolumesResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class BooksListPresenterImpl extends BasePresenter<BooksListView> implements BooksListPresenter {

	private Call<BooksVolumesResponse> mBooksCall;
	private int mListIndex = 0;
	private int mTotalIndexCount = Integer.MAX_VALUE;

	public BooksListPresenterImpl(BooksListView view) {
		super(view);
	}

	@Override
	public void onCreate() {
		getView().setupList();
		getView().enableScrollListener();
		requestForBooks();
	}

	@Override
	public void onResume() {
		if (mListIndex == 0 && mBooksCall == null) {
			requestForBooks();
		}
	}

	private void requestForBooks() {
		if (mTotalIndexCount > mListIndex) {
			mBooksCall = RestClient.getBooksApi().listBooks(AppConstants.THIS_APP_QUERY, AppConstants.MAX_RESULTS_SIZE, mListIndex);
			mBooksCall.enqueue(new Callback<BooksVolumesResponse>() {
				@Override
				public void onResponse(Response<BooksVolumesResponse> response, Retrofit retrofit) {
					final BooksVolumesResponse booksVolumesResponse = response.body();
					if (response.isSuccess() && booksVolumesResponse != null) {
						mListIndex += AppConstants.MAX_RESULTS_SIZE;
						mTotalIndexCount = booksVolumesResponse.getTotalItemsCount();
						getView().addBooks(booksVolumesResponse.getBooks());
					}
				}

				@Override
				public void onFailure(Throwable t) {
					t.printStackTrace();
					getView().showError(t);
				}
			});
		}
	}

	@Override
	public void onScrolledDown() {
		requestForBooks();
	}

	@Override
	public void onPause() {
		if (mBooksCall != null) {
			mBooksCall.cancel();
			mBooksCall = null;
		}
	}

	@Override
	public void onDestroy() {
		getView().disableScrollListener();
	}
}
