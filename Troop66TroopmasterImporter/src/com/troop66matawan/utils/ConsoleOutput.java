package com.troop66matawan.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ConsoleOutput implements Output {

	@Override
	public void append(String x) {
		System.out.println(x);

	}

	@Override
	public void append(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		append(sw.toString());
	}

}
