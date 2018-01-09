package udovyk.dribbleclimoxydaggerrx.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by udovik.s on 09.01.2018.
 */

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
