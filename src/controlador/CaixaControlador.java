package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import entidade.ContaDespesa;
import entidade.ContaReceita;
import io.InOut;
import repositorio.ContaDespesaRepositorio;
import repositorio.ContaReceitaRepositorio;
import util.Data;

public class CaixaControlador {

	public void menu() throws IOException {
		int opt = 0;

		do {
			opt = InOut.leInt(
					"****** MENU GESTAO DE CAIXA ***** \n\n" + "1- GERACAO DE CAIXA MENSAL \n" + "2- VOLTAR \n" + "\n");

			switch (opt) {
			case 1:
				this.caixaMensal();
				break;
			case 2:
				opt = -1;
				break;

			default:
				opt = -1;
				break;
			}

		} while (opt != -1);
	}

	public void caixaMensal() throws IOException {

		Calendar dataAtual = Calendar.getInstance();

		String mesAno = InOut.leString("Digite o mes/ano. EX: (xx/xxxx)");
		Calendar dataSolicitada = Data.converteParaData("01/" + mesAno);

		String verificaMesAno = "";

		if (dataSolicitada.get(Calendar.MONTH) == dataAtual.get(Calendar.MONTH)
				&& dataSolicitada.get(Calendar.YEAR) == dataAtual.get(Calendar.YEAR)) {
			verificaMesAno = "presente";
		} else if (dataSolicitada.get(Calendar.DATE) < dataAtual.get(Calendar.DATE)) {
			verificaMesAno = "passado";
		} else if (dataSolicitada.get(Calendar.DATE) > dataAtual.get(Calendar.DATE)) {
			verificaMesAno = "futuro";
		}

		/****************** CALCULO RECEITA *********************/
		
		ContaReceitaRepositorio recRep = new ContaReceitaRepositorio();
		ArrayList<ContaReceita> receitas = recRep.listaTodos();

		double totalReceitaRecebido = 0;
		double totalReceitaReceber = 0;

		for (ContaReceita contaReceita : receitas) {
			Calendar dataCorrente = Data.converteParaData(contaReceita.getDataVencimento());

			if (verificaMesAno.equals("passado") && dataCorrente.get(Calendar.DATE) < dataSolicitada.get(Calendar.DATE)
					&& contaReceita.getValorRecebido() > 0) {

				totalReceitaRecebido += contaReceita.getValorRecebido();

			} else if (verificaMesAno.equals("presente")
					&& dataCorrente.get(Calendar.MONTH) == dataSolicitada.get(Calendar.MONTH)
					&& dataCorrente.get(Calendar.YEAR) == dataSolicitada.get(Calendar.YEAR)) {
				
				if (contaReceita.getValorRecebido() > 0) {
					totalReceitaRecebido += contaReceita.getValorRecebido();
				}
				else {
					totalReceitaReceber += contaReceita.getValor();
				}
				
			}
			else if (verificaMesAno.equals("futuro") && dataCorrente.get(Calendar.DATE) > dataSolicitada.get(Calendar.DATE)) {

				totalReceitaReceber += contaReceita.getValor();
			}
		}
		
		double totalReceitaGeral = totalReceitaRecebido + totalReceitaReceber;
		
		/**************** CALCULO DESPESA *******************/
		
		ContaDespesaRepositorio desRep = new ContaDespesaRepositorio();
		ArrayList<ContaDespesa> despesas = desRep.listaTodos();

		double totalDespesaPago = 0;
		double totalDespesaPagar = 0;

		for (ContaDespesa contaDespesa : despesas) {
			Calendar dataCorrente = Data.converteParaData(contaDespesa.getDataVencimento());

			if (verificaMesAno.equals("passado") && dataCorrente.get(Calendar.DATE) < dataSolicitada.get(Calendar.DATE)
					&& contaDespesa.getValorPago() > 0) {

				totalDespesaPago += contaDespesa.getValorPago();

			} else if (verificaMesAno.equals("presente")
					&& dataCorrente.get(Calendar.MONTH) == dataSolicitada.get(Calendar.MONTH)
					&& dataCorrente.get(Calendar.YEAR) == dataSolicitada.get(Calendar.YEAR)) {
				
				if (contaDespesa.getValorPago() > 0) {
					totalDespesaPago += contaDespesa.getValorPago();
				}
				else {
					totalDespesaPagar += contaDespesa.getValor();
				}
				
			}
			else if (verificaMesAno.equals("futuro") && dataCorrente.get(Calendar.DATE) > dataSolicitada.get(Calendar.DATE)) {

				totalDespesaPagar += contaDespesa.getValor();
			}
		}
		
		double totalDespesaGeral = totalDespesaPago + totalDespesaPagar;
		
		/**************** CALCULO GERAL *******************/
		
		String str = "GERACAO DE CAIXA: " + mesAno + "\n\n";
		
		if (verificaMesAno.equals("passado")){
			str += "Total de Receitas Recebidas: " + totalReceitaRecebido + "\n";
			str += "Total de Despesas Pagas: " + totalDespesaPago + "\n\n";
			str += "Total Geral: " + (totalReceitaRecebido - totalDespesaPago) + "\n";
		}
		else if (verificaMesAno.equals("presente")){
			str += "Total de Receitas Recebidas: " + totalReceitaRecebido + "\n";
			str += "Total de Receitas a Receber: " + totalReceitaReceber + "\n\n";
			str += "Total de Despesas Pagas: " + totalDespesaPago + "\n";
			str += "Total de Despesas a Pagar: " + totalDespesaPagar + "\n\n";
			str += "Total Geral: " + (totalReceitaGeral - totalDespesaGeral) + "\n";
		}
		else if (verificaMesAno.equals("futuro")){
			str += "Total de Receitas a Receber: " + totalReceitaReceber + "\n";
			str += "Total de Despesas a Pagar: " + totalDespesaPagar + "\n\n";
			str += "Total Geral: " + (totalReceitaReceber - totalDespesaPagar) + "\n";
		}
		
		InOut.MsgDeInformacao("", str);
	}
}
