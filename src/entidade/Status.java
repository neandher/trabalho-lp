package entidade;

import java.util.HashMap;

public class Status {

	public static String CONTA_STATUS_EM_ABERTO = "em_aberto";
	public static String CONTA_STATUS_PAGO = "pago";
	public static String CONTA_STATUS_RECEBIDO = "recebido";
	public static HashMap<String, String> OPCOES = new HashMap<>();

	public static String normaliza(String opcao) {
		popula();
		return Status.OPCOES.get(opcao);
	}

	public static void popula() {
		Status.OPCOES.put(Status.CONTA_STATUS_EM_ABERTO, "Em Aberto");
		Status.OPCOES.put(Status.CONTA_STATUS_PAGO, "Pago");
		Status.OPCOES.put(Status.CONTA_STATUS_RECEBIDO, "Recebido");
	}
}
