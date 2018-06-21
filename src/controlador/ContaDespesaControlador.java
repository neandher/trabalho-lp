package controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entidade.ContaDespesa;
import entidade.Status;
import exception.CadastroNaoEncontradoException;
import io.InOut;
import repositorio.ContaDespesaRepositorio;
import repositorio.MetodoPagamentoRepositorio;
import repositorio.PlanoContaRepositorio;
import util.Data;
import util.Messages;

public class ContaDespesaControlador {

	public void menu() throws IOException {
		int opt = 0;

		do {
			opt = InOut.leInt("****** MENU CONTAS A PAGAR ***** \n\n" 
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

		ContaDespesaRepositorio rep = new ContaDespesaRepositorio();
		ContaDespesa verificaExiste = rep.encontraPeloCodigo(cod);

		if (verificaExiste != null) {
			InOut.MsgDeErro("", Messages.CADASTRO_JA_EXISTE);
		} else {
			
			String descricao = InOut.leString("Digite a descricao: ");
			double valor = InOut.leDouble("Digite o valor: ");
			String dataVencimento = InOut.leString("Digite a data de vencimento: (Ex: xx/xx/xxxx)");

			PlanoContaRepositorio pcRepositorio = new PlanoContaRepositorio();
			int planoConta = InOut.leInt("Selecione o plano de conta: \n \n" + pcRepositorio.listaTodosMenu(ContaDespesa.REFERENCIA) + "\n\n");

			String contaStatus = Status.CONTA_STATUS_EM_ABERTO;

			MetodoPagamentoRepositorio mpRepositorio = new MetodoPagamentoRepositorio();
			int metodoPagamento = InOut.leInt("Selecione o método de pagamento: \n\n" + mpRepositorio.listaTodosMenu() + "\n\n");

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
				
				ContaDespesa contaDespesa = new ContaDespesa(cod, descricao, valor, dataVencimento, 0, "", planoConta, contaStatus,
						metodoPagamento);
				
				rep.cadastar(contaDespesa);
			}

			InOut.MsgDeAviso("", Messages.CADASTRO_SUCESSO);
		}
	}

	public void listar() throws IOException {
		ContaDespesaRepositorio rep = new ContaDespesaRepositorio();
		ArrayList<ContaDespesa> registros = rep.listaTodos();

		String str = "";

		for (ContaDespesa contaDespesa : registros) {
			str += contaDespesa.toStringNormaliza() + "\n";
		}

		InOut.MsgDeInformacao("", "LISTA: \n\n" + str + "\n");
	}

	public void visualizar() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");
			
		ContaDespesaRepositorio rep = new ContaDespesaRepositorio();
		ContaDespesa conta = rep.encontraPeloCodigo(cod);
		
		MetodoPagamentoRepositorio mpRepositorio = new MetodoPagamentoRepositorio();
		PlanoContaRepositorio pcRepositorio = new PlanoContaRepositorio();
		
		if(conta != null) {
			InOut.MsgDeInformacao("", ""
					+ "Descricao: " + conta.getDescricao() + "\n"
					+ "Plano Pagamento: " + pcRepositorio.encontraPeloCodigo(conta.getPlanoConta()).getDescricao() + "\n"
					+ "Metodo Pagamento: " + mpRepositorio.encontraPeloCodigo(conta.getMetodoPagamento()).getDescricao() + "\n"
					+ "Valor: " + conta.getValor() + "\n"
					+ "Data Vencimento: " + conta.getDataVencimento() + "\n"
					+ "Status: " + Status.normaliza(conta.getContaStatus()) + "\n"
					+ "ValorPago: " + conta.getValorPago() + "\n"
					+ "Data Pagamento: " + conta.getDataPagamento() + "\n"
					);
		}
	}
	
	public void excluir() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");

		ContaDespesaRepositorio rep = new ContaDespesaRepositorio();
			
		/********* EXEMPLO DE EXCECAO *************/
		try {
			rep.excluir(cod);
			InOut.MsgDeAviso("", Messages.REGISTRO_EXCLUSAO_SUCESSO);
		} catch (CadastroNaoEncontradoException e) {
			InOut.MsgDeErro("", e.getMessage());
		}
	}
	
	public void efetuaBaixa() throws IOException {
		int cod = InOut.leInt("Digite o codigo:");
		
		ContaDespesaRepositorio rep = new ContaDespesaRepositorio();
		ContaDespesa conta = rep.encontraPeloCodigo(cod);
		
		if(conta != null) {
			String str = "\nConta: " + conta.getDescricao() + " | " + conta.getValor() + " | " + conta.getDataVencimento() + "\n\n";
			conta.setValorPago(InOut.leDouble(str + "Valor pago:"));
			conta.setDataPagamento(InOut.leString(str + "Data de pagamento:"));
			conta.setContaStatus(Status.CONTA_STATUS_PAGO);
			
			rep.atualizar(cod, conta);
			
			InOut.MsgDeInformacao("", "BAIXA EFETUADA");
		}		
		else {
			InOut.MsgDeAviso("", "Codigo invalido!");
		}
	}
}
