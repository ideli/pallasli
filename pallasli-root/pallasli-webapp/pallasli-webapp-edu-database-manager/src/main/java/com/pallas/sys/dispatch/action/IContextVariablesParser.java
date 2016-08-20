package com.pallas.sys.dispatch.action;


public abstract interface IContextVariablesParser
{
  public abstract String replaceContextVariables(String paramString);
}