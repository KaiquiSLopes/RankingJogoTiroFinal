package br.com.ranking.jogo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.ranking.util.Arquivo;

/**
 * Classe que representa as regras do jogo
 *
 *
 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
 * @since 16 de jun de 2016 17:58:34
 * @version 1.0
 */
public class ControladorPartida{
	
	
	/** METODO RESPONSAVEL POR CHAMAR O ARQUIVO TXT COM AS INFORMA��ES DO LOG */
	public ArrayList< String > carregarArquivoLog( String caminho ) throws IOException {
		return (ArrayList< String >) new Arquivo().lerArquivoTxt( caminho );
	}

	private void prapararEvento( String dadosData, String dadosJogador, Jogador jogador ) throws ParseException {
		String[ ] dados = dadosJogador.split( " " );

		String acao = dados[ Arquivo.ACAO_KILLED ];
		String vitima = dados[ Arquivo.NOME_VITIMA ];
		String usingOrBy = dados[ Arquivo.USANDO_OU_CAUSA ];
		String armaOrCaussa = dados[ Arquivo.ARMA_OU_CAUSA ];

		Evento evento = new Evento();

		if ( "killed".equalsIgnoreCase( acao ) ) {

			evento.setNomeJogadorMorto( vitima );
			evento.setData( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).parse( dadosData ) );

			if ( "using".equalsIgnoreCase( usingOrBy ) ) {
				evento.setArma( armaOrCaussa );
			} else if ( "by".equalsIgnoreCase( usingOrBy ) ) {
				evento.setCausa( armaOrCaussa );
			}

			jogador.getAssassinatos().add( evento );

		}
	}

	private void carregarJogadores( String data, String dados, Partida partidaCorrente ) throws ParseException {
		String nomeJogador = dados.split( " " )[ Arquivo.NOME_QUEM_FEZ ];
		String vitima = dados.split( " " )[ Arquivo.NOME_VITIMA ];
		
		criarJogador( "assassino", nomeJogador, partidaCorrente, data, dados );
		criarJogador( "vitima", vitima, partidaCorrente, data, dados );
	}
	
	private void criarJogador(String tipo, String nomeJogador, Partida partidaCorrente, String data, String dados) throws ParseException{
		Jogador jogador = new Jogador();
		jogador.setNome( nomeJogador );
		
		if(partidaCorrente.getJogadores().contains( jogador )){
			jogador = partidaCorrente.getJogadores().get(partidaCorrente.getJogadores().indexOf( jogador ));
		}else{
			partidaCorrente.getJogadores().add( jogador );
		}
		if(tipo.equalsIgnoreCase( "assassino" )){
			prapararEvento( data, dados, jogador );
		}
	}

	/**
	 * M�todo respons�vel por iniciar o Ranking
	 *
	 * @param caminhoArquivoLog
	 * @throws Exception
	 *
	 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
	 * @since 16 de jun de 2016 21:11:47
	 * @version 1.0
	 */
	public void carregarRanking( String caminhoArquivoLog ) throws Exception {
		ArrayList< String > linhasArquivo = carregarArquivoLog( caminhoArquivoLog );
		
		ArrayList< Partida > partidas = new ArrayList<>();
		Partida partida = null;

		//		Identifica quando comeca a partida e quando termina a partida. 
		for ( String linhaCorrente : linhasArquivo ) {
			String data = linhaCorrente.split( " - " )[ Arquivo.INDICE_DATA ];
			String dados = linhaCorrente.split( " - " )[ Arquivo.INDICE_DADOS ];

			if ( linhaCorrente.contains( "New" ) ) {
				partida = new Partida();
				String dataInicio = data;
				partida.setDataInicio( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).parse( dataInicio ) );

				partida.setNumero( Long.valueOf( dados.split( " " )[ Arquivo.NUMERO_PARTIDA ] ) );

				continue;
			} else if ( linhaCorrente.contains( "ended" ) ) {
				if ( partida == null ) {
					throw new Exception( "Partida n�o inicializada" );
				}
				String dataTermino = data;
				partida.setDataTermino( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).parse( dataTermino ) );
			}
			if ( partida != null && partida.getDataTermino() != null ) {
				partidas.add( partida );
			} else {
				carregarJogadores( data, dados, partida );
			}
		}
		exibirRanking( partidas );
	}
	
	private void exibirRanking(ArrayList< Partida > partidas){
		for ( Partida partidaCorrente : partidas ) {
			System.out.println( "-------- INICIO DA PARTIDA --------" );
			new Ranking().exibirRanking( partidaCorrente );
			System.out.println( "\n-------- TERMINO DA PARTIDA --------\n" );
		}
	}

}
