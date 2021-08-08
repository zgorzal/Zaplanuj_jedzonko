package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private String charsetEncoding = "UTF-8";
    private String contentType = "text/html";
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(charsetEncoding);
        response.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        chain.doFilter(request, response);
    }
}
