package entidade;

import java.io.IOException;

import repositorio.MetodoPagamentoRepositorio;
import repositorio.PlanoContaRepositorio;

public class ContaReceita extends Conta implements EntidadeInterface {

	public static String REFERENCIA = "conta_receita";
	public static String NOME_ARQUIVO = "conta_receita.txt";
	private double valorRecebido;
	private String dataRecebimento;
	
	public ContaReceita(int cod, String descricao, double valor, String dataVencimento, double valorRecebido, String dataRecebimento,
			int planoConta, String contaStatus, int metodoPagamento) {
		
		super(cod, descricao, valor, dataVencimento, planoConta, contaStatus, metodoPagamento);
		
		this.valorRecebido = valorRecebido;
		this.dataRecebimento = dataRecebimento;
	}

	public double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public String getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public String toStringNormaliza() {

		PlanoContaRepositorio pcRepositorio = new PlanoContaRepositorio();
		MetodoPagamentoRepositorio mpRepositorio = new MetodoPagamentoRepositorio();
		String str = "";

		try {
			return super.getCod() + " - " + pcRepositorio.encontraPeloCodigo(super.getPlanoConta()).getDescricao() + " - " + super.getDescricao() + " - "
					+ Status.normaliza(super.getContaStatus()) + " - "
					+ mpRepositorio.encontraPeloCodigo(super.getMetodoPagamento()).getDescricao() + " | VALOR: " + super.getValor()
					+ " | DATA VENCIMENTO: " + super.getDataVencimento() + " | VALOR RECEBIDO: " + valorRecebido + " | DATA RECEBIMENTO: "
					+ dataRecebimento;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	public String toStringArquivo() {
		return super.getCod() + ";" + super.getDescricao() + ";" + super.getValor() + ";" + super.getDataVencimento() + ";" + valorRecebido + ";" + dataRecebimento + ";"
				+ super.getPlanoConta() + ";" + super.getContaStatus() + ";" + super.getMetodoPagamento() + ";";
	}
}
