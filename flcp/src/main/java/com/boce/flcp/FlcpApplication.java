package com.boce.flcp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.UnknownHostException;

@SpringBootApplication
@EnableScheduling // 启用定时任务
public class FlcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlcpApplication.class, args);
	}

	//spring boot默认自动配置了RedisTemplate，而RedisTemplate使用的是jdk序列化策略，在测试的时候演示很不直观，
	//因为jdk使用二级制形式存储数据(Object)，因此自己配置RedisTemplate并定义序列化方式
	@Bean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
			throws UnknownHostException {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(redisConnectionFactory);

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//GenericJackson2JsonRedisSerializer 支持多种不同类型的domain对象
		template.setValueSerializer(jackson2JsonRedisSerializer); //1设置值的序列采用jackson2JsonRedisSerializer
		template.setKeySerializer(new StringRedisSerializer()); //2设置键的序列采用StringRedisSerializer

		template.afterPropertiesSet();
		return template;
	}
}
