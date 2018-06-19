package repositorio;

import java.io.IOException;
import java.util.ArrayList;

import entidade.EntidadeInterface;
import entidade.ContaReceita;
import io.GravaArq;
import io.LeArquivo;

public class ContaReceitaRepositorio extends LeArquivo {

	public ContaReceita retornaObjetoArquivo(String linha) {
		String[] dados = linha.split(";");

		int cod = Integer.parseInt(dados[0]);
		String descricao = dados[1];
		double valor = Double.parseDouble(dados[2]);
		String dataVencimento = dados[3];
		double valorPago = Double.parseDouble(dados[4]);
		String dataPagamento = dados[5];
		int planoConta = Integer.parseInt(dados[6]);
		String contaStatus = dados[7];
		int metodoPagamento = Integer.parseInt(dados[8]);

		return (new ContaReceita(cod, descricao, valor, dataVencimento, valorPago, dataPagamento, planoConta,
				contaStatus, metodoPagamento));
	}

	public ArrayList<ContaReceita> listaTodos() throws IOException {

		this.iniciaLeituraArquivo(ContaReceita.NOME_ARQUIVO);

		ArrayList<ContaReceita> listContaReceita = new ArrayList<ContaReceita>();

		for (EntidadeInterface entity : this.listaRegistros()) {
			ContaReceita cr = (ContaReceita) entity;
			listContaReceita.add(cr);
		}

		return listContaReceita;
	}

	public ContaReceita encontraPeloCodigo(int cod) throws IOException {

		this.iniciaLeituraArquivo(ContaReceita.NOME_ARQUIVO);

		ContaReceita contaReceita = null;

		for (EntidadeInterface entity : this.listaRegistros()) {
			ContaReceita cr = (ContaReceita) entity;
			if (cr.getCod() == cod) {
				contaReceita = cr;
			}
		}

		return contaReceita;
	}

	public void cadastar(ContaReceita pc) throws IOException {
		GravaArq g = new GravaArq(ContaReceita.NOME_ARQUIVO, true);
		g.gravaArquivo(pc.toStringArquivo());
		g.fechaArquivo();
	}

	public boolean excluir(int cod) throws IOException {

		this.iniciaLeituraArquivo(ContaReceita.NOME_ARQUIVO);
		ArrayList<EntidadeInterface> registros = this.listaRegistros();

		String str = "";
		boolean encontrou = false;

		for (EntidadeInterface entity : registros) {
			ContaReceita cr = (ContaReceita) entity;
			if (cr.getCod() == cod)
				encontrou = true;
			else
				str += cr.toStringArquivo() + "\n";
		}

		if (encontrou == true) {
			GravaArq g = new GravaArq(ContaReceita.NOME_ARQUIVO, false);
			g.gravaArquivo(str);
			g.fechaArquivo();

			return true;
		}

		return false;
	}
	
	public boolean atualizar(int cod, ContaReceita conta) throws IOException {
		this.iniciaLeituraArquivo(ContaReceita.NOME_ARQUIVO);
		ArrayList<EntidadeInterface> registros = this.listaRegistros();

		String str = "";
		boolean atualizou = false;

		for (EntidadeInterface entity : registros) {
			ContaReceita cd = (ContaReceita) entity;
			if (cd.getCod() == cod) {
				str += conta.toStringArquivo() + "\n";
				atualizou = true;
			} else {
				str += cd.toStringArquivo() + "\n";
			}
		}

		if (atualizou == true) {
			GravaArq g = new GravaArq(ContaReceita.NOME_ARQUIVO, false);
			g.gravaArquivo(str);
			g.fechaArquivo();

			return true;
		}

		return false;
	}
}
