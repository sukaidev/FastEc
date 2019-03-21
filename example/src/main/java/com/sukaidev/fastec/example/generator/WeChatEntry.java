package com.sukaidev.fastec.example.generator;

import com.sukaidev.annotations.EntryGenerator;
import com.sukaidev.latte_core.wechat.templates.WXEntryTemplate;

/**
 * Created by sukaidev on 2019/03/17.
 */
@EntryGenerator(
        packageName = "com.sukaidev.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
