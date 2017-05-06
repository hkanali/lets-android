package lets.android.network;

import lets.android.network.entity.Category;
import lets.android.network.entity.PageImpl;
import lets.android.network.entity.Restaurant;
import lets.android.network.entity.User;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("/api/category/{id}")
    Observable<Response<User>> getUser(@Path("id") String id);
    //Observable<Response<User>> getUser(@Header("Authorization") String oauthAccessToken);

    @GET("/api/category")
    Observable<Response<PageImpl<Category>>> getCategory(@Query("page") int page);

    @GET("/api/restaurant")
    Observable<Response<PageImpl<Restaurant>>> getRestaurant(@Query("page") int page);
}
