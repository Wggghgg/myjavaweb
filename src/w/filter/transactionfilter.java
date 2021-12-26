package w.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import w.utils.DruidUtils;

public class transactionfilter implements Filter {
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {

	    }

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        try {
	            filterChain.doFilter(servletRequest,servletResponse);
	            DruidUtils.beginTx();
	            DruidUtils.endTx();
	        } catch (Exception e) {
	            try {
					DruidUtils.rollbackTx();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            e.printStackTrace();
	        }

	    }

	    @Override
	    public void destroy() {

	    }
}
