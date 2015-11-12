package com.pallasli.jdbc.exception;

/**
 * <p>Title: ��ݿ�洢��̲������쳣��</p>
 * <p>Description: �Զ��������ݿ��쳣��</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class ProcNotExistException extends CallDbException
{

	private static final long serialVersionUID = -4400972945346712442L;

  public ProcNotExistException()
  {
      super();
  }

  public ProcNotExistException(String message)
  {
      super(message);
  }

  public ProcNotExistException(String message, Throwable cause)
  {
      super(message, cause);
  }

  public ProcNotExistException(Throwable cause)
  {
      super(cause);
  }

  public ProcNotExistException(String message, int errCode)
  {
      super(message);
      this.errCode = errCode;
  }

  public ProcNotExistException(String message, Throwable cause, int errCode)
  {
      super(message, cause);
      this.errCode = errCode;
  }

  public ProcNotExistException(Throwable cause, int errCode)
  {
      super(cause);
      this.errCode = errCode;
  }

}
