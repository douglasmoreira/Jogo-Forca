package br.com.zup.apolo11.Extra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Forca {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		
		String palavraForca = leituraArquivo();
		char[] palavraEscondida = gerarPalavraEscondida(palavraForca).toCharArray();
		int numeroErros = 0;
	
		String letra;
		do {
		System.out.println("Digite uma possível letra");
		letra = input.next();
		
		if ((substituiLetrasNaForca(letra, palavraForca, palavraEscondida, numeroErros).length()) == 1) {
			System.out.println(erros(numeroErros)+ " Erros");
			numeroErros = erros(numeroErros);
			
		}else {
			palavraComEspaco(substituiLetrasNaForca(letra, palavraForca, palavraEscondida,numeroErros));
			palavraEscondida = substituiLetrasNaForca(letra, palavraForca, palavraEscondida, numeroErros).toCharArray();
			
			if (finalizaJogo(palavraForca, palavraEscondida)) {
				System.out.println("voce Venceu!");
				break;
			}
		}
		
		}while ( erros(numeroErros) <= 6 );
		
		input.close();
	}
	
public static void palavraComEspaco(String palavraEscondida) {
		
		StringBuilder stringBuilder = new StringBuilder(palavraEscondida);
		
		for (int i = palavraEscondida.length()-1; i >= 1;i-=1) {
			stringBuilder.insert(i, ' ');
			
		}
		System.out.println(stringBuilder.toString());
	}

	
	public static String gerarPalavraEscondida(String palavraEscondida) {
		
		char[] caracterePalavra = new char[palavraEscondida.length()];
		for (int i = 0; i < palavraEscondida.length();i++) {
			caracterePalavra[i] = '_';
		}
		return (new String(caracterePalavra));
	}
	
	public static int erros(int numeroErros) {
		numeroErros++;
		return numeroErros;
	}
	public static boolean finalizaJogo(String palavraDesafio, char[] palavraEscondida) {
		if ( palavraDesafio.equals(new String (palavraEscondida))) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static String substituiLetrasNaForca(String letra, String palavraDesafio, char[] palavraEscondida, int numeroErros) {

		char letras = letra.charAt(0);
		char[] palavras = palavraDesafio.toCharArray();
		

		if (palavraDesafio.contains(letra)){
			
			for (int i = 0;  i < palavras.length; i++){
				if (palavras[i] == letras) {
					palavraEscondida[i] = letras;
				}
			}
			return (new String(palavraEscondida));
		}else {
			return (String.format("%d", erros(numeroErros)));
		}
	}
	
	public static String leituraArquivo() throws IOException{
		FileReader reader = new FileReader("palavras forca.txt");
		BufferedReader buffer = new BufferedReader(reader);
		SecureRandom random = new SecureRandom();
		
		ArrayList <String> palavras = new ArrayList<String>();
		String linha;
		
		while((linha = buffer.readLine()) != null) {
			
			palavras.add(linha);
		}
		
		int numeroAleatorio = random.nextInt(palavras.size());
		
		buffer.close();
		reader.close();
		
		return palavras.get(numeroAleatorio);
	}
}
