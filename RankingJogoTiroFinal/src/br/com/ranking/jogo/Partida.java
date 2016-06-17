package br.com.ranking.jogo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe que representa uma partida!
 *
 *
 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
 * @since 15 de jun de 2016 15:36:30
 * @version 1.0
 */
public class Partida{
	
	private Long numero;
	private Date dataInicio;
	private Date dataTermino;
	private ArrayList< Jogador > jogadores;
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero( Long numero ) {
		this.numero = numero;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio( Date dataInicio ) {
		this.dataInicio = dataInicio;
	}
	public Date getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino( Date dataTermino ) {
		this.dataTermino = dataTermino;
	}
	public ArrayList< Jogador > getJogadores() {
		if(jogadores == null){
			jogadores = new ArrayList<Jogador>();
		}
		return jogadores;
	}
	public void setJogadores( ArrayList< Jogador > jogadores ) {
		this.jogadores = jogadores;
	}
	
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append( "Partida [numero=" + numero + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + "\n\t" );
		
		for ( Jogador jogadorCorrente : jogadores ) {
			stringBuffer.append( jogadorCorrente.toString() + "\n\t" );
		}
		return stringBuffer.toString();
	}
	

}
