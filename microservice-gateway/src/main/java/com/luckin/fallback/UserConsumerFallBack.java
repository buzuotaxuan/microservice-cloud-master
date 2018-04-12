package com.luckin.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConsumerFallBack implements FallbackProvider {

	@Override
	public String getRoute() {
		return "microservice-user-consumer";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {

	    return new ClientHttpResponse() {
	      @Override
	      public HttpStatus getStatusCode() throws IOException {
	        return HttpStatus.BAD_REQUEST;
	      }

	      @Override
	      public int getRawStatusCode() throws IOException {
	        return HttpStatus.BAD_REQUEST.value();
	      }

	      @Override
	      public String getStatusText() throws IOException {
	        return HttpStatus.BAD_REQUEST.getReasonPhrase();
	      }

	      @Override
	      public void close() {
	      }

	      @Override
	      public InputStream getBody() throws IOException {
	        return new ByteArrayInputStream(("consumer fallback--->" + UserConsumerFallBack.this.getRoute()).getBytes());
	      }

	      @Override
	      public HttpHeaders getHeaders() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        return headers;
	      }
	    };
	  
	}

	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		cause.printStackTrace();
		return fallbackResponse();
	}

}
