package com.example.snnu;

import com.example.snnu.config.IpConfig;
import com.example.snnu.netty.advertise.NettyServer;
import com.example.snnu.netty.lock.LockServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.example.snnu.dao")
public class SpringmvcWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcWorkApplication.class, args);
		new NettyServer(IpConfig.B_PORT).start();
//		new SendUdp().start();
//		new GetUdp().start();
	}
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("100MB");
		factory.setMaxRequestSize("100MB");
		return factory.createMultipartConfig();
	}
}
