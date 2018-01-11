package udovyk.dribbleclimoxydaggerrx.navigation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by udovik.s on 11.01.2018.
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {AnimationType.NO_ANIM, AnimationType.FADE_ANIM})
public @interface AnimationType {
    int NO_ANIM = 0;
    int FADE_ANIM = 1;


}