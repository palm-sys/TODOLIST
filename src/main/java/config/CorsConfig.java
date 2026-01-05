package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 1. 允许前端源（精确匹配，避免*导致凭证失效）
        config.addAllowedOrigin("http://localhost:5173");
        // 2. 允许所有请求头（或按需配置：Content-Type、Authorization等）
        config.addAllowedHeader("*");
        // 3. 允许所有HTTP方法（含OPTIONS预检请求）
        config.addAllowedMethod("*");
        // 4. 允许跨域携带凭证（如Cookie，不需要则可注释）
        config.setAllowCredentials(true);
        // 5. 预检请求缓存时间（减少OPTIONS请求次数）
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 6. 对所有接口生效
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}