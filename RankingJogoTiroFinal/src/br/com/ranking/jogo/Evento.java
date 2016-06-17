package br.com.ranking.jogo;

import java.util.Date;

/**
 * Classe que representa um evento
 * ex evento: Fulado killed Ciclano. 
 *
 *
 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
 * @since 15 de jun de 2016 20:20:32
 * @version 1.0
 */
public class Evento{
	
	private String arma;
	private String causa;
	private Date data;
	private String nomeJogadorMorto;
	
	public String getArma() {
		return arma;
	}
	public void setArma( String arma ) {
		this.arma = arma;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa( String causa ) {
		this.causa = causa;
	}
	public Date getData() {
		return data;
	}
	public void setData( Date data ) {
		this.data = data;
	}
	public String getNomeJogadorMorto() {
		return nomeJogadorMorto;
	}
	public void setNomeJogadorMorto( String nomeJogadorMorto ) {
		this.nomeJogadorMorto = nomeJogadorMorto;
	}
	
	@Override
	public String toString() {
		return "Evento [arma=" + arma + ", causa=" + causa + ", data=" + data + ", jogador=" + nomeJogadorMorto + "]";
	}

}
