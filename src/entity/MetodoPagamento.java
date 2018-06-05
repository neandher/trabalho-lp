package entity;

public class MetodoPagamento {
	
	private int cod;
	private String descricao;
	
	public MetodoPagamento(int cod, String descricao) {
		super();
		this.cod = cod;
		this.descricao = descricao;
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
}
