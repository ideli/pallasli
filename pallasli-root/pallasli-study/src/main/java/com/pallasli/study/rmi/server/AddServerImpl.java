package com.pallasli.study.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.pallasli.study.rmi.AddServer;

public class AddServerImpl extends UnicastRemoteObject implements AddServer {
	private static final long serialVersionUID = -2714005260158663264L;

	public AddServerImpl() throws RemoteException {
		super();
	}

	public int AddNumbers(int firstnumber, int secondnumber)
			throws RemoteException {
		return firstnumber + secondnumber;
	}
}