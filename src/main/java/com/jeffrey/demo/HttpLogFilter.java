package com.jeffrey.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class HttpLogFilter extends OncePerRequestFilter{

	private String beforeMessagePrefix = "";

	private String afterMessagePrefix = "";

	public String getBeforeMessagePrefix() {
		return beforeMessagePrefix;
	}

	public void setBeforeMessagePrefix(String beforeMessagePrefix) {
		this.beforeMessagePrefix = beforeMessagePrefix;
	}

	public String getAfterMessagePrefix() {
		return afterMessagePrefix;
	}

	public void setAfterMessagePrefix(String afterMessagePrefix) {
		this.afterMessagePrefix = afterMessagePrefix;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		boolean isFirstRequest = !isAsyncDispatch(request);
		HttpServletRequest requestToUse = new ContentCachingRequestWrapper(request, 1000);
		boolean shouldLog = true;

		if (isFirstRequest) {
			logger.info(String.format("%s%s url=%s%s",
					beforeMessagePrefix, requestToUse.getMethod(), requestToUse.getRequestURI(),
					request.getQueryString() != null ? "?"+request.getQueryString() : ""));
		}
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
		try {
			filterChain.doFilter(requestToUse, wrappedResponse);
		}catch(Exception e)
		{
			logger.info(String.format("%shttpCode=%d, message=%s",
					afterMessagePrefix, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e));
			shouldLog =false;
			throw e;
		}
		finally {
			if (shouldLog && !isAsyncStarted(requestToUse)) {
				int code = wrappedResponse.getStatus();
				if(code >= 200 && code < 300) {
					logger.info(String.format("%shttpCode=%d", afterMessagePrefix, code));
				}else {
					String contect = new String(wrappedResponse.getContentAsByteArray());
					logger.info(String.format("%shttpCode=%d, message=%s", afterMessagePrefix, code, contect));
				}
			}
			wrappedResponse.copyBodyToResponse();
		}
	}

}
