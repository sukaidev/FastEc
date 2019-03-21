package com.sukaidev.latte_core.wechat.templates;

import com.sukaidev.latte_core.activities.ProxyActivity;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.wechat.BaseWXEntryActivity;
import com.sukaidev.latte_core.wechat.LatteWeChat;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * Created by sukaidev on 2019/03/17.
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
