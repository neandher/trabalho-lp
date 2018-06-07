package entidade;

public abstract class Conta {

	private int cod;
	private String descricao;
	private double valor;
	private String dataVencimento;
	private int planoConta;
	private String contaStatus;
	private int metodoPagamento;

	public Conta(int cod, String descricao, double valor, String dataVencimento, int planoConta, String contaStatus,
			int metodoPagamento) {
		super();
		this.cod = cod;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.planoConta = planoConta;
		this.contaStatus = contaStatus;
		this.metodoPagamento = metodoPagamento;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
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

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(int planoConta) {
		this.planoConta = planoConta;
	}

	public String getContaStatus() {
		return contaStatus;
	}

	public void setContaStatus(String contaStatus) {
		this.contaStatus = contaStatus;
	}

	public int getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(int metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}	

	public static String normalizaRef(String contaReferencia) {
		return contaReferencia.equals(ContaDespesa.REFERENCIA) ? "Despesa" : "Receita";
	}
	
	public abstract String toStringNormaliza();
	
	public abstract String toStringArquivo();
}
