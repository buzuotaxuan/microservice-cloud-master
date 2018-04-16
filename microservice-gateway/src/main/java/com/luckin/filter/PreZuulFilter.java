package com.luckin.filter;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.luckin.core.CoreHeaderInterceptor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreZuulFilter extends ZuulFilter {

	private static final HashMap<String, String> TOKEN_LABEL_MAP = new HashMap<>();

	static {
		TOKEN_LABEL_MAP.put("emt", "EN,Male,Test");
		TOKEN_LABEL_MAP.put("eft", "EN,Female,Test");
		TOKEN_LABEL_MAP.put("cmt", "CN,Male,Test");
		TOKEN_LABEL_MAP.put("cft", "CN,Female,Test");
		TOKEN_LABEL_MAP.put("em", "EN,Male");
		TOKEN_LABEL_MAP.put("ef", "EN,Female");
		TOKEN_LABEL_MAP.put("cm", "CN,Male");
		TOKEN_LABEL_MAP.put("cf", "CN,Female");
	}
	
  private static final Logger LOGGER = LoggerFactory.getLogger(PreZuulFilter.class);

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    String host = request.getRemoteHost();
	String token = request.getHeader(HttpHeaders.AUTHORIZATION);
   
	String labels = TOKEN_LABEL_MAP.get(token);
	 PreZuulFilter.LOGGER.info("请求的label:{},host:{}",labels, host);

	CoreHeaderInterceptor.initHystrixRequestContext(labels); // zuul本身调用微服务
	ctx.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_LABEL, labels); // 传递给后续微服务
    return null;
  }

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

}
