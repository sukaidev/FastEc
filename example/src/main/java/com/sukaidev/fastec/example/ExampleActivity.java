package com.sukaidev.fastec.example;


import com.sukaidev.latte.ec.laucher.LauncherDelegate;
import com.sukaidev.latte_core.activities.ProxyActivity;
import com.sukaidev.latte_core.delegates.LatteDelegate;


public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
