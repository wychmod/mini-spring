package com.wychmod.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 资源处理接口
 * 提供获取 InputStream 流的方法，接下来再分别实现三种不同的流文件操作：classPath、FileSystem、URL
 * @author: wychmod
 * @date: 2023/7/14
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
