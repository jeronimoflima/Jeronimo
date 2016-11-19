package br.com.GoTrip.objetos;

import java.io.Serializable;


public class Endereco implements Serializable {
		private static final long serialVersionUID = 1L; 
		/*
		* Declarar o nome dos atributos de maneira idêntica ao nome dos
		* campos do formulário que enviarão os dados para serem armazenados
		* nestes.
		*/
		
		private int id;
		private String nome;
		private int numero;
		private String bairro;
		private int cep;
		private String complemento;
		private Cidade cidade;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getNumero() {
			return numero;
		}
		public void setNumero(int numero) {
			this.numero = numero;
		}
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}
		public int getCep() {
			return cep;
		}
		public void setCep(int cep) {
			this.cep = cep;
		}
		public Cidade getCidade() {
			return cidade;
		}
		public void setCidade(Cidade cidade) {
			this.cidade = cidade;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		
		
		
		
		

}
