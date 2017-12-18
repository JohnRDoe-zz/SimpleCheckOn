package utils;

import com.sun.istack.internal.Nullable;

/**
 * Author:  andy.xwt
 * Date:    2017/12/5 17:39
 * Description:
 */


public class TextUtils {

    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
