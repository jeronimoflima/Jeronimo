package br.com.GoTripLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.GoTrip.bd.conexao.Conexao;
import br.com.GoTrip.criptografia.ConverteMd5;
import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.jdbc.JDBCLoginDAO;
import br.com.GoTrip.objetos.Usuario;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, GoTripException {

		String context = request.getServletContext().getContextPath();
		Usuario usa = new Usuario();

		usa.setEmail(request.getParameter("email"));
		usa.setSenha(request.getParameter("senha"));
		Conexao conec = new Conexao();

		try {

			Map<String, String> msg = new HashMap<String, String>();
			Connection conexao = conec.abrirConexao();
			JDBCLoginDAO jdbcLogin = new JDBCLoginDAO(conexao);

			String senha = ConverteSenha(usa.getSenha());
			usa.setSenha(senha);
			Usuario usuario = jdbcLogin.login(usa);
			
			

			HttpSession sessao = request.getSession();

			if (usuario != null) {

				sessao.setAttribute("login", usuario);
				
				response.sendRedirect(usuario.getTipo() == 1 ? context + "/private/usuario/gestao_administrador.html":context+"/private/usuario/gestao_organizador.html");
				//condicao ? (se condicao for verdadeira) :(else) executa condicao do else
			} else {
				msg.put("msg", "Erro Login");
				sessao.setAttribute("msg", "Usuário ou senha inválido");
				response.sendRedirect(context + "/index.html");

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conec.fecharConexao();

		}

	}

	private String ConverteSenha(String senha) {


		byte[] decoded = Base64.getDecoder().decode(senha);
		senha = new String(decoded, StandardCharsets.UTF_8);

		String senhaMd5 = "";
		try {
			senhaMd5 = ConverteMd5.converteMd5(senha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return senhaMd5;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			process(request, response);
		} catch (GoTripException e) {
			e.printStackTrace();
		}

	}

}