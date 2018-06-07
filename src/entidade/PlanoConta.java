package entidade;

public class PlanoConta implements EntidadeInterface {

	public static String NOME_ARQUIVO = "plano_conta.txt";
	private int cod;
	private String descricao;
	private String tipoPlanoConta;

	public PlanoConta(int cod, String descricao, String tipoPlanoConta) {
		super();
		this.cod = cod;
		this.descricao = descricao;
		this.tipoPlanoConta = tipoPlanoConta;
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

	public String getTipoPlanoConta() {
		return tipoPlanoConta;
	}

	public void setTipoPlanoConta(String tipoPlanoConta) {
		this.tipoPlanoConta = tipoPlanoConta;
	}		
	
	@Override
	public String toString() {
		return cod + " - " + descricao + " - " + Conta.normalizaRef(tipoPlanoConta);
	}

	public String toStringArquivo() {
		return cod + ";" + descricao + ";" + tipoPlanoConta + ";";
	}

}
