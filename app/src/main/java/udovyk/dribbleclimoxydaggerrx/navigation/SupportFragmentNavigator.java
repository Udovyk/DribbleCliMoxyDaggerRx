package udovyk.dribbleclimoxydaggerrx.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.Screens;

import static udovyk.dribbleclimoxydaggerrx.navigation.AnimationType.FADE_ANIM;
import static udovyk.dribbleclimoxydaggerrx.navigation.AnimationType.NO_ANIM;

/**
 * Created by udovik.s on 11.01.2018.
 */

public abstract class SupportFragmentNavigator implements Navigator {
    private static String TAG = "SupportFragmentNavigator";
    private FragmentManager fragmentManager;
    private int containerId;
    private int inAnim;
    private int outAnim;


    public SupportFragmentNavigator(FragmentManager fragmentManager, int containerId, @AnimationType int animationId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        setAnimationTransaction(animationId);
    }

    public void setAnimationTransaction(int anim) {
        switch (anim) {
            case NO_ANIM:
                inAnim = NO_ANIM;
                outAnim = NO_ANIM;
                break;
            case FADE_ANIM:
                inAnim = R.anim.enter_from_right;
                outAnim = R.anim.exit_to_left;
                break;
        }
    }

    @Override
    public void applyCommand(Command command) {
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (inAnim != NO_ANIM && outAnim != NO_ANIM) {
                transaction.setCustomAnimations(inAnim, outAnim, inAnim, outAnim);
            }
            if (command instanceof Forward) {
                Forward forward = (Forward) command;
                Log.d(TAG, "---- passed data = " + forward.getTransitionData().toString() + "----");
                transaction.add(containerId, createFragment(forward.getScreenKey(), forward.getTransitionData()))
                        .addToBackStack(forward.getScreenKey())
                        .commit();

            } else if (command instanceof Back) {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStackImmediate();
                } else {
                    exit();
                }
            } else if (command instanceof Replace) {
                Replace replace = (Replace) command;
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStackImmediate();
                    transaction.add(containerId, createFragment(replace.getScreenKey(), replace.getTransitionData()))
                            .addToBackStack(replace.getScreenKey())
                            .commit();
                } else {
                    transaction.replace(containerId, createFragment(replace.getScreenKey(), replace.getTransitionData()))
                            .commit();
                }
            } else if (command instanceof BackTo) {
                String key = ((BackTo) command).getScreenKey();

                if (key == null) {
                    backToRoot();
                } else {
                    boolean hasScreen = false;
                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        if (key.equals(fragmentManager.getBackStackEntryAt(i).getName())) {
                            fragmentManager.popBackStackImmediate(key, 0);
                            hasScreen = true;
                            break;
                        }
                    }
                    if (!hasScreen) {
                        backToUnexisting();
                    }
                }
            } else if (command instanceof SystemMessage) {
                showSystemMessage(((SystemMessage) command).getMessage());
            }
    }

    private void backToRoot() {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        fragmentManager.executePendingTransactions();
    }


    protected abstract Fragment createFragment(String screenKey, Object data);


    protected abstract void showSystemMessage(String message);


    protected abstract void exit();


    protected void backToUnexisting() {
        backToRoot();
    }


}
