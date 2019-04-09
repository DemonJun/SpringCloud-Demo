package com.demon.order.config;


import com.demon.common.exception.ServiceExceptionAdvice;
import com.demon.common.log.ControllerLogAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jundemon
 */
@Configuration
public class OrderConfiguration {

  private final BaseProperties baseProperties;

  @Autowired
  public OrderConfiguration(BaseProperties baseProperties) {
    this.baseProperties = baseProperties;
  }

  @Bean
  public ServiceExceptionAdvice serviceExceptionAdvice() {
    return new ServiceExceptionAdvice();
  }


  @Bean
  public ControllerLogAspect controllerLogAspect() {
    return new ControllerLogAspect();
  }

}
