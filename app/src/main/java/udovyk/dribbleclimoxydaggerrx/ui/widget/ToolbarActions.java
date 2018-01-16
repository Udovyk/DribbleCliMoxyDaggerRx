package udovyk.dribbleclimoxydaggerrx.ui.widget;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

public interface ToolbarActions {

    void showDrawerToggleButton();

    void showDrawerToggleButton(Toolbar toolbar);

    Toolbar getToolBar();

    void setSupportToolBar(Toolbar toolbar);
}
