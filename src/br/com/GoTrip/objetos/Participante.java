package br.com.GoTrip.objetos;

import java.io.Serializable;
import java.util.Date;

public class Participante implements Serializable {
		private static final long serialVersionUID = 1L; 
		/*
		* Declarar o nome dos atributos de maneira idêntica ao nome dos
		* campos do formulário que enviarão os dados para serem armazenados
		* nestes.
		*/
		private int id;
		private String nome;
		private Date data;
		private String cpf;
		private String email;
		private String telefone;
		private String sexo;
		private String rg;
		private String status;
		private Endereco endereco;
		private Excursao excursao;
		
		
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
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		public String getRg() {
			return rg;
		}
		public void setRg(String rg) {
			this.rg = rg;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Endereco getEndereco() {
			return endereco;
		}
		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}
		public Excursao getExcursao() {
			return excursao;
		}
		public void setExcursao(Excursao excursao) {
			this.excursao = excursao;
		}
		


}
