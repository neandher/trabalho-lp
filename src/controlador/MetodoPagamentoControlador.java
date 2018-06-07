package controlador;

import java.io.IOException;
import java.util.ArrayList;

import entidade.MetodoPagamento;
import io.InOut;
import repositorio.MetodoPagamentoRepositorio;
import util.Messages;

public class MetodoPagamentoControlador {

	public void menu() throws IOException {
		int opt = 0;

		do {
			opt = InOut.leInt(
					"****** MENU METODOS DE PAGAMENTO ***** \n\n" + "1- CADASTRAR \n" + "2- LISTAR \n" + "3- EXCLUIR \n" + "4- VOLTAR \n\n");

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
		
		MetodoPagamentoRepositorio rep = new MetodoPagamentoRepositorio();
		MetodoPagamento verificaExiste = rep.encontraPeloCodigo(cod);
		
		if(verificaExiste != null) {
			InOut.MsgDeErro("", Messages.CADASTRO_JA_EXISTE);
		}
		else{
			String descricao = InOut.leString("Digite a descricao: ");				
			
			rep.cadastar((new MetodoPagamento(cod, descricao)));
			InOut.MsgDeAviso("", Messages.CADASTRO_SUCESSO);
		}	
	}

	public void listar() throws IOException {
		MetodoPagamentoRepositorio rep = new MetodoPagamentoRepositorio();
		ArrayList<MetodoPagamento> registros = rep.listaTodos();
		
		String str = "";
		
		for (MetodoPagamento metodoPagamento : registros) {
			str += metodoPagamento.toString() + "\n";
		}
		
		InOut.MsgDeInformacao("", "LISTA: \n\n" + str + "\n");
	}

	public void excluir() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");
		
		MetodoPagamentoRepositorio rep = new MetodoPagamentoRepositorio();
		
		if (rep.excluir(cod))
			InOut.MsgDeAviso("", Messages.REGISTRO_EXCLUSAO_SUCESSO);
		else
			InOut.MsgDeErro("", Messages.REGISTRO_EXCLUSAO_ERRO);
	}
}
