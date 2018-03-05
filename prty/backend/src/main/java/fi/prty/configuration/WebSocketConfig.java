package fi.prty.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/lobby");
		config.setApplicationDestinationPrefixes("/prty");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> converters) {
	    converters.add(new StringMessageConverter());
	    return false; // Prevent registration of default converters
	}
}
