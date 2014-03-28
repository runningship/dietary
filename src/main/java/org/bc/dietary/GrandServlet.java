package org.bc.dietary;

import java.io.IOException;
import java.util.logging.Level;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.bc.dietary.web.ModelAndView;
import org.bc.dietary.web.ModuleManager;
import org.bc.sdak.GException;
import org.bc.sdak.utils.LogUtil;


public class GrandServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		LogUtil.info(path);
		if("/".equals(path)){
			processRootRequest(req, resp);
			return;
		}
		String module = ServletHelper.getModule(path);
		String method = ServletHelper.getMethod(path);
		Object manager = ModuleManager.getModuleInstance(module);
		if(manager==null){
			resp.getWriter().println("404 : page not found");
			return;
		}
        
		try{
			Object[] params = buildParamForMethod(manager,method,req);
			ModelAndView mv = ServletHelper.call(manager,method,params);
			if(mv.jsp==null){
				resp.getWriter().println(mv.data.toString());
			}else{
				ServletHelper.fillMV(req,mv);
				RequestDispatcher rd = req.getRequestDispatcher(mv.jsp);
				if(rd==null){
					resp.getWriter().println("404 : page not found");
				}else{
					rd.forward(req, resp);
				}
			}
		}catch(GException ex){
			String msg = ex.getMessage();
			if(StringUtils.isEmpty(msg)){
				msg = ex.getStackTrace()[0].toString();
			}
			resp.getWriter().println(msg);
		}catch(Exception ex){
			ex.printStackTrace(resp.getWriter());
			//go to error page 
			LogUtil.log(Level.SEVERE,"internal server error",ex);
		}finally{
			
		}
	}

	private void processRootRequest(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.getWriter().println("you are not allowed to access root url");
	}
	
	private Object[] buildParamForMethod(Object manager,String method, HttpServletRequest req){
		Object[] params = null;
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = null;
		try {
			cc = pool.getCtClass(manager.getClass().getName());
		} catch (NotFoundException e) {
			pool.appendClassPath(new ClassClassPath(manager.getClass()));
		}
		if(cc==null){
			//get again
			try {
				cc = pool.getCtClass(manager.getClass().getName());
			} catch (NotFoundException ex) {
				LogUtil.log(Level.WARNING, "class not found", ex);
				return new Object[]{};
			}
		}
		for(CtMethod cm : cc.getDeclaredMethods()){
			if(cm.getName().equals(method)){
				LogUtil.info("start to build parameters for "+manager.getClass().getName()+"."+method);
				params = ServletHelper.buildParamters(cm , req);
			}
		}
		return params;
	}
}
