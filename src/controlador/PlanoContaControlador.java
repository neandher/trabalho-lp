package controlador;

import java.io.IOException;
import java.util.ArrayList;

import entidade.ContaDespesa;
import entidade.ContaReceita;
import entidade.PlanoConta;
import io.InOut;
import repositorio.PlanoContaRepositorio;
import util.Messages;

public class PlanoContaControlador {

	public void menu() throws IOException {
		int opt = 0;

		do {
			opt = InOut.leInt(
					"****** MENU PLANO DE CONTAS ***** \n\n" + "1- CADASTRAR \n" + "2- LISTAR \n" + "3- EXCLUIR \n" + "4- VOLTAR \n\n");

			switch (opt) {
			case 1:
				this.cadastrar();
				break;
			case 2:
				this.listar();
				break;
			case 3:
				this.excluir();
				break;
			case 4:
				opt = -1;
				break;

			default:
				opt = -1;
				break;
			}

		} while (opt != -1);
	}

	public void cadastrar() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");
		
		PlanoContaRepositorio rep = new PlanoContaRepositorio();
		PlanoConta verificaExiste = rep.encontraPeloCodigo(cod);
		
		if(verificaExiste != null) {
			InOut.MsgDeErro("", Messages.CADASTRO_JA_EXISTE);
		}
		else{
			String descricao = InOut.leString("Digite a descricao: ");
			int opcaoTipoPlanoConta = InOut.leInt("Tipo Plano Conta: \n" + "1 - Despesa \n" + "2 - Receita");
			String tipoPlanoConta = opcaoTipoPlanoConta == 1 ? ContaDespesa.REFERENCIA : ContaReceita.REFERENCIA;
			
			rep.cadastar((new PlanoConta(cod, descricao, tipoPlanoConta)));
			InOut.MsgDeAviso("", Messages.CADASTRO_SUCESSO);
		}	
	}

	public void listar() throws IOException {
		PlanoContaRepositorio rep = new PlanoContaRepositorio();
		ArrayList<PlanoConta> registros = rep.listaTodos();
		
		String str = "";
		
		for (PlanoConta planoConta : registros) {
			str += planoConta.toString() + "\n";
		}
		
		InOut.MsgDeInformacao("", "LISTA: \n\n" + str + "\n");
	}

	public void excluir() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");
		
		PlanoContaRepositorio rep = new PlanoContaRepositorio();
		
		if (rep.excluir(cod))
			InOut.MsgDeAviso("", Messages.REGISTRO_EXCLUSAO_SUCESSO);
		else
			InOut.MsgDeErro("", Messages.REGISTRO_EXCLUSAO_ERRO);
	}
}
