package entity;

import java.util.Calendar;

public class ContaReceita extends Conta {

	public static String CONTA_RECEITA = "conta_receita";
	
	public ContaReceita(String descricao, double valor, Calendar dataVencimento, double valorPago, Calendar dataPagamento,
			PlanoConta planoConta, Status contaStatus, MetodoPagamento metodoPagamento) {
		super(descricao, valor, dataVencimento, valorPago, dataPagamento, planoConta, contaStatus, metodoPagamento);		
	}
	
}
