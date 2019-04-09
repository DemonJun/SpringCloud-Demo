package com.demon.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: fanjunxiang
 * @date: 2019年01月24日
 **/
@Configuration
@Slf4j
public class BaseProperties {

  private static final String PROJECT_ENV_PROD = "prod";
  private static final String PROJECT_ENV_TEST = "test";
  @Value("${project.env}")
  private String env;


  public Boolean isTestEnv() {
    log.info("project env {}", env);
    return PROJECT_ENV_TEST.equals(env);
  }
}
