package com.sukaidev.latte_core.wechat;

import androidx.appcompat.app.AppCompatActivity;

import com.sukaidev.latte_core.app.ConfigKeys;
import com.sukaidev.latte_core.app.Latte;
import com.sukaidev.latte_core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by sukaidev on 2019/03/20.
 */
public class LatteWeChat {

    public static final String APP_ID = (String) Latte.getConfiguration().get(ConfigKeys.WE_CHAT_APP_ID.name());
    public static final String APP_SECRET = (String) Latte.getConfiguration().get(ConfigKeys.WE_CHAT_APP_SECRET.name());
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;


    private LatteWeChat() {
        final AppCompatActivity activity = (AppCompatActivity) Latte.getConfiguration().get(ConfigKeys.ACTIVITY.name());
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback =callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
