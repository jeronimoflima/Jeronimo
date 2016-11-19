package br.com.GoTripFiltro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Usuario;

public class FiltroConexao implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		
		//m�todo getContexPath � respons�vel por retornar da URL que realizou a requisi��o.
		String context = request.getServletContext().getContextPath();
		
		try{
			/*O m�todo getSession � responsavel por pegar a sess�o ativa. 
			 * Aqui foi necess�rio fazer um casting pois o objeto request � do tipo ServletRequest e n�o
			 * HttpServletRequest como no servlet onde utiliza o m�todo em quest�o sem uso do casting.
			 */
			HttpSession session = ((HttpServletRequest) request).getSession();
			Usuario usuario = null;
			
			if(session != null){
				usuario = (Usuario) session.getAttribute("login");
			}
			if(usuario == null){
				/*
				 Aqui est� sendo setado um atributo na sess�o para que depois possamos
				 exibir uma mensagem ao usu�rio.
				 */
				session.setAttribute("msg", "Voc� n�o est� logado no sitema!");
				
				/* Utilizamos o m�todo sendRedirect que altera a URL do navegador para posicionar
				 o su�rio na tela de login, que neste caso � a pagina exemploSessao.html
				 Note que n�o precisamos utulizar o recurso "../../" para informa
				 o caminho da pagina exemploSessao.html, a variavel do contexto ja posiciona no inicio da URL.
				 */
				System.out.print(context);
				
				((HttpServletResponse) response).sendRedirect(context+"/index.html");
			}else{
				/*
				 Caso exista um usu�rio valido (diferente de nulo) envia a requisi��o para
				 a pagina que se deseja acessar, ou seja, permite o acesso.
				 */
				chain.doFilter(request, response);
			}
		}catch (Exception e){
			try {
				throw new GoTripException(e);
			} catch (GoTripException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init");
	}

}
