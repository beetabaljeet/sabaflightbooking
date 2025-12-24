
package com.sababooking.SabaBookingEngine.config;

import com.sababooking.SabaBookingEngine.properties.AmadeusProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(AmadeusProperties.class)
public class WebClientConfig {

    @Bean
    public WebClient amadeusWebClient(AmadeusProperties props) {
        // Build Reactor Netty HttpClient with connect + read + response timeouts
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, props.getConnectTimeoutMs())
                // overall response timeout (from connect to fully received response)
                .responseTimeout(Duration.ofMillis(props.getResponseTimeoutMs()))
                .doOnConnected(conn ->
                        // socket read timeout (per read operation)
                        conn.addHandlerLast(new ReadTimeoutHandler(props.getReadTimeoutMs(), TimeUnit.MILLISECONDS))
                );

        return WebClient.builder()
                .baseUrl(props.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
