package com.demon.gateway.config;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: fanjunxiang
 * @date: 2019年01月15日
 **/
@Configuration
@ConfigurationProperties(prefix = "gateway.filter")
@Data
@Slf4j
public class GatewayFilterConfigProperties {

  private Boolean enable;
  private List<String> routePrefixList = new ArrayList<>();
  private List<String> ignoresPathList = new ArrayList<>();
}
