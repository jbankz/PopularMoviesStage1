package bankzworld.com.utilities;

import bankzworld.com.model.movieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by King Jaycee on 26/10/2017.
 */

public interface APiService {

    @GET("popular")
    Call<movieResponse> getPopular(@Query("api_key") String api_key);

    @GET("top_rated")
    Call<movieResponse> getTopRated(@Query("api_key") String api_key);

}
