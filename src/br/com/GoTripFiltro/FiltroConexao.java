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
		
		//método getContexPath é responsável por retornar da URL que realizou a requisição.
		String context = request.getServletContext().getContextPath();
		
		try{
			/*O método getSession é responsavel por pegar a sessão ativa. 
			 * Aqui foi necessário fazer um casting pois o objeto request é do tipo ServletRequest e não
			 * HttpServletRequest como no servlet onde utiliza o método em questão sem uso do casting.
			 */
			HttpSession session = ((HttpServletRequest) request).getSession();
			Usuario usuario = null;
			
			if(session != null){
				usuario = (Usuario) session.getAttribute("login");
			}
			if(usuario == null){
				/*
				 Aqui está sendo setado um atributo na sessão para que depois possamos
				 exibir uma mensagem ao usuário.
				 */
				session.setAttribute("msg", "Você não está logado no sitema!");
				
				/* Utilizamos o método sendRedirect que altera a URL do navegador para posicionar
				 o suário na tela de login, que neste caso é a pagina exemploSessao.html
				 Note que não precisamos utulizar o recurso "../../" para informa
				 o caminho da pagina exemploSessao.html, a variavel do contexto ja posiciona no inicio da URL.
				 */
				System.out.print(context);
				
				((HttpServletResponse) response).sendRedirect(context+"/index.html");
			}else{
				/*
				 Caso exista um usuário valido (diferente de nulo) envia a requisição para
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
