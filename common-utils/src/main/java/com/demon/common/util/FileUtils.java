package com.demon.common.util;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

/**
 * @description: 文件工具类
 * @author: DemonJun
 * @date: 2018年08月28日
 **/
@SuppressWarnings("ALL")
public class FileUtils {

  /**
   * <p> 计算文件hsash值   </p>
   *
   * @param file 待计算hash文件
   * @return 文件hash值
   * @author DemonJun
   * @date 2018/8/28
   */
  public static String getFileHashCode(File file) {
    try {
      return Files.asByteSource(file).hash(Hashing.hmacMd5("JY".getBytes())).toString();
    } catch (IOException e) {
      return null;
    }
  }
}
