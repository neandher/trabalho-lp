package repositorio;

import java.io.IOException;
import java.util.ArrayList;

import entidade.EntidadeInterface;
import entidade.PlanoConta;
import io.LeArquivo;

public class PlanoContaRepositorio extends LeArquivo{

	public ArrayList<PlanoConta> listaTodos() throws IOException {
				
		this.iniciaLeituraArquivo(PlanoConta.NOME_ARQUIVO);	
		
		ArrayList<PlanoConta> listPlanoConta = new ArrayList<PlanoConta>();

		for (EntidadeInterface entity : this.listaRegistros()) {
			PlanoConta pc = (PlanoConta) entity;
			listPlanoConta.add(pc);
		}
		
		return listPlanoConta;
	}

	public PlanoConta encontraPeloCodigo(int cod) throws IOException {
		
		this.iniciaLeituraArquivo(PlanoConta.NOME_ARQUIVO);	
		
		PlanoConta planoConta = null;

		for (EntidadeInterface entity : this.listaRegistros()) {
			PlanoConta pc = (PlanoConta) entity;
			if(pc.getCod() == cod) {
				planoConta = pc;
			}
		}

		return planoConta;
	}
	
	public PlanoConta retornaObjetoArquivo(String linha) {
		String[] dados = linha.split(";");
		int cod = Integer.parseInt(dados[0]);
		String descricao = dados[1];
		String tipoConta = dados[2];
		return (new PlanoConta(cod, descricao, tipoConta));
	}

}
