package br.com.ranking.jogo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que monta o Ranking do jogo
 *
 *
 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
 * @since 15 de jun de 2016 20:44:40
 * @version 1.0
 */
public class Ranking{

	private HashMap< String, Integer > quantidadeAssassinatosJogador(Partida partida) {
		HashMap< String, Integer > mapAssassinatos = new HashMap< String, Integer >();
		for ( Jogador jogadorCorrente : partida.getJogadores() ) {
			if("<WORLD>".equalsIgnoreCase( jogadorCorrente.getNome() )){
				continue;
			}
			mapAssassinatos.put( jogadorCorrente.getNome(), jogadorCorrente.getAssassinatos().size() );
		}
		return mapAssassinatos;
	}
	

	private HashMap< String, Integer > quantidadeMortesJogador(Partida partida) {
		ArrayList< Evento > eventosPartida = new ArrayList<>();
		for ( Jogador jogadorCorrente : partida.getJogadores() ) {
			eventosPartida.addAll( jogadorCorrente.getAssassinatos() );
		}
		
		HashMap< String, Integer > mapMortes = new HashMap< String, Integer >();
		for ( Jogador jogadorCorrente : partida.getJogadores() ) {
			if("<WORLD>".equalsIgnoreCase( jogadorCorrente.getNome() )){
				continue;
			}
			int contadorVezesMorreu = 0;
			
			for ( Evento eventoCorrente : eventosPartida ) {
				if(jogadorCorrente.getNome().equals( eventoCorrente.getNomeJogadorMorto() )){
					contadorVezesMorreu++;
				}
			}
			mapMortes.put( jogadorCorrente.getNome(), contadorVezesMorreu );
		}
		return mapMortes;
	}
	
	public void exibirRanking(Partida partida){
		System.out.println( "\n -ASSASSINATOS-" );
		HashMap< String, Integer > mapAssassinatos = quantidadeAssassinatosJogador(partida);
		for ( String jogadorCorrente : mapAssassinatos.keySet() ) {
			System.out.println( "O jogador: " + jogadorCorrente + ", matou:" + mapAssassinatos.get( jogadorCorrente ) + "  jogador(es)");
		}
		
		System.out.println( "\n-MORTES-" );
		HashMap< String, Integer > mapMortes = quantidadeMortesJogador(partida);
		for ( String jogadorCorrente : mapMortes.keySet() ) {
			System.out.println( "O jogador: " + jogadorCorrente + ", morreu: " + mapMortes.get( jogadorCorrente ) + " vez(es)");
		}
		
		System.out.println( "\n-ARMA QUE MAIS MATOU NA PARTIDA-" );
		System.out.println( pegarArmaMaisUsadaPeloVencedo( pegarVencedor( mapAssassinatos, partida ) ) );
	}
	
	
	private Jogador pegarVencedor(HashMap< String, Integer > jogadores, Partida partida){
		String nomeJogador = "";
		int quantidadeAssassinatos = 0;
		
		for ( String nomeJogadorCorrente : jogadores.keySet() ) {
			if ( nomeJogador.isEmpty() ) {
				nomeJogador = nomeJogadorCorrente;
				quantidadeAssassinatos = jogadores.get( nomeJogadorCorrente );
			} else {
				int quantidadeCorrente = jogadores.get( nomeJogadorCorrente );
				if(quantidadeCorrente > quantidadeAssassinatos){
					nomeJogador = nomeJogadorCorrente;
					quantidadeAssassinatos = jogadores.get( nomeJogadorCorrente );
				}
			}
		}
		Jogador jogadorEncontrado = new Jogador();
		jogadorEncontrado.setNome( nomeJogador );
		return partida.getJogadores().get( partida.getJogadores().indexOf( jogadorEncontrado ) );
	}
	
	private String pegarArmaMaisUsadaPeloVencedo(Jogador jogador){
		HashMap< String, Integer > mapArmasUsadas = new HashMap< String, Integer >();
		
		ArrayList< Evento > eventosArmasPartida = new ArrayList<>();
		eventosArmasPartida.addAll( jogador.getAssassinatos() );
		
		for ( Evento eventoCorrente : eventosArmasPartida ) {
			if(eventoCorrente.getArma() != null){
				if ( mapArmasUsadas.containsKey( eventoCorrente.getArma() ) ) {
					Integer totalDeUso = mapArmasUsadas.get( eventoCorrente.getArma() );
					mapArmasUsadas.replace( eventoCorrente.getArma(), ++totalDeUso );
				} else {
					mapArmasUsadas.put( eventoCorrente.getArma(), 1 );
				}
			}
				
		}
		
		String nomeArma = "";
		int quantidadeAssassinatos = 0;
		
		for ( String nomeJogadorCorrente : mapArmasUsadas.keySet() ) {
			if ( nomeArma.isEmpty() ) {
				nomeArma = nomeJogadorCorrente;
				quantidadeAssassinatos = mapArmasUsadas.get( nomeJogadorCorrente );
			} else {
				int quantidadeCorrente = mapArmasUsadas.get( nomeJogadorCorrente );
				if(quantidadeCorrente > quantidadeAssassinatos){
					nomeArma = nomeJogadorCorrente;
					quantidadeAssassinatos = mapArmasUsadas.get( nomeJogadorCorrente );
				}
			}
		}
		
		return "A Arma: " + nomeArma + ", matou" + " ( " + quantidadeAssassinatos + " vez(es) )";
	}


}
