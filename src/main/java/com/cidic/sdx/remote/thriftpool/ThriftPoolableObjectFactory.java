package com.cidic.sdx.remote.thriftpool;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftPoolableObjectFactory extends BasePooledObjectFactory<TTransport> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/** �����IP */
	private String serviceIP;
	/** ����Ķ˿� */
	private int servicePort;
	/** ��ʱ���� */
	private int timeOut;

	public ThriftPoolableObjectFactory(String serviceIP, int servicePort, int timeOut) {
		super();
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeOut = timeOut;
	}

	@Override
	public PooledObject<TTransport> wrap(TTransport value) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<TTransport>(value);
	}

	@Override
	public TTransport create() throws Exception {
		TTransport transport = new TSocket(this.serviceIP, this.servicePort, this.timeOut);  
        transport.open();  
        return transport;
	}

}
