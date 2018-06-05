package entity;

import java.util.Calendar;

public class ContaDespesa extends Conta {

	public static String CONTA_DESPESA = "conta_despesa"; 
	
	public ContaDespesa(String descricao, double valor, Calendar dataVencimento, double valorPago, Calendar dataPagamento,
			PlanoConta planoConta, Status contaStatus, MetodoPagamento metodoPagamento) {
		super(descricao, valor, dataVencimento, valorPago, dataPagamento, planoConta, contaStatus, metodoPagamento);
	}
		
}
