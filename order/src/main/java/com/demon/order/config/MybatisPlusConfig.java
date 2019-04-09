package com.demon.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description: Mybatis Plus配置类
 * @author: jundemon
 * @date: 2018年08月10日
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.demon.order.mapper"})
public class MybatisPlusConfig {

  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }


}
