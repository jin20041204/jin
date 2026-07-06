package com.jin.service;

import com.jin.dto.ChatRequest;
import com.jin.dto.ChatResponse;
import com.jin.entity.Product;
import com.jin.repository.ProductRepository;
import com.jin.util.SentimentAnalyzer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ProductRepository productRepository;

    public ChatService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ChatResponse handleChat(ChatRequest req) {
        String message = req.getMessage();
        String sentiment = SentimentAnalyzer.analyze(message);

        // Build a friendly reply
        String reply;
        switch (sentiment) {
            case "positive":
                reply = "听起来你心情不错，这里有一些适合庆祝或奖励自己的美食与商品～";
                break;
            case "negative":
                reply = "抱歉听到你心情不好，我为你挑选了一些能缓解心情的美食与购物推荐，希望能让你好一点。";
                break;
            default:
                reply = "我可以根据你的心情和偏好推荐美食与商品，告诉我你现在想吃什么或需要什么吧～";
        }

        // Simple recommendation logic based on keywords and sentiment
        List<Product> all = productRepository.findAll();
        List<Product> recs = new ArrayList<>();

        // prefer keyword matches in title/description
        String t = message == null ? "" : message.toLowerCase();

        // If sentiment positive, prefer desserts and small treats
        if ("positive".equals(sentiment)) {
            recs = filterByKeywords(all, new String[]{"蛋糕", "甜", "点心", "糕", "草莓", "甜点"});
        } else if ("negative".equals(sentiment)) {
            recs = filterByKeywords(all, new String[]{"热", "咖啡", "安慰", "巧克力", "暖", "汤"});
        } else {
            // neutral - use message keywords first
            recs = filterByKeywords(all, new String[]{"咖啡", "蛋糕", "零食", "点心", "咖啡豆", "茶"});
            if (recs.isEmpty() && !t.isEmpty()) {
                // try matching message words
                List<String> words = List.of(t.split("\\s+"));
                for (String w : words) {
                    recs.addAll(filterBySingleKeyword(all, w));
                }
            }
        }

        // fallback: top 4 newest products
        if (recs.isEmpty()) {
            recs = all.stream().sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt())).limit(4).collect(Collectors.toList());
        }

        // limit to 6
        if (recs.size() > 6) recs = recs.subList(0, 6);

        return new ChatResponse(reply, sentiment, recs);
    }

    private List<Product> filterByKeywords(List<Product> all, String[] keys) {
        List<Product> out = new ArrayList<>();
        for (Product p : all) {
            String text = (p.getTitle() + " " + (p.getDescription() == null ? "" : p.getDescription())).toLowerCase();
            for (String k : keys) {
                if (text.contains(k)) {
                    out.add(p);
                    break;
                }
            }
        }
        return out;
    }

    private List<Product> filterBySingleKeyword(List<Product> all, String key) {
        if (key == null || key.isBlank()) return List.of();
        String kk = key.toLowerCase();
        List<Product> out = new ArrayList<>();
        for (Product p : all) {
            String text = (p.getTitle() + " " + (p.getDescription() == null ? "" : p.getDescription())).toLowerCase();
            if (text.contains(kk)) out.add(p);
        }
        return out;
    }
}
