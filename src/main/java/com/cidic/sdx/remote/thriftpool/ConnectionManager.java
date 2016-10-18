package com.cidic.sdx.remote.thriftpool;

import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionManager {

	 /** ��־��¼�� */  
    private final Logger logger = LoggerFactory.getLogger(getClass());  
    /** ����local���� */  
    ThreadLocal<TSocket> socketThreadSafe = new ThreadLocal<TSocket>();  
  
    /** �����ṩ�� */  
    @Autowired  
    private ConnectionProvider connectionProvider;  
  
    public TSocket getSocket() {  
        TSocket socket = null;  
        try {  
            socket = connectionProvider.getConnection();  
            socketThreadSafe.set(socket);  
            return socketThreadSafe.get();  
        } catch (Exception e) {  
            logger.error("error ConnectionManager.invoke()", e);  
        } finally {  
            connectionProvider.returnCon(socket);  
            socketThreadSafe.remove();  
        }  
        return socket;  
    }  
}
