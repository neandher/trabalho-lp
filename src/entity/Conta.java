package entity;

import java.util.Calendar;

abstract class Conta {
	
	private String descricao;
	private double valor;
	private Calendar dataVencimento;
	private double valorPago;
	private Calendar dataPagamento;
	private PlanoConta planoConta;
	private Status contaStatus;
	private MetodoPagamento metodoPagamento;
	
	public Conta(String descricao, double valor, Calendar dataVencimento, double valorPago, Calendar dataPagamento,
			PlanoConta planoConta, Status contaStatus, MetodoPagamento metodoPagamento) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
		this.planoConta = planoConta;
		this.contaStatus = contaStatus;
		this.metodoPagamento = metodoPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public PlanoConta getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	public Status getContaStatus() {
		return contaStatus;
	}

	public void setContaStatus(Status contaStatus) {
		this.contaStatus = contaStatus;
	}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
	
	
}
