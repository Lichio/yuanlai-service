package org.qingtai.impl.test;

import com.alibaba.dubbo.config.annotation.Service;
import org.qingtai.interfaces.test.DemoService;

/**
 * org.qingtai.impl.test
 * Created on 2017/11/1
 *
 * @author Lichaojie
 */
@Service
public class DemoServiceImpl implements DemoService {

	public String sayHello(String name){
		return "Hello " + name;
	}
}
