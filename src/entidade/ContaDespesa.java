package entidade;

import java.io.IOException;

import repositorio.MetodoPagamentoRepositorio;
import repositorio.PlanoContaRepositorio;

public class ContaDespesa extends Conta implements EntidadeInterface {

	public static String REFERENCIA = "conta_despesa"; 
	public static String NOME_ARQUIVO = "conta_despesa.txt";
	private double valorPago;
	private String dataPagamento;
	
	public ContaDespesa(int cod, String descricao, double valor, String dataVencimento, double valorPago, String dataPagamento,
			int planoConta, String contaStatus, int metodoPagamento) {
		
		super(cod, descricao, valor, dataVencimento, planoConta, contaStatus, metodoPagamento);
		
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
	}
	
	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}		

	public String toStringNormaliza() {

		PlanoContaRepositorio pcRepositorio = new PlanoContaRepositorio();
		MetodoPagamentoRepositorio mpRepositorio = new MetodoPagamentoRepositorio();
		String str = "";

		try {
			return super.getCod() + " - " + pcRepositorio.encontraPeloCodigo(super.getPlanoConta()).getDescricao() + " - " + super.getDescricao() + " - "
					+ Status.normaliza(super.getContaStatus()) + " - "
					+ mpRepositorio.encontraPeloCodigo(super.getMetodoPagamento()).getDescricao() + " | VALOR: " + super.getValor()
					+ " | DATA VENCIMENTO: " + super.getDataVencimento() + " | VALOR PAGO: " + valorPago + " | DATA PAGAMENTO: "
					+ dataPagamento;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	public String toStringArquivo() {
		return super.getCod() + ";" + super.getDescricao() + ";" + super.getValor() + ";" + super.getDataVencimento() + ";" + valorPago + ";" + dataPagamento + ";"
				+ super.getPlanoConta() + ";" + super.getContaStatus() + ";" + super.getMetodoPagamento() + ";";
	}
		
}
