package com.demon.gateway.route;

import com.demon.gateway.filter.TokenGatewayFilter;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.stereotype.Component;

/**
 * @description: 路由构建器
 * @author: fanjunxiang
 * @date: 2019年01月15日
 **/
@Component
public class RouteBuilder {

  private final TokenGatewayFilter tokenGatewayFilter;

  @Autowired
  public RouteBuilder(TokenGatewayFilter tokenGatewayFilter) {
    this.tokenGatewayFilter = tokenGatewayFilter;
  }

  public Function<PredicateSpec, Route.AsyncBuilder> orderRoute(String prefix) {
    StringBuilder path = new StringBuilder("/order/**");
    StringBuilder uri = new StringBuilder("lb://order-service");
    StringBuilder id = new StringBuilder("order-service");

    return this.getPredicateSpecBuilderFunction(prefix, path, uri, id);
  }

  public Function<PredicateSpec, Route.AsyncBuilder> goodsRoute(String prefix) {
    StringBuilder path = new StringBuilder("/goods/**");
    StringBuilder uri = new StringBuilder("lb://goods-service");
    StringBuilder id = new StringBuilder("goods-service");

    return this.getPredicateSpecBuilderFunction(prefix, path, uri, id);
  }

  private Function<PredicateSpec, Route.AsyncBuilder> getPredicateSpecBuilderFunction(String prefix,
      StringBuilder path, StringBuilder uri, StringBuilder id) {
    if (null != prefix) {
      path.insert(0, prefix);
      uri.append("-").append(prefix.replace("/", ""));
      id.append("-").append(prefix.replace("/", ""));
    }

    String finalPath = path.toString();
    String finalUri = uri.toString();
    String finalId = id.toString();

    if (null != prefix) {
      return r -> r.path(finalPath)
          .filters(f -> f
              .filter(tokenGatewayFilter)
              .rewritePath(prefix + "/(?<uri>.*)", "/${uri}")
          )
          .uri(finalUri)
          .id(finalId);
    } else {
      return r -> r.path(finalPath)
          .filters(f -> f
              .filter(tokenGatewayFilter)
          )
          .uri(finalUri)
          .id(finalId);
    }

  }
}
