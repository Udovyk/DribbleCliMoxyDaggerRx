package udovyk.dribbleclimoxydaggerrx.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by udovik.s on 11.01.2018.
 */

public interface ShotAttachmentsView extends MvpView {

    void hideStatusBar();

    void showViewPager();

    void showProgressbar();

    void hideProgressbar();

}
