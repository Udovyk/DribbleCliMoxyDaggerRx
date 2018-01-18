package udovyk.dribbleclimoxydaggerrx.mvp.view;

import java.util.List;

import udovyk.dribbleclimoxydaggerrx.network.model.Shot;

/**
 * Created by udovik.s on 11.01.2018.
 */

public interface ShotsView extends BaseMvpView {

    void showLoadingPb();

    void hideLoadingPb();

    void addAll(List<Shot> list);

    void addLoadingFooter();

    void removeLoadingFooter();

    void isLoading(boolean flag);



}
