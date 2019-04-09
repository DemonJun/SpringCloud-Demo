package com.demon.common.cache;


/**
 * @description: 缓存管理组件
 * @author: xueyongxin
 * @date: 2018年08月30日
 **/


public class CacheBuild {

  /**
   * <p>  构建缓存KEy  </p>
   *
   * @return java.lang.String
   * @author xuhao
   * @date 2018/10/10
   */
  public static String keyBuilder(String prefix, String key) {
    return prefix + key;
  }


}
