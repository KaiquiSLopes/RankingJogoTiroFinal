package br.com.ranking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Classe que le um arquivo com as instru��es de log do jogo
 *
 * @author Kaiqui Lopes <kaiquilopes@gmail.com.br>
 * @since 14 de jun de 2016 13:48:59
 * @version 1.0
 */
public class Arquivo{
	
	public static final int NUMERO_PARTIDA = 2;
	
	public static final int NOME_QUEM_FEZ = 0;
	public static final int ACAO_KILLED = 1;
	public static final int NOME_VITIMA = 2;
	public static final int USANDO_OU_CAUSA = 3;
	public static final int ARMA_OU_CAUSA = 4;
	
	public static final int INDICE_DATA = 0;
	public static final int INDICE_DADOS = 1;
	
	// Metodo que le o arquivo.txt
	public List< String > lerArquivoTxt(String caminho) throws IOException{
		File file = new File(caminho);
		ArrayList< String > linhas = new ArrayList<>();
		String linha = null;
		
		if(file.exists()){
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(bufferedReader.ready()){
				linha = bufferedReader.readLine();
				linhas.add( linha );
			}
			fileReader.close();
			bufferedReader.close();
		}else{
			JOptionPane.showMessageDialog(null, "Arquivo n�o encontrado");
		}
		return linhas;
	}
	
}
