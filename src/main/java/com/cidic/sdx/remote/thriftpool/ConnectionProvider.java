package com.cidic.sdx.remote.thriftpool;

import org.apache.thrift.transport.TSocket;

public interface ConnectionProvider {

	/**
     * ȡ���ӳ��е�һ������
     * 
     * @return
     */
    public TSocket getConnection();
    /**
     * ��������
     * 
     * @param socket
     */
    public void returnCon(TSocket socket);
}
