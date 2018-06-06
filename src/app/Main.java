package app;

import java.io.IOException;
import java.util.ArrayList;

import entidade.ContaDespesa;
import entidade.PlanoConta;
import io.GravaArq;
import repositorio.PlanoContaRepositorio;

public class Main {

	public static void main(String[] args) throws IOException {	
		
		/*PlanoConta pc = new PlanoConta(1, "Carro", ContaDespesa.REFERENCIA);
		GravaArq g = new GravaArq(PlanoConta.NOME_ARQUIVO, true);
		g.gravaArquivo(pc.toStringArquivo());
		g.fechaArquivo();	*/
		
		PlanoContaRepositorio rep;
		
		try {
			rep = new PlanoContaRepositorio();
			
			ArrayList<PlanoConta> pcList = rep.listaTodos();
			
			for (PlanoConta planoConta : pcList) {
				System.out.println(planoConta.toStringArquivo());
			}
			
			ArrayList<PlanoConta> pcList2 = rep.listaTodos();
			
			for (PlanoConta planoConta : pcList2) {
				System.out.println(planoConta.toStringArquivo());
			}
			
			/*PlanoConta byCod = rep.encontraPeloCodigo(1);
			
			System.out.println(byCod);
			
			if(byCod != null) {
				System.out.println("by code");
				System.out.println(byCod.toStringArquivo());
			}*/
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
