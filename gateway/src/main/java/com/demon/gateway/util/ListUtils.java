package com.demon.gateway.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: fanjunxiang
 * @date: 2019年01月15日
 **/
public class ListUtils {

  /**
   * 判断请求地址中是否包含前缀列表中的前缀，如果存在则返回该前缀，不存在则返回空
   *
   * @param prefixList 前缀列表中
   * @param url 请求地址
   * @return 前缀 or null
   */
  public static String containPrefix(List<String> prefixList, String url) {
    if (null == prefixList) {
      return null;
    }

    AtomicReference<String> prefix = new AtomicReference<>(null);
    prefixList.forEach(tempPrefix -> {
      if (url.contains(tempPrefix)) {
        prefix.set(tempPrefix);
      }
    });

    return prefix.get();
  }
}
