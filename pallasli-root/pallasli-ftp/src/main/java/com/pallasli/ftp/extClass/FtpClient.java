package com.pallasli.ftp.extClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpDirParser;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;

public class FtpClient extends sun.net.ftp.FtpClient {
	private String server;
	private int port;
	private String name;
	private String pass;

	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public FtpClient(String server, int port) throws IOException {
		super();
		this.server = server;
	}

	public FtpClient() {
		super();
	}


	public String getServer() {
		return server;
	}

	@Override
	public sun.net.ftp.FtpClient abort() throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient allocate(long arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient appendFile(String arg0, InputStream arg1)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient changeDirectory(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient changeToParentDirectory()
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public sun.net.ftp.FtpClient completePending() throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient connect(SocketAddress arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient connect(SocketAddress arg0, int arg1)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient deleteFile(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient enablePassiveMode(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient endSecureSession()
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getConnectTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getFeatures() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient getFile(String arg0, OutputStream arg1)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getFileStream(String arg0) throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelp(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLastModified(String arg0) throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FtpReplyCode getLastReplyCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastResponseString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLastTransferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Proxy getProxy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReadTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SocketAddress getServerAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSize(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStatus(String arg0) throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSystem() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWelcomeMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWorkingDirectory() throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPassiveModeEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InputStream list(String arg0) throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<FtpDirEntry> listFiles(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient login(String arg0, char[] arg1)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient login(String arg0, char[] arg1, String arg2)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient makeDirectory(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream nameList(String arg0) throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient putFile(String arg0, InputStream arg1,
			boolean arg2) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream putFileStream(String arg0, boolean arg1)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient reInit() throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient removeDirectory(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient rename(String arg0, String arg1)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient setConnectTimeout(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient setDirParser(FtpDirParser arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient setProxy(Proxy arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient setReadTimeout(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient setRestartOffset(long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient setType(TransferType arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient siteCmd(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient startSecureSession()
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient structureMount(String arg0)
			throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient useKerberos() throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public sun.net.ftp.FtpClient noop() throws FtpProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public OutputStream put(String remoteFile) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean serverIsOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public void readServerResponse() {
		// TODO Auto-generated method stub
		
	}

	public void sendServer(String string) {
		// TODO Auto-generated method stub
		
	}

	public void binary() {
		// TODO Auto-generated method stub
		
	}

	public String pwd() {
		// TODO Auto-generated method stub
		return null;
	}

	public void login(String userStr, String passStr) {
		// TODO Auto-generated method stub
		
	}

	public void openServer(String trim, int port2) {
		// TODO Auto-generated method stub
		
	}

	public void cd(String name2) {
		// TODO Auto-generated method stub
		
	}

	public void cdUp() {
		// TODO Auto-generated method stub
		
	}

	public TelnetInputStream get(String name2) {
		// TODO Auto-generated method stub
		return null;
	}

	public TelnetInputStream list() {
		// TODO Auto-generated method stub
		return null;
	}
}
