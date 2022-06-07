package ksmart.mybatis.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import ksmart.mybatis.controller.MemberController;

import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor{
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	/* HandlerMapping이 핸들러 객체를 결정한 후 HandlerAdapter가 핸들러를 
       호출하기 전에 호출되는 메소드
       return true(cotroller 진입), false(cotroller 진입 차단)
    */
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
           throws Exception {
	   Set<String> paramKeys = request.getParameterMap().keySet();
	   
	   
	   StringJoiner param = new StringJoiner(",");
	   if(paramKeys != null) {
		   
		   for(String key : paramKeys) {
			   param.add(key + ":" + request.getParameter(key));
		   }
	   }
	   
	   log.info("AccessInfo=======================================================");
	   log.info("PORT          ::::    {}", request.getLocalPort());
	   log.info("SERVERname    ::::    {}", request.getServerName());
	   log.info("URI           ::::    {}", request.getRequestURI());
	   log.info("HTTPMETHOD    ::::    {}", request.getMethod());
	   if(param != null) {
		   log.info("PARAMETER     ::::    {}", param);
	   }
	   log.info("AccessInfo=======================================================preHandlerend");
	   
   
       return HandlerInterceptor.super.preHandle(request, response, handler);
       //true
   }
   /* 
    *   매개변수: HttpServletRequest, HttpServletResponse, Object handler, ModelAndView  
		HandlerAdapter가 실제로 핸들러를 호출한 후 DispatcherServlet이 뷰를 
		렌더링하기 전에 호출되는 메소드 
		ModelAndView를 통해 뷰에 추가 모델 객체를 노출  

    */
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
           ModelAndView modelAndView) throws Exception {
       log.info("postHandler=======================================================");
       HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
   }
   /*
    * 매개변수: HttpServletRequest, HttpServletResponse, Object handler
      DispatcherServlet이 뷰를 렌더링하기 전에 호출되는 메소드
    */
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
           throws Exception {
	   log.info("afterCompletion=======================================================");
       HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
   }
}
