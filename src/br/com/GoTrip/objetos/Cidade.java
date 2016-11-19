package br.com.GoTrip.objetos;

import java.io.Serializable;


public class Cidade implements Serializable {
		private static final long serialVersionUID = 1L; 
		/*
		* Declarar o nome dos atributos de maneira idêntica ao nome dos
		* campos do formulário que enviarão os dados para serem armazenados
		* nestes.
		*/
		
		private int id;
		private String nome;
		private String estado;
		
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
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		
		
		
		
		
		
		

}