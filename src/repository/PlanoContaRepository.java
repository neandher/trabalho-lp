package repository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import entity.PlanoConta;
import io.LeArquivo;

public class PlanoContaRepository extends LeArquivo {

	public PlanoContaRepository() throws FileNotFoundException {
		super(PlanoConta.NOME_ARQUIVO);
	}

	public ArrayList<PlanoConta> listaTodos() {
		ArrayList<Object> listObject = new ArrayList<Object>();
		
		this.leArquivo(listObject);
		
		ArrayList<PlanoConta> listPlanoConta = new ArrayList<PlanoConta>();
		
		
		for (PlanoConta planoConta : listPlanoConta) {
			listPlanoConta.addAll(planoConta);
		}

		
		return listPlanoConta;		
	}

	@Override
	public PlanoConta retornaObjeto(String linha) {
		String[] dados = linha.split(";");
		int cod = Integer.parseInt(dados[0]);
		String descricao = dados[1];
		String tipoConta = dados[2];
		return (new PlanoConta(cod, descricao, tipoConta));
	}

}
