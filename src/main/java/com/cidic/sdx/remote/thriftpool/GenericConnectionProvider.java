package com.cidic.sdx.remote.thriftpool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class GenericConnectionProvider implements ConnectionProvider, InitializingBean, DisposableBean{

	public static final Logger logger = LoggerFactory
            .getLogger(GenericConnectionProvider.class);
	 /** �����IP��ַ */  
    private String serviceIP;  
    /** ����Ķ˿� */  
    private int servicePort;  
    /** ���ӳ�ʱ���� */  
    private int conTimeOut;  
    /** ���Դӻ�����з�������������� */  
    private int maxActive = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;  
    /** ������������ж������� */  
    private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;  
    /** ���������С���ж������� */  
    private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;  
    /** ������������� */  
    private long maxWait = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;  
  
    /** �ӻ�����з�������Ƿ�ִ��PoolableObjectFactory.validateObject���� */  
    private boolean testOnBorrow = GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW;  
    private boolean testOnReturn = GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN;  
    private boolean testWhileIdle = GenericObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE;  
  
    /** ���󻺴�� */  
    private ObjectPool<TTransport> objectPool = null;  
  
    @Override  
    public TSocket getConnection() {  
        try {  
            // �Ӷ����ȡ����  
            TSocket socket = (TSocket) objectPool.borrowObject();  
            return socket;  
        } catch (Exception e) {  
            throw new RuntimeException("error getConnection()", e);  
        }  
    }  
  
    @Override  
    public void returnCon(TSocket socket) {  
        try {  
            // ������Żض����  
            objectPool.returnObject(socket);  
        } catch (Exception e) {  
            throw new RuntimeException("error returnCon()", e);  
        }  
    }  
  
    @Override  
    public void destroy() throws Exception {  
        try {  
            objectPool.close();  
        } catch (Exception e) {  
            throw new RuntimeException("erorr destroy()", e);  
        }  
    }  
  
    @SuppressWarnings("deprecation")  
    @Override  
    public void afterPropertiesSet() throws Exception {  
        // �����  
        objectPool = new GenericObjectPool<TTransport>(new ThriftPoolableObjectFactory(serviceIP, servicePort, conTimeOut));  
        //  
        ((GenericObjectPool<TTransport>) objectPool).setMaxTotal(maxActive);  
        ((GenericObjectPool<TTransport>) objectPool).setMaxIdle(maxIdle);  
        ((GenericObjectPool<TTransport>) objectPool).setMinIdle(minIdle);  
        ((GenericObjectPool<TTransport>) objectPool).setMaxWaitMillis(maxWait);  
        ((GenericObjectPool<TTransport>) objectPool).setTestOnBorrow(testOnBorrow);  
        ((GenericObjectPool<TTransport>) objectPool).setTestOnReturn(testOnReturn);  
        ((GenericObjectPool<TTransport>) objectPool).setTestWhileIdle(testWhileIdle);  
        ((GenericObjectPool<TTransport>) objectPool).setBlockWhenExhausted(GenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED);
    }  
  
    public String getServiceIP() {  
        return serviceIP;  
    }  
  
    public void setServiceIP(String serviceIP) {  
        this.serviceIP = serviceIP;  
    }  
  
    public int getServicePort() {  
        return servicePort;  
    }  
  
    public void setServicePort(int servicePort) {  
        this.servicePort = servicePort;  
    }  
  
    public int getConTimeOut() {  
        return conTimeOut;  
    }  
  
    public void setConTimeOut(int conTimeOut) {  
        this.conTimeOut = conTimeOut;  
    }  
  
    public int getMaxActive() {  
        return maxActive;  
    }  
  
    public void setMaxActive(int maxActive) {  
        this.maxActive = maxActive;  
    }  
  
    public int getMaxIdle() {  
        return maxIdle;  
    }  
  
    public void setMaxIdle(int maxIdle) {  
        this.maxIdle = maxIdle;  
    }  
  
    public int getMinIdle() {  
        return minIdle;  
    }  
  
    public void setMinIdle(int minIdle) {  
        this.minIdle = minIdle;  
    }  
  
    public long getMaxWait() {  
        return maxWait;  
    }  
  
    public void setMaxWait(long maxWait) {  
        this.maxWait = maxWait;  
    }  
  
    public boolean isTestOnBorrow() {  
        return testOnBorrow;  
    }  
  
    public void setTestOnBorrow(boolean testOnBorrow) {  
        this.testOnBorrow = testOnBorrow;  
    }  
  
    public boolean isTestOnReturn() {  
        return testOnReturn;  
    }  
  
    public void setTestOnReturn(boolean testOnReturn) {  
        this.testOnReturn = testOnReturn;  
    }  
  
    public boolean isTestWhileIdle() {  
        return testWhileIdle;  
    }  
  
    public void setTestWhileIdle(boolean testWhileIdle) {  
        this.testWhileIdle = testWhileIdle;  
    }  
  
    public ObjectPool<TTransport> getObjectPool() {  
        return objectPool;  
    }  
  
    public void setObjectPool(ObjectPool<TTransport> objectPool) {  
        this.objectPool = objectPool;  
    }  

}
