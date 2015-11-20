package pl.elabo.gbooks.mvp.presenter;


public interface BookDetailPresenter {

	void onCreate();

	void onResume();

	void onPause();

	void onBookImageLoaded();

	void onBookImageFailedToLoad();

}
