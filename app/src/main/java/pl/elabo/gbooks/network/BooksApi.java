package pl.elabo.gbooks.network;


import pl.elabo.gbooks.model.Book;
import pl.elabo.gbooks.network.response.BooksVolumesResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface BooksApi {

	@GET("volumes/{id}")
	Call<Book> book(@Path("id") String id);

	@GET("volumes")
	Call<BooksVolumesResponse> listBooks(@Query("q") String query, @Query("maxResults") int maxResults, @Query("startIndex") int startIndex);

}
