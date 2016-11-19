package br.com.GoTrip.objetos;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
		private static final long serialVersionUID = 1L; 
		
		private int id;
		private int tipo;
		private String nome;
		private String cpf;
		private String telefone;
		private String email;
		private String senha;
		private String confirmasenha;
		private Date data;
		private Endereco endereco;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getTipo() {
			return tipo;
		}
		public void setTipo(int tipo) {
			this.tipo = tipo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String getConfirmasenha() {
			return confirmasenha;
		}
		public void setConfirmasenha(String confirmasenha) {
			this.confirmasenha = confirmasenha;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public Endereco getEndereco() {
			return endereco;
		}
		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}
		
		
		
		
		
}
