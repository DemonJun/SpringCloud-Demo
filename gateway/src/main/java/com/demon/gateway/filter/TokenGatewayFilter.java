package com.demon.gateway.filter;


import static com.demon.common.constant.ResponseCode.FAILED_CODE;
import static com.demon.common.constant.ResponseCode.FAILED_MESSAGE;
import static com.demon.gateway.contant.ResponseCode.ACCOUNT_HAS_EXPIRED_CODE;
import static com.demon.gateway.contant.ResponseCode.ACCOUNT_HAS_EXPIRED_MESSAGE;

import com.alibaba.fastjson.JSON;
import com.demon.common.model.CustomResponse;
import com.demon.gateway.config.GatewayFilterConfigProperties;
import com.demon.gateway.util.ListUtils;
import com.demon.gateway.util.ResponseUtil;
import com.demon.gateway.util.TokenUtils;
import com.google.common.reflect.TypeToken;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: fanjunxiang
 * @date: 2019年01月15日
 **/
@Slf4j
@Component
@SuppressWarnings("ALL")
public class TokenGatewayFilter implements GatewayFilter, Ordered {

  private final StringRedisTemplate redisTemplate;
  private final GatewayFilterConfigProperties gatewayFilterConfigProperties;
  @Value("${gateway.filter.enable:true}")
  private Boolean enable;

  @Autowired
  public TokenGatewayFilter(StringRedisTemplate redisTemplate,
      GatewayFilterConfigProperties gatewayFilterConfigProperties) {
    this.redisTemplate = redisTemplate;
    this.gatewayFilterConfigProperties = gatewayFilterConfigProperties;
  }


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    ServerHttpResponse response = exchange.getResponse();
    try {
      if (!enable) {
        return chain.filter(exchange);
      }
      // 请求头不需要token的接口地址
      List<String> ignoresPathList = gatewayFilterConfigProperties.getIgnoresPathList();
      List<String> prefixList = gatewayFilterConfigProperties.getRoutePrefixList();
      URI uri = request.getURI();
      String requestPath = uri.getPath();
      String prefix = ListUtils.containPrefix(prefixList, requestPath);
      if (null != prefix) {
        requestPath = requestPath.replace(prefix, "");
      }

      //此处采用uri参数形式写入，便于和request中的requestBody中参数进行区分
      StringBuilder queryParams = new StringBuilder();
      String originalQuery = uri.getRawQuery();

      if (org.springframework.util.StringUtils.hasText(originalQuery)) {
        queryParams.append(originalQuery);
      }
      //判断请求地址是否为不需要验证的路径
      boolean isIgnore = ignoresPathList.contains(requestPath);
      //如果不需要验证，直接放行
      if (isIgnore) {
        return ResponseUtil.doFilter(exchange, chain, queryParams.toString());
      }
      //需要token，获取token，解析token
      String token = request.getHeaders().getFirst(TokenUtils.TOKEN_HEADER);
      if (StringUtils.isBlank(token)) {
        return this.userAccountHasExpired(response);
      }
      Map<String, String> tokenMap = TokenUtils.getObjectJsonFromToken(token);
      //缓存未命中token，说明token过期
      String cacheToken = redisTemplate.boundValueOps(tokenMap.get(TokenUtils.CACHE_KEY))
          .get();
      if (StringUtils.isBlank(cacheToken) || !token.equals(cacheToken)) {
        return this.userAccountHasExpired(response);
      }
      //token未过期，将token中的信息转成request中参数
      Map<String, Object> tokenInfoMap = JSON
          .parseObject(tokenMap.get(TokenUtils.TOKEN_DATA),
              new TypeToken<Map<String, String>>() {
              }.getType());

      tokenInfoMap.forEach((k, v) -> {
        if (!"".equals(k)) {
          queryParams
              .append(URLEncoder.encode(k, StandardCharsets.UTF_8))
              .append("=")
              .append(URLEncoder.encode(String.valueOf(v), StandardCharsets.UTF_8))
              .append("&");
        }
      });

      return ResponseUtil.doFilter(exchange, chain, queryParams.toString());
    } catch (Exception ex) {
      log.error("请求处理失败，失败异常：{}", ex);
      return ResponseUtil.writeResponse(response,
          CustomResponse.builder()
              .code(FAILED_CODE)
              .message(FAILED_MESSAGE)
              .build()
      );
    }
  }

  @Override
  public int getOrder() {
    return 0;
  }


  private Mono<Void> userAccountHasExpired(ServerHttpResponse response) {
    CustomResponse tidings = CustomResponse.builder()
        .code(ACCOUNT_HAS_EXPIRED_CODE)
        .message(ACCOUNT_HAS_EXPIRED_MESSAGE)
        .build();

    return ResponseUtil.writeResponse(response, tidings);
  }
}
