package com.atwasoft.framework.container;

import java.io.IOException;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataServer extends AbstractServer
{
	private static final Logger log=LoggerFactory.getLogger(DataServer.class);

	public DataServer(){
		super();
	}
    public static void main(String[] args) throws IOException {
    	
        new DataServer();
    }
    
    public static void GuiStart()
    {
    	(new Thread(new Runnable(){
    		public void run()
    		{
               	try
               	{
               		new DataServer();
               	}
               	catch(Exception e)
               	{
               		log.error(e.getMessage());
               	}
            }
        })).start();  	
    	
    }

}