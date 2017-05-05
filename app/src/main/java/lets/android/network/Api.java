package lets.android.network;

import lets.android.network.entity.Category;
import lets.android.network.entity.PageImpl;
import lets.android.network.entity.Restaurant;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("/api/category")
    Observable<PageImpl<Category>> getCategory(@Query("page") int page);

    @GET("/api/restaurant")
    Observable<PageImpl<Restaurant>> getRestaurant(@Query("page") int page);
}
