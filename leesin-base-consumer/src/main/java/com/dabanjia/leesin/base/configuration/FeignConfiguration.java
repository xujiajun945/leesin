package com.dabanjia.leesin.base.configuration;

import com.alibaba.fastjson.JSON;
import com.dabanjia.vayne.core.exception.ResponseException;
import com.dabanjia.vayne.core.model.ResponseData;
import feign.*;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author xujiajun
 * @since 2019/12/25
 */
public class FeignConfiguration {

	private static final int CONNECT_TIMEOUT_MILLIS = 2000;

	private static final int READ_TIMEOUT_MILLIS = 6000;

	@Bean
	public Request.Options options() {
		return new Request.Options(CONNECT_TIMEOUT_MILLIS, READ_TIMEOUT_MILLIS);
	}

	@Bean
	public Retryer retryer() {
		return new Retryer.Default();
	}

	@Bean
	public Contract contract() {
		return new SpringMvcContract();
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new UserErrorDecoder();
	}

	@Bean
	public Decoder decoder() {
		return new ClassicDecoder();
	}

	@Bean
	public feign.Logger.Level feignLoggerLevel() {
		return feign.Logger.Level.FULL;
	}

	public static class ClassicDecoder implements Decoder {

		@Override
		public Object decode(Response response, Type type) throws FeignException, IOException {
			String json = Util.toString(response.body().asReader());
			ResponseData responseData = JSON.parseObject(json, ResponseData.class);
			Object data = responseData.getData();
			if (data != null) {
				return JSON.parseObject(data.toString(), type);
			}
			return null;
		}
	}

	/**
	 * 自定义错误
	 */
	@Slf4j
	public static class UserErrorDecoder implements ErrorDecoder {

		@Override
		public Exception decode(String methodKey, Response response) {
			Exception exception = null;
			try {
				// 获取原始的返回内容
				String json = Util.toString(response.body().asReader());
				ResponseData responseData = JSON.parseObject(json, ResponseData.class);
				// 若状态码为400,抛出ResponseException
				if (response.status() == HttpStatus.BAD_REQUEST.value()) {
					exception = new ResponseException(responseData.getCode(), responseData.getMessage());
				}
			} catch (IOException ex) {
				log.error(ex.getMessage(), ex);
			}
			return exception;
		}
	}
}
