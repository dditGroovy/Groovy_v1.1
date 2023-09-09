package kr.co.groovy.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketTextHandler(), "/chat/rooms/*")
                .addInterceptors(hand)
    }

    @Bean
    public WebSocketHandler socketTextHandler() {
        return new SocketTextHandler();
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return
    }
}
