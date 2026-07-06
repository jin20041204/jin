package com.jin.util;

import java.util.Locale;

public class SentimentAnalyzer {

    // Very simple rule-based sentiment analyzer for demo purposes
    public static String analyze(String text) {
        if (text == null) return "neutral";
        String t = text.toLowerCase(Locale.ROOT);
        // positive keywords
        String[] positive = new String[]{"好", "喜欢", "爱", "棒", "开心", "美味", "好吃", "推荐", "满意", "棒极了", "甜"};
        for (String p : positive) {
            if (t.contains(p)) return "positive";
        }
        // negative keywords
        String[] negative = new String[]{"差", "难吃", "不行", "失望", "讨厌", "难受", "贵", "生气", "悲伤", "难过"};
        for (String n : negative) {
            if (t.contains(n)) return "negative";
        }
        return "neutral";
    }
}
