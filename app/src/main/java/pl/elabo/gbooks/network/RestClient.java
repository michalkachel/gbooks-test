package pl.elabo.gbooks.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import pl.elabo.gbooks.AppConstants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestClient {

	private static RestClient sRestClient;

	private BooksApi mBooksApi;

	public RestClient() {
		Gson gson = new GsonBuilder()
				.create();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(AppConstants.BASE_URL)
				.client(getHttpClient())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();

		mBooksApi = retrofit.create(BooksApi.class);
	}

	private static RestClient getInstance() {
		if (sRestClient == null) {
			sRestClient = new RestClient();
		}
		return sRestClient;
	}

	public static BooksApi getBooksApi() {
		return getInstance().mBooksApi;
	}

	private OkHttpClient getHttpClient() {
		OkHttpClient client = new OkHttpClient();
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		client.interceptors().add(interceptor);

		return client;
	}
}
