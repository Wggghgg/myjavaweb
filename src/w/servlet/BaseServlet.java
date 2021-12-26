package w.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String methodName = req.getParameter("method");

        if (methodName != null) {
            Method method = null;

            try {
                method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();

                throw new RuntimeException("未找到Controller方法:" + methodName);
            }
            
        
            String invoke = null;

            try {
                invoke = (String) method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();

                throw new RuntimeException("方法：" + methodName + " 访问异常！");
            } catch (InvocationTargetException e) {
                e.printStackTrace();

                throw new RuntimeException("方法：" + methodName + " 执行异常！");
            }

            if (StringUtils.isNotEmpty(invoke)) {
                if (invoke.startsWith("forward:")) {
                    req.getRequestDispatcher(invoke.split(":")[1]).forward(req, resp);
                } else if (invoke.startsWith("redirect:")) {
                    resp.sendRedirect(invoke.split(":")[1]);
                }
            }
        }
    }

}
