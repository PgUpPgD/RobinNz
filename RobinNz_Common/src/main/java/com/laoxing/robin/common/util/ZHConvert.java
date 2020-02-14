package com.laoxing.robin.common.util;

import java.util.ResourceBundle;

/**
 * @program: BabyStudy
 * @description:
 * @author: Feri
 * @create: 2019-12-30 15:44
 */
public class ZHConvert {
    private static final ZHConvert zhConvert = new ZHConvert();
    private static ResourceBundle resourceBundle = null;

    public static ZHConvert getInstance(ZHType type) {
        if (type.equals(ZHType.繁体)) {
            resourceBundle= ResourceBundle.getBundle("sim");
        } else {
            resourceBundle = ResourceBundle.getBundle("tra");
        }
        return zhConvert;
    }

    private ZHConvert() {
    }

    public static String parseText(String text, ZHType type) {
        return ZHConvert.getInstance(type).parse(text);
    }

    public String parse(String text) {
        if (text == null) {
            throw new IllegalArgumentException("字符串为null");
        } else {
            StringBuilder builder = new StringBuilder();
            if (text.length() > 1 && resourceBundle.containsKey(text)) {
                return resourceBundle.getString(text);
            } else {
                char[] var3 = text.toCharArray();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    char c = var3[var5];
                    String s = String.valueOf(c);
                    builder.append(resourceBundle.containsKey(s) ?resourceBundle.getString(s).charAt(0) : s);
                }

                return builder.toString();
            }
        }
    }

    public static enum ZHType {
        繁体,
        简体;

        private ZHType() {
        }
    }
}
