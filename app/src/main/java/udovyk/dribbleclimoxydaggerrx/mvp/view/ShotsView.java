package udovyk.dribbleclimoxydaggerrx.mvp.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import udovyk.dribbleclimoxydaggerrx.network.model.Shot;

/**
 * Created by udovik.s on 11.01.2018.
 */

public interface ShotsView extends MvpView {

    void showLoadingPb();

    void hideLoadingPb();

    void addAll(List<Shot> list);

    void addLoadingFooter();

    void removeLoadingFooter();

    void isLoading(boolean flag);



}
