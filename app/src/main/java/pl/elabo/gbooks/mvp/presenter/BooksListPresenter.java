package pl.elabo.gbooks.mvp.presenter;


public interface BooksListPresenter {

	void onCreate();

	void onResume();

	void onScrolledDown();

	void onPause();

	void onDestroy();

}
