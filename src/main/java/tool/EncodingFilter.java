package tool;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain
	) throws IOException, ServletException  {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("フィルタの前処理");
		
		//chain.doFilter(request, response);
		try {
            chain.doFilter(request, response);
        } catch (Throwable t) {// ★ ここで何が起きても必ず表示
            t.printStackTrace();// Eclipse のコンソールに例外の詳細が出る
            if (t instanceof ServletException) {
                throw (ServletException) t;
            } else if (t instanceof IOException) {
                throw (IOException) t;
            } else {
                throw new ServletException(t);
            }
        }
		System.out.println("フィルタの後処理");
	}
	
	public void init(FilterConfig filterconfig) {
		
	}
	
	public void destory() {
		
	}
	
}
