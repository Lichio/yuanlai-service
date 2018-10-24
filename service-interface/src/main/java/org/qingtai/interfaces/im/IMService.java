package org.qingtai.interfaces.im;

/**
 * org.qingtai.interfaces.im
 * Created on 2017/11/8
 *
 * @author Lichaojie
 */
public interface IMService {
	boolean sendMessage(long fromAccount,long toAccount,String content);
}
