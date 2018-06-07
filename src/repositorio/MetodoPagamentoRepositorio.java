package repositorio;

import java.io.IOException;
import java.util.ArrayList;

import entidade.EntidadeInterface;
import entidade.MetodoPagamento;
import io.GravaArq;
import io.LeArquivo;

public class MetodoPagamentoRepositorio extends LeArquivo {

	public MetodoPagamento retornaObjetoArquivo(String linha) {
		String[] dados = linha.split(";");

		int cod = Integer.parseInt(dados[0]);
		String descricao = dados[1];		

		return (new MetodoPagamento(cod, descricao));
	}

	public ArrayList<MetodoPagamento> listaTodos() throws IOException {

		this.iniciaLeituraArquivo(MetodoPagamento.NOME_ARQUIVO);

		ArrayList<MetodoPagamento> listMetodoPagamento = new ArrayList<MetodoPagamento>();

		for (EntidadeInterface entity : this.listaRegistros()) {
			MetodoPagamento mp = (MetodoPagamento) entity;
			listMetodoPagamento.add(mp);
		}

		return listMetodoPagamento;
	}
	
	public String listaTodosMenu() throws IOException {
		ArrayList<MetodoPagamento> lista = this.listaTodos();
		
		String str = "";
		for (MetodoPagamento metodoPagamento : lista) {
			str += metodoPagamento.toString() + "\n";
		}
		
		return str;
	}

	public MetodoPagamento encontraPeloCodigo(int cod) throws IOException {

		this.iniciaLeituraArquivo(MetodoPagamento.NOME_ARQUIVO);

		MetodoPagamento metodoPagamento = null;

		for (EntidadeInterface entity : this.listaRegistros()) {
			MetodoPagamento mp = (MetodoPagamento) entity;
			if (mp.getCod() == cod) {
				metodoPagamento = mp;
			}
		}

		return metodoPagamento;
	}

	public void cadastar(MetodoPagamento pc) throws IOException {
		GravaArq g = new GravaArq(MetodoPagamento.NOME_ARQUIVO, true);
		g.gravaArquivo(pc.toStringArquivo());
		g.fechaArquivo();
	}

	public boolean excluir(int cod) throws IOException {

		this.iniciaLeituraArquivo(MetodoPagamento.NOME_ARQUIVO);
		ArrayList<EntidadeInterface> registros = this.listaRegistros();

		String str = "";
		boolean encontrou = false;

		for (EntidadeInterface entity : registros) {
			MetodoPagamento mp = (MetodoPagamento) entity;
			if(mp.getCod() == cod)
				encontrou = true;			
			else
				str += mp.toStringArquivo() + "\n";			
		}

		if (encontrou == true) {
			GravaArq g = new GravaArq(MetodoPagamento.NOME_ARQUIVO, false);
			g.gravaArquivo(str);
			g.fechaArquivo();

			return true;
		}
		
		return false;
	}
}
