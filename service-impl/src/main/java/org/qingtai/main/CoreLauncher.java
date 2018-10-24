package org.qingtai.main;

/**
 * org.qingtai.main
 * Created on 2017/11/1
 *
 * @author Lichaojie
 */
public class CoreLauncher {
	public static void main(String[] args){
		com.alibaba.dubbo.container.Main.main(args);

//		try {
//			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/dubbo-provider.xml");
//			context.start();
//		} catch (Exception e) {
//			//log.error("== DubboProvider context start error:",e);
//		}
//		synchronized (CoreLauncher.class) {
//			while (true) {
//				try {
//					CoreLauncher.class.wait();
//				} catch (InterruptedException e) {
//					//log.error("== synchronized error:",e);
//				}
//			}
//		}
	}
}
