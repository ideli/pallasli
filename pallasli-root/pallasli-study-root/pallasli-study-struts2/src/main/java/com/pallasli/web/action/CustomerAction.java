package com.pallasli.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pallasli.web.model.Customer;

@SuppressWarnings("serial")
public class CustomerAction extends ActionSupport implements
		ModelDriven<Customer> {

	// have to initialize it
	Customer customer = new Customer();

	@Override
	public String execute() throws Exception {

		return SUCCESS;

	}

	@Override
	public Customer getModel() {

		return customer;

	}
}