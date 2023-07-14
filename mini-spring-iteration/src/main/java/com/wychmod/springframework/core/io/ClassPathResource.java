package com.wychmod.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.wychmod.springframework.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 类路径资源
 * @author: wychmod
 * @date: 2023/7/14
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new IOException(
                    this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
