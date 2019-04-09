package com.demon.common.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {


  /**
   * 生成随机盐，用于加密凭证
   */
  public static String generatorSalt() {
    Random random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    return new String(salt, StandardCharsets.UTF_8);
  }

}
