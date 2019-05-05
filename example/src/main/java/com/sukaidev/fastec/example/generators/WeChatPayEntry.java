package com.sukaidev.fastec.example.generators;

import com.sukaidev.annotations.PayEntryGenerator;
import com.sukaidev.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by sukaidev on 2019/03/17.
 */
@PayEntryGenerator(
        packageName = "com.sukaidev.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
