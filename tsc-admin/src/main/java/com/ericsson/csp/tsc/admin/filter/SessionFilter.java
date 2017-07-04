package com.ericsson.csp.tsc.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ericsson.csp.tsc.admin.dao.entity.SysUser;
import com.ericsson.csp.tsc.admin.util.SysConstant;

public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    /*
     * if to test with soapUI, comment below method body except "chain.doFilter(arg0, arg1);"
     * **/
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(SysConstant.SESSION_USER_KEY);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login/");
            return;
        }
        chain.doFilter(arg0, arg1);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
