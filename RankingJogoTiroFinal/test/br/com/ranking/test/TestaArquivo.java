package br.com.ranking.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.ranking.util.Arquivo;

public class TestaArquivo{

	@Ignore
	@Test
	public void testaSeEstaLendoUmArquivo() throws IOException {
		Arquivo arquivo = new Arquivo();
		String caminho = "D:/Desenvolvimento/PROJETOS KAIQUI SWING/workspace/RankingJogoTiroFinal/Arquivo.txt";
		assertEquals( "[Oii eu sou o Goku!!!]", arquivo.lerArquivoTxt(caminho).toString() );
	}

}
