package util;

import java.util.Calendar;

public class Data {

	public static Calendar converteParaData(String str) {
		int dia, mes, ano;
		String[] aux;
		Calendar data = Calendar.getInstance();
		aux = str.split("/");
		dia = Integer.parseInt(aux[0]);
		mes = Integer.parseInt(aux[1]) - 1;
		ano = Integer.parseInt(aux[2]);
		data.set(ano, mes, dia);
		
		return data;
	}
}
