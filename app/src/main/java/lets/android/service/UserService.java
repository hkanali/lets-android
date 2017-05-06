package lets.android.service;

import android.databinding.ViewDataBinding;

import java.io.IOException;

import lets.android.BR;
import lets.android.network.ApiFactory;
import lets.android.network.entity.User;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class UserService {

    public <T extends ViewDataBinding> void setUser(final T viewDataBinding, String oauthToken) {

        ApiFactory.getRetrofitClient().getUser(oauthToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<User>>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Timber.e(e);
                    }

                    @Override
                    public void onNext(Response<User> response) {

                        User user;
                        if (!response.isSuccessful()) {

                            try {

                                Timber.e(response.errorBody().string());
                            } catch (IOException e) {
                            }

                            user = new User();
                            User.Meta meta = new User.Meta();
                            meta.name = "dummyUser";
                            meta.description = "dummy";
                            meta.imageUrl = "http://example.com";
                            user.meta = meta;
                        } else {

                            user = response.body();
                        }

                        viewDataBinding.setVariable(BR.user, user);
                    }
                });
    }
}
