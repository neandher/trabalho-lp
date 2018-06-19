package app;

import java.io.IOException;

import controlador.CaixaControlador;
import controlador.ContaDespesaControlador;
import controlador.ContaReceitaControlador;
import controlador.MetodoPagamentoControlador;
import controlador.PlanoContaControlador;
import io.InOut;

public class Main {

	public static void main(String[] args) throws IOException {			
		
		int opt = 0;
		
		do {
			opt = InOut.leInt(
			"*****************************************************" + "\n"+
			"SISTEMA DE CONTROLE FINANCEIRO PESSOAL" + "\n"+
			"*****************************************************" + "\n" +
			"\n MENU PRINCIPAL \n\n" 
			+ "1- CONTAS A RECEBER \n" 
			+ "2- CONTAS A PAGAR \n"
			+ "3- GERENCIAR CAIXA \n"
			+ "4- PLANO DE CONTAS \n" 
			+ "5- METODOS DE PAGAMENTO \n"			
			+ "6- SAIR DO SISTEMA \n\n"
			);

			switch (opt) {
			case 1:				
				ContaReceitaControlador c1 = new ContaReceitaControlador();
				c1.menu();
				break;
			case 2:
				ContaDespesaControlador c2 = new ContaDespesaControlador();
				c2.menu();
				break;	
			case 3:
				CaixaControlador c3 = new CaixaControlador();
				c3.menu();
				break;
			case 4:
				PlanoContaControlador c4 = new PlanoContaControlador();
				c4.menu();
				break;
			case 5:
				MetodoPagamentoControlador c5 = new MetodoPagamentoControlador();
				c5.menu();
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

}
