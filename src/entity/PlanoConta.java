package entity;

public class PlanoConta {

	public static String NOME_ARQUIVO = "plano_conta.txt";
	private int cod;
	private String descricao;
	private String tipoConta;

	public PlanoConta(int cod, String descricao, String tipoConta) {
		super();
		this.cod = cod;
		this.descricao = descricao;
		this.tipoConta = tipoConta;
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

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

}
