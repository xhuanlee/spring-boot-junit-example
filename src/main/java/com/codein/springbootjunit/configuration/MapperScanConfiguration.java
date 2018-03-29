package com.codein.springbootjunit.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lxh on 2018/3/29.
 */
@Configuration
@MapperScan(value = "com.codein.springbootjunit.dao")
public class MapperScanConfiguration {
}
