package com.demon.gateway.util;

import com.alibaba.fastjson.JSON;
import com.demon.common.model.CustomResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: fanjunxiang
 * @date: 2018年12月19日
 **/
public class ResponseUtil {

  public static Mono<Void> writeResponse(ServerHttpResponse response, CustomResponse customResponse) {
    response.setStatusCode(HttpStatus.OK);
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

    return response.writeWith(Mono.just(response.bufferFactory()
        .wrap(JSON.toJSONString(customResponse).getBytes(StandardCharsets.UTF_8))));
  }


  public static Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain,
      String queryParams) {
    URI newUri = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
        .replaceQuery(queryParams)
        .build(true)
        .toUri();
    ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();

    return chain.filter(exchange.mutate().request(request).build());
  }
}
