package com.tengzhi.base.tools;

/**
 * 层级码拼接工具
 *
 * @author: gaodu
 * @date: 2020/5/12 10:54
 **/
public class TierTool {
    public static final String DEFAULT_SPLIT_CHAR = "@";

    private TierTool() {

    }

    /**
     * 拼接层级码
     *
     * @param codes
     * @return
     */
    public static String join(String... codes) {
        return joinWithTrie(DEFAULT_SPLIT_CHAR, codes);
    }

    public static String joinWithTrie(String splitChar, String... codes) {
        StringBuilder sb = new StringBuilder();
        if (codes.length > 0) {
            for (int i = 0, max = codes.length; i < max; i++) {
                sb.append(codes[i]);
                if (!codes[i].endsWith(splitChar)) {
                    sb.append(splitChar);
                }
            }
        }
        return sb.toString();
    }
}
