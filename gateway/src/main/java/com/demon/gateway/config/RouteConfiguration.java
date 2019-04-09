package com.demon.gateway.config;

import com.demon.gateway.route.RouteBuilder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 路由配置器
 * @author: fanjunxiang
 * @date: 2019年01月15日
 **/
@Configuration
@AutoConfigureAfter({GatewayFilterConfigProperties.class, BaseProperties.class})
@Slf4j
public class RouteConfiguration {

  private final GatewayFilterConfigProperties gatewayFilterConfigProperties;
  private final BaseProperties baseProperties;

  @Autowired
  public RouteConfiguration(GatewayFilterConfigProperties gatewayFilterConfigProperties,
      BaseProperties baseProperties) {
    this.gatewayFilterConfigProperties = gatewayFilterConfigProperties;
    this.baseProperties = baseProperties;
  }

  @Bean
  public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder,
      RouteBuilder routeBuilder) {
    List<String> prefixList = gatewayFilterConfigProperties.getRoutePrefixList();

    log.info("routePrefixList is {}", prefixList);
    RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
    routes.route(routeBuilder.orderRoute(null))
        .route(routeBuilder.goodsRoute(null));

    if (baseProperties.isTestEnv() && null != prefixList) {
      prefixList.forEach(prefix ->
          routes.route(routeBuilder.orderRoute(prefix))
              .route(routeBuilder.goodsRoute(prefix))
      );
    }

    return routes.build();
  }
}
