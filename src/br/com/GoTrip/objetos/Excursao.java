package br.com.GoTrip.objetos;

import java.io.Serializable;
import java.util.Date;

public class Excursao implements Serializable {
		private static final long serialVersionUID = 1L; 
		
		private int id;
		private String nome;
		private String categoria;
		private String local;
		private Date data;
		private int totalparti;
		private int minimoparti;
		private double valor;
		private String status;
		private String descricao;
		private String imagem1;
		private String imagem2;
		private Cidade cidade;
		private Usuario usuario;
		private int contParticipante;
		
		public int getContParticipante() {
			return contParticipante;
		}
		public void setContParticipante(int totalParti) {
			this.contParticipante = totalParti;
		}
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
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
		public String getCategoria() {
			return categoria;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public String getLocal() {
			return local;
		}
		public void setLocal(String local) {
			this.local = local;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public int getTotalparti() {
			return totalparti;
		}
		public void setTotalparti(int totalparti) {
			this.totalparti = totalparti;
		}
		public int getMinimoparti() {
			return minimoparti;
		}
		public void setMinimoparti(int minimoparti) {
			this.minimoparti = minimoparti;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getImagem1() {
			return imagem1;
		}
		public void setImagem1(String imagem1) {
			this.imagem1 = imagem1;
		}
		public String getImagem2() {
			return imagem2;
		}
		public void setImagem2(String imagem2) {
			this.imagem2 = imagem2;
		}
		public Cidade getCidade() {
			return cidade;
		}
		public void setCidade(Cidade cidade) {
			this.cidade = cidade;
		}
		
		
		
		
		

}
