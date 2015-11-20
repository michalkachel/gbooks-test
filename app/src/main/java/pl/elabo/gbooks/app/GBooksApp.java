package pl.elabo.gbooks.app;

import android.app.Application;

import pl.elabo.gbooks.BuildConfig;
import timber.log.Timber;


public class GBooksApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}
}
