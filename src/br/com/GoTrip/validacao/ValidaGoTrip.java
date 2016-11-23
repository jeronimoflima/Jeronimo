package br.com.GoTrip.validacao;

import java.util.Date;

import br.com.GoTrip.exception.GoTripException;
import br.com.GoTrip.objetos.Excursao;
import br.com.GoTrip.objetos.Usuario;
import br.com.GoTrip.objetos.Participante;


public class ValidaGoTrip {

	public static void validarParticipante(Participante participante) throws GoTripException {
		String msgTel = "";
		String msg = "";

		msg += ValidaMSGString(participante.getNome(), "Nome");
		msg += ValidaMSGDate(participante.getData(), "Data de Nascimento");
		msg += ValidaMSGString(participante.getCpf(), "CPF");
		msg += ValidaMSGString(participante.getEmail(), "Email");
		msg += ValidaMSGString(participante.getTelefone(), "Telefone");
		msg += ValidaMSGString(participante.getSexo(), "Sexo");
		msg += ValidaMSGString(participante.getRg(), "RG");
		msg += ValidaMSGString(participante.getStatus(), "Status");
		msg += ValidaMSGString(participante.getEndereco().getNome(), "Endereco");
		msg += ValidaMSGInteiro(participante.getEndereco().getNumero(), "Número");
		msg += ValidaMSGString(participante.getEndereco().getBairro(), "Bairro");
		msg += ValidaMSGInteiro(participante.getEndereco().getCep(), "CEP");
		//msg += ValidaMSGString(participante.getEndereco().getComplemento(), "Complemento");
		msg += ValidaMSGString(participante.getEndereco().getCidade().getNome(), "Cidade");
		msg += ValidaMSGString(participante.getEndereco().getCidade().getEstado(), "Estado");

		if (participante.getTelefone().equals("")) {
			msg += msgTel;
			

		} else if (!participante.getTelefone().matches("([1-9]{2})[0-9]{4}[0-9]{4}")) {
			msg += "Campo Telefone Inválido";
		}
		if (!msg.equals("")) {
			
			throw new GoTripException(msg);

		}

		

	}

	public static void validarAdm(Usuario administrador) throws GoTripException {
		String msgTel = "";
		String msg = "";

		msg += ValidaMSGString(administrador.getNome(), "Nome");
		msg += ValidaMSGString(administrador.getCpf(), "CPF");
		msg += ValidaMSGString(administrador.getTelefone(), "Telefone");
		msg += ValidaMSGString(administrador.getEmail(), "Email");
		msg += ValidaMSGString(administrador.getSenha(), "Senha");

		if (administrador.getTelefone().equals("")) {
			msg += msgTel;
			

		} else if (!administrador.getTelefone().matches("([1-9]{2})[0-9]{4}[0-9]{4}")) {
			msg += "Campo Telefone Inválido";
		}
		if (!msg.equals("")) {
			
			throw new GoTripException(msg);

		}

		

	}
	
	public static void validarOrga(Usuario organizador) throws GoTripException {
		String msgTel = "";
		String msg = "";

		msg += ValidaMSGString(organizador.getNome(), "Nome");
		msg += ValidaMSGDate(organizador.getData(), "Data de Nascimento");
		msg += ValidaMSGString(organizador.getCpf(), "CPF");
		msg += ValidaMSGString(organizador.getEmail(), "Email");
		msg += ValidaMSGString(organizador.getTelefone(), "Telefone");
		msg += ValidaMSGString(organizador.getSenha(), "Senha");
		msg += ValidaMSGString(organizador.getEndereco().getNome(), "Endereco");
		msg += ValidaMSGInteiro(organizador.getEndereco().getNumero(), "Número");
		msg += ValidaMSGString(organizador.getEndereco().getBairro(), "Bairro");
		msg += ValidaMSGInteiro(organizador.getEndereco().getCep(), "CEP");
		//msg += ValidaMSGString(organizador.getEndereco().getComplemento(), "Complemento");
		msg += ValidaMSGString(organizador.getEndereco().getCidade().getNome(), "Cidade");
		msg += ValidaMSGString(organizador.getEndereco().getCidade().getEstado(), "Estado");

		if (organizador.getTelefone().equals("")) {
			msg += msgTel;
			

		} else if (!organizador.getTelefone().matches("([1-9]{2})[0-9]{4}[0-9]{4}")) {
			msg += "Campo Telefone Inválido";
		}
		if (!msg.equals("")) {
			
			throw new GoTripException(msg);

		}

		

	}
	
	public static void validarExcur(Excursao excursao) throws GoTripException {
		String msg = "";

		msg += ValidaMSGString(excursao.getNome(), "Título");
		msg += ValidaMSGString(excursao.getCategoria(), "Categoria");
		msg += ValidaMSGString(excursao.getCidade().getNome(), "Cidade");
		msg += ValidaMSGString(excursao.getCidade().getEstado(), "Estado");
		msg += ValidaMSGString(excursao.getLocal(), "Local de Saída");
		msg += ValidaMSGInteiro(excursao.getTotalparti(), "Total Participantes");
		msg += ValidaMSGDate(excursao.getData(), "Data de Saída");
		msg += ValidaMSGInteiro(excursao.getMinimoparti(), "Mínimo Participantes");
		msg += ValidaMSGDouble(excursao.getValor(), "Valor");
		msg += ValidaMSGString(excursao.getDescricao(), "Descrição");
		//msg += ValidaMSGBytes(excursao.getImagem1(), "Imagem1");
		//msg += ValidaMSGBytes(excursao.getImagem2(), "Imagem2");
		msg += ValidaMSGString(excursao.getStatus(), "Status");

		if (!msg.equals("")) {
			
			throw new GoTripException(msg);
		}

	}

	private static String ValidaMSGDate(Date data, String campo) {

		String msg = "";

		if (data.equals("")) {
			msg += "<br/>Preencha o campo " + campo;
		}
		return msg;

	}

	private static String ValidaMSGString(String nome, String campo) {
		String msg = "";

		if (nome.trim().equals("")) {
			msg += "<br/>Preencha o campo " + campo;
		}
		return msg;

	}
	
	private static String ValidaMSGInteiro(int nome, String campo) {
		String msg = "";

		if (nome==0) {
			msg += "<br/>Preencha o campo " + campo;
		}
		return msg;

	}
	
	private static String ValidaMSGDouble(double nome, String campo) {
		String msg = "";

		if (nome==0) {
			msg += "<br/>Preencha o campo " + campo;
		}
		return msg;

	}
	
	private static String ValidaMSGBytes(byte[] nome, String campo) {
		String msg = "";

		if (nome.equals("")) {
			msg += "<br/>Preencha o campo " + campo;
		}
		return msg;

	}

}
