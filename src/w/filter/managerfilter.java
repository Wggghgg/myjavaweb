package w.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import w.bean.User;

public class managerfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object user =httpServletRequest.getSession().getAttribute("user");
        if(user==null){
            httpServletRequest.getRequestDispatcher("cannotmanage.jsp").forward(servletRequest,servletResponse);
        }else {
            User user1 = (User) user;
            if("admin".equals(user1.getUsername())){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                httpServletRequest.getRequestDispatcher("cannotmanage.jsp").forward(servletRequest,servletResponse);
            }

        }
    }
}
