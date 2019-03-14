package com.sukaidev.latte_core.app;

import com.sukaidev.latte_core.util.storage.LattePreference;

/**
 * Created by sukaidev on 2019/03/14.
 */
public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}