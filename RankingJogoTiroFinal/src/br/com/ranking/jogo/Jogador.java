package br.com.ranking.jogo;

import java.util.ArrayList;

/**
 * Classe que representa um Jogador
 *
 *
 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
 * @since 15 de jun de 2016 16:36:29
 * @version 1.0
 */
public class Jogador{
	
	private String nome;
	private ArrayList< Evento > mortes = new ArrayList<>();
	private ArrayList< Evento > assassinatos = new ArrayList<>();
	
	
	public String getNome() {
		return nome;
	}
	public void setNome( String nome ) {
		this.nome = nome;
	}
	public ArrayList< Evento > getMortes() {
		return mortes;
	}
	public void setMortes( ArrayList< Evento > mortes ) {
		this.mortes = mortes;
	}
	public ArrayList< Evento > getAssassinatos() {
		return assassinatos;
	}
	public void setAssassinatos( ArrayList< Evento > assassinatos ) {
		this.assassinatos = assassinatos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		return result;
	}
	
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Jogador other = (Jogador) obj;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append( "Jogador nome=" + nome + "\n\t" );
		
		for ( Evento evento : assassinatos ) {
			stringBuffer.append( evento.toString() + "\n\t" );
		}
		return stringBuffer.toString();
	}
	
}
