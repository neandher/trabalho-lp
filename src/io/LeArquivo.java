package io;

import java.io.*;
import java.util.*;

public abstract class LeArquivo {

	private Scanner entrada;

	public LeArquivo(String nome) throws FileNotFoundException {
		try {
			this.entrada = new Scanner(new FileReader(nome));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("ARQUIVO NAO ENCONTRADO");
		}
	}

	public void leArquivo(ArrayList<Object> cad) throws IllegalStateException {
		String linha;
		try {
			while (this.entrada.hasNext()) {
				linha = this.entrada.nextLine();
				if (!linha.equals(""))
					cad.add(leLinha(linha));
			}
		} catch (IllegalStateException e) {
			throw new IllegalStateException("ERRO DE LEITURA DO ARQUIVO");
		}
	}

	public Object leLinha(String linha) throws NoSuchElementException {

		try {
			/*
			 * dados = linha.split(";"); String agencia = dados[0]; int num =
			 * Integer.parseInt(dados[1]); String nome = dados[2]; String cpf = dados[3];
			 * return (new Conta (agencia, num, nome, cpf));
			 */
			return this.retornaObjeto(linha);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("ARQUIVO DIFERENTE DO REGISTRO ");
		}
	}

	public abstract Object retornaObjeto(String linha);

	public void fechaArquivo() throws IOException {
		try {
			this.entrada.close();
		} catch (IllegalStateException e) {
			throw new IOException("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
