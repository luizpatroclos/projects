package br.gov.inpi.cliente.timer;

import java.util.Calendar;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class Teste {
	public static void main(String[] args) {

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		calendar2.set(2014, 8, 14, 8, 50, 30);
		calendar1.set(2014, 8, 14, 0, 10, 00);

		System.out.println(DurationFormatUtils.formatPeriod(calendar1.getTimeInMillis(), calendar2.getTimeInMillis(), "HH:mm:ss"));

	}
}
