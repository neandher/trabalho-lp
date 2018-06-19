package controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entidade.ContaReceita;
import entidade.Status;
import io.InOut;
import repositorio.ContaReceitaRepositorio;
import repositorio.MetodoPagamentoRepositorio;
import repositorio.PlanoContaRepositorio;
import util.Data;
import util.Messages;

public class ContaReceitaControlador {

	public void menu() throws IOException {
		int opt = 0;

		do {
			opt = InOut.leInt("****** MENU CONTAS A RECEBER ***** \n\n" 
					+ "1- CADASTRAR \n" 
					+ "2- LISTAR \n"
					+ "3- VISUALIZAR \n"
					+ "4- EXCLUIR \n" 
					+ "5- EFETUAR BAIXA \n"
					+ "6- VOLTAR \n"
					+ "\n");

			switch (opt) {
			case 1:
				this.cadastrar();
				break;
			case 2:
				this.listar();
				break;
			case 3:
				this.visualizar();
				break;
			case 4:
				this.excluir();
				break;
			case 5:
				this.efetuaBaixa();
				break;
			case 6:
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

		ContaReceitaRepositorio rep = new ContaReceitaRepositorio();
		ContaReceita verificaExiste = rep.encontraPeloCodigo(cod);

		if (verificaExiste != null) {
			InOut.MsgDeErro("", Messages.CADASTRO_JA_EXISTE);
		} else {

			String descricao = InOut.leString("Digite a descricao: ");
			double valor = InOut.leDouble("Digite o valor: ");
			String dataVencimento = InOut.leString("Digite a data de vencimento: (Ex: xx/xx/xxxx)");

			PlanoContaRepositorio pcRepositorio = new PlanoContaRepositorio();
			int planoConta = InOut.leInt("Selecione o plano de conta: \n \n"
					+ pcRepositorio.listaTodosMenu(ContaReceita.REFERENCIA) + "\n\n");

			String contaStatus = Status.CONTA_STATUS_EM_ABERTO;

			MetodoPagamentoRepositorio mpRepositorio = new MetodoPagamentoRepositorio();
			int metodoPagamento = InOut
					.leInt("Selecione o método de pagamento: \n\n" + mpRepositorio.listaTodosMenu() + "\n\n");

			int parcelas = InOut.leInt("Digite a quantidade de parcelas:");

			for (int i = 0; i < parcelas; i++) {

				Calendar data;

				if (i > 0) {
					data = Data.converteParaData(dataVencimento);
					data.add(Calendar.MONTH, 1);

					Date dataParcela = data.getTime();
					DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

					dataVencimento = df.format(dataParcela.getTime());
					cod++;
				}

				ContaReceita contaReceita = new ContaReceita(cod, descricao, valor, dataVencimento, 0, "", planoConta,
						contaStatus, metodoPagamento);

				rep.cadastar(contaReceita);
			}

			InOut.MsgDeAviso("", Messages.CADASTRO_SUCESSO);
		}
	}

	public void listar() throws IOException {
		ContaReceitaRepositorio rep = new ContaReceitaRepositorio();
		ArrayList<ContaReceita> registros = rep.listaTodos();

		String str = "";

		for (ContaReceita contaReceita : registros) {
			str += contaReceita.toStringNormaliza() + "\n";
		}

		InOut.MsgDeInformacao("", "LISTA: \n\n" + str + "\n");
	}

	public void visualizar() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");

		ContaReceitaRepositorio rep = new ContaReceitaRepositorio();
		ContaReceita conta = rep.encontraPeloCodigo(cod);

		MetodoPagamentoRepositorio mpRepositorio = new MetodoPagamentoRepositorio();
		PlanoContaRepositorio pcRepositorio = new PlanoContaRepositorio();

		if (conta != null) {
			InOut.MsgDeInformacao("", "" + "Descricao: " + conta.getDescricao() + "\n" + "Plano Pagamento: "
					+ pcRepositorio.encontraPeloCodigo(conta.getPlanoConta()).getDescricao() + "\n"
					+ "Metodo Pagamento: " + mpRepositorio.encontraPeloCodigo(conta.getMetodoPagamento()).getDescricao()
					+ "\n" + "Valor: " + conta.getValor() + "\n" + "Data Vencimento: " + conta.getDataVencimento()
					+ "\n" + "Status: " + Status.normaliza(conta.getContaStatus()) + "\n" + "ValorRecebido: "
					+ conta.getValorRecebido() + "\n" + "Data Recebimento: " + conta.getDataRecebimento() + "\n");
		}
	}

	public void excluir() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");

		ContaReceitaRepositorio rep = new ContaReceitaRepositorio();

		if (rep.excluir(cod))
			InOut.MsgDeAviso("", Messages.REGISTRO_EXCLUSAO_SUCESSO);
		else
			InOut.MsgDeErro("", Messages.REGISTRO_EXCLUSAO_ERRO);
	}

	public void efetuaBaixa() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");

		ContaReceitaRepositorio rep = new ContaReceitaRepositorio();
		ContaReceita conta = rep.encontraPeloCodigo(cod);

		if (conta != null) {
			String str = "\nConta: " + conta.getDescricao() + " | " + conta.getValor() + " | "
					+ conta.getDataVencimento() + "\n\n";
			conta.setValorRecebido(InOut.leDouble(str + "Valor pago:"));
			conta.setDataRecebimento(InOut.leString(str + "Data de pagamento:"));
			conta.setContaStatus(Status.CONTA_STATUS_RECEBIDO);

			rep.atualizar(cod, conta);

			InOut.MsgDeInformacao("", "BAIXA EFETUADA");
		} else {
			InOut.MsgDeAviso("", "Codigo invalido!");
		}
	}
}
