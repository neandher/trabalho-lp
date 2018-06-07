package repositorio;

import java.io.IOException;
import java.util.ArrayList;

import entidade.EntidadeInterface;
import entidade.ContaDespesa;
import io.GravaArq;
import io.LeArquivo;

public class ContaDespesaRepositorio extends LeArquivo {

	public ContaDespesa retornaObjetoArquivo(String linha) {
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

		return (new ContaDespesa(cod, descricao, valor, dataVencimento, valorPago, dataPagamento, planoConta,
				contaStatus, metodoPagamento));
	}

	public ArrayList<ContaDespesa> listaTodos() throws IOException {

		this.iniciaLeituraArquivo(ContaDespesa.NOME_ARQUIVO);

		ArrayList<ContaDespesa> listContaDespesa = new ArrayList<ContaDespesa>();

		for (EntidadeInterface entity : this.listaRegistros()) {
			ContaDespesa cd = (ContaDespesa) entity;
			listContaDespesa.add(cd);
		}

		return listContaDespesa;
	}

	public ContaDespesa encontraPeloCodigo(int cod) throws IOException {

		this.iniciaLeituraArquivo(ContaDespesa.NOME_ARQUIVO);

		ContaDespesa contaDespesa = null;

		for (EntidadeInterface entity : this.listaRegistros()) {
			ContaDespesa cd = (ContaDespesa) entity;
			if (cd.getCod() == cod) {
				contaDespesa = cd;
			}
		}

		return contaDespesa;
	}

	public void cadastar(ContaDespesa pc) throws IOException {
		GravaArq g = new GravaArq(ContaDespesa.NOME_ARQUIVO, true);
		g.gravaArquivo(pc.toStringArquivo());
		g.fechaArquivo();
	}

	public boolean excluir(int cod) throws IOException {

		this.iniciaLeituraArquivo(ContaDespesa.NOME_ARQUIVO);
		ArrayList<EntidadeInterface> registros = this.listaRegistros();

		String str = "";
		boolean encontrou = false;

		for (EntidadeInterface entity : registros) {
			ContaDespesa cd = (ContaDespesa) entity;
			if (cd.getCod() == cod)
				encontrou = true;
			else
				str += cd.toStringArquivo() + "\n";			
		}

		if (encontrou == true) {
			GravaArq g = new GravaArq(ContaDespesa.NOME_ARQUIVO, false);
			g.gravaArquivo(str);
			g.fechaArquivo();

			return true;
		}

		return false;
	}
}
