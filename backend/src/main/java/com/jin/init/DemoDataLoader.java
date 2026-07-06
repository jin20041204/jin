package com.jin.init;

import com.jin.entity.Product;
import com.jin.entity.User;
import com.jin.repository.ProductRepository;
import com.jin.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DemoDataLoader(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // create demo accounts if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User u = new User();
            u.setUsername("admin");
            u.setPassword(passwordEncoder.encode("admin123"));
            u.setRole("ROLE_ADMIN");
            userRepository.save(u);
        }
        if (userRepository.findByUsername("merchant").isEmpty()) {
            User u = new User();
            u.setUsername("merchant");
            u.setPassword(passwordEncoder.encode("merchant123"));
            u.setRole("ROLE_MERCHANT");
            userRepository.save(u);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User u = new User();
            u.setUsername("user");
            u.setPassword(passwordEncoder.encode("user123"));
            u.setRole("ROLE_USER");
            userRepository.save(u);
        }

        // demo products
        if (productRepository.count() == 0) {
            Product p1 = new Product();
            p1.setTitle("萌心草莓蛋糕");
            p1.setDescription("新鲜草莓与香滑奶油，少女心首选");
            p1.setPrice(new BigDecimal("48.00"));
            p1.setImageUrl("https://via.placeholder.com/320x180.png?text=草莓蛋糕");
            p1.setMerchantId(1L);
            productRepository.save(p1);

            Product p2 = new Product();
            p2.setTitle("暖心咖啡豆 250g");
            p2.setDescription("中度烘焙，香气四溢");
            p2.setPrice(new BigDecimal("78.00"));
            p2.setImageUrl("https://via.placeholder.com/320x180.png?text=咖啡豆");
            p2.setMerchantId(1L);
            productRepository.save(p2);
        }
    }
}
