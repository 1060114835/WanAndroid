package com.chen.wanandroid.architecture.function;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

public class CopyUtil {
    public static void copy(Context context, String data) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Label", data);
        cm.setPrimaryClip(clipData);
    }
}
