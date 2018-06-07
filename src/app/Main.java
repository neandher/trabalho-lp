package app;

import java.io.IOException;
import controlador.ContaDespesaControlador;
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
			+ "3- PLANO DE CONTAS \n" 
			+ "4- METODOS DE PAGAMENTO \n"			
			+ "5- SAIR DO SISTEMA \n\n"
			);

			switch (opt) {
			case 1:				
				//
				break;
			case 2:
				ContaDespesaControlador c2 = new ContaDespesaControlador();
				c2.menu();
				break;	
			case 3:
				PlanoContaControlador c3 = new PlanoContaControlador();
				c3.menu();
				break;
			case 4:
				MetodoPagamentoControlador c4 = new MetodoPagamentoControlador();
				c4.menu();
				break;
			case 5:
				opt = -1;
				break;

			default:
				opt = -1;
				break;
			}

		} while (opt != -1);
	}

}
