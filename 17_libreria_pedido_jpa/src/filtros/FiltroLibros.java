package filtros;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebFilter(urlPatterns= {"/Controller"})
public class FiltroLibros implements Filter {

    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Entra en filtro");
		HttpSession s=((HttpServletRequest)request).getSession();
		String q=(((HttpServletRequest)request).getQueryString());
		if(q.equals("op=doTemas")&&s.getAttribute("idCliente")==null) {
			((HttpServletResponse)response).sendRedirect("login.jsp");
			
		}else {
			chain.doFilter(request, response); //seguir adelante
		}
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
