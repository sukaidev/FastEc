package com.sukaidev.fastec.example.generator;

import com.sukaidev.annotations.AppRegisterGenerator;
import com.sukaidev.latte_core.wechat.templates.AppRegisterTemplate;

/**
 * Created by sukaidev on 2019/03/17.
 */
@AppRegisterGenerator(
        packageName = "com.sukaidev.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
