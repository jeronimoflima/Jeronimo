package br.com.GoTrip.objetos;

import java.io.Serializable;


public class ParticipanteExcursao implements Serializable {
		private static final long serialVersionUID = 1L; 
		
		
		private Participante participante;
		private int idExcursao;
		
		
		
		public int getIdExcursao() {
			return idExcursao;
		}
		public void setIdExcursao(int idExcursao) {
			this.idExcursao = idExcursao;
		}
		public Participante getParticipante() {
			return participante;
		}
		public void setParticipante(Participante participante) {
			this.participante = participante;
		}
		
		

}