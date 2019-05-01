package com.java2s.common;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class UserBean {

	@Inject
	Printer printer;

	public void setPrinter(Printer p) {
		this.printer = p;
	}

	public String printMsgFromSpring() {
		return printer.print();
	}

}