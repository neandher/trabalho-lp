package repositorio;

import java.io.IOException;
import java.util.ArrayList;

import entidade.EntidadeInterface;
import entidade.PlanoConta;
import io.GravaArq;
import io.LeArquivo;

public class PlanoContaRepositorio extends LeArquivo {

	public PlanoConta retornaObjetoArquivo(String linha) {
		String[] dados = linha.split(";");

		int cod = Integer.parseInt(dados[0]);
		String descricao = dados[1];
		String tipoPlanoConta = dados[2];

		return (new PlanoConta(cod, descricao, tipoPlanoConta));
	}

	public ArrayList<PlanoConta> listaTodos() throws IOException {

		this.iniciaLeituraArquivo(PlanoConta.NOME_ARQUIVO);

		ArrayList<PlanoConta> listPlanoConta = new ArrayList<PlanoConta>();

		for (EntidadeInterface entity : this.listaRegistros()) {
			PlanoConta pc = (PlanoConta) entity;
			listPlanoConta.add(pc);
		}

		return listPlanoConta;
	}

	public String listaTodosMenu(String tipo) throws IOException {
		ArrayList<PlanoConta> lista = this.listaTodos();

		String str = "";

		for (PlanoConta planoConta : lista) {
			if (planoConta.getTipoPlanoConta().equals(tipo)) {
				str += planoConta.toString() + "\n";
			}			
		}

		str += "\n \n";

		return str;
	}

	public PlanoConta encontraPeloCodigo(int cod) throws IOException {

		this.iniciaLeituraArquivo(PlanoConta.NOME_ARQUIVO);

		PlanoConta planoConta = null;

		for (EntidadeInterface entity : this.listaRegistros()) {
			PlanoConta pc = (PlanoConta) entity;
			if (pc.getCod() == cod) {
				planoConta = pc;
			}
		}

		return planoConta;
	}

	public void cadastar(PlanoConta pc) throws IOException {
		GravaArq g = new GravaArq(PlanoConta.NOME_ARQUIVO, true);
		g.gravaArquivo(pc.toStringArquivo());
		g.fechaArquivo();
	}

	public boolean excluir(int cod) throws IOException {

		this.iniciaLeituraArquivo(PlanoConta.NOME_ARQUIVO);
		ArrayList<EntidadeInterface> registros = this.listaRegistros();

		String str = "";
		boolean encontrou = false;

		for (EntidadeInterface entity : registros) {
			PlanoConta pc = (PlanoConta) entity;
			if (pc.getCod() == cod)
				encontrou = true;
			else
				str += pc.toStringArquivo() + "\n";
		}

		if (encontrou == true) {
			GravaArq g = new GravaArq(PlanoConta.NOME_ARQUIVO, false);
			g.gravaArquivo(str);
			g.fechaArquivo();

			return true;
		}

		return false;
	}
}
