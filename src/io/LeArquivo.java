package io;

import java.io.*;
import java.util.*;

import entidade.EntidadeInterface;
import entidade.PlanoConta;

public abstract class LeArquivo{

	private Scanner entrada;	

	public void iniciaLeituraArquivo(String nome) throws FileNotFoundException {
		try {
			this.entrada = new Scanner(new FileReader(nome));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("ARQUIVO NAO ENCONTRADO");
		}
	}

	private void leArquivo(ArrayList<EntidadeInterface> cad) throws IllegalStateException {
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

	private EntidadeInterface leLinha(String linha) throws NoSuchElementException {
		try {			
			return this.retornaObjetoArquivo(linha);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("ARQUIVO DIFERENTE DO REGISTRO ");
		}
	}
	
	public abstract PlanoConta retornaObjetoArquivo(String linha);

	public ArrayList<EntidadeInterface> listaRegistros() throws IOException{
		ArrayList<EntidadeInterface> lista = new ArrayList<EntidadeInterface>();		
		this.leArquivo(lista);	
		this.fechaArquivo();
		return lista;
	}
	
	public void fechaArquivo() throws IOException {
		try {
			this.entrada.close();
		} catch (IllegalStateException e) {
			throw new IOException("ERRO AO FECHAR O ARQUIVO");
		}
	}
}
