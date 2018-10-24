package org.qingtai.impl.im;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.qingtai.interfaces.im.IMService;
import org.apache.http.client.HttpClient;

import java.io.*;
import java.util.Map;

/**
 * org.qingtai.impl.im
 * Created on 2017/11/8
 *
 * @author Lichaojie
 */
public class IMServiceImpl extends IMBase implements IMService {

	Logger logger = Logger.getLogger(IMServiceImpl.class);


	public boolean sendMessage(long fromAccount, long toAccount, String content){
		HttpClient httpClient = new DefaultHttpClient();

		String host = "https://console.tim.qq.com/v4/openim/sendmsg?";

		String identifier = "admin";
		String usersig = doMain(identifier);
		long sdkappid = 1400037242;
		int random = 1;
		String contenttype = "json";

		String url = host + "usersig=" + usersig + "&"
				+ "identifier=" + identifier + "&"
				+ "sdkappid=" + sdkappid + "&"
				+ "random=" + random + "&"
				+ "contenttype=" + contenttype;
		HttpPost httpPost = new HttpPost(url);

		JSONObject text = new JSONObject();
		text.put("Text",content);

		JSONObject msgBody = new JSONObject();
		msgBody.put("MsgType","TIMTextElem");
		msgBody.put("MsgContent",text);

		JSONObject msgBodyArray[] = new JSONObject[1];
		msgBodyArray[0] = msgBody;

		JSONObject responseBody = new JSONObject();
		responseBody.put("SyncOtherMachine",1);
//        UserInfo fromUser = userInfoMapper.selectByPrimaryKey(from_account);
//        UserInfo toUser = userInfoMapper.selectByPrimaryKey(to_account);
		responseBody.put("From_Account",Long.toString(fromAccount));
		responseBody.put("To_Account",Long.toString(toAccount));
		responseBody.put("MsgRandom",1287657);
		responseBody.put("MsgTimeStamp", System.currentTimeMillis() / 1000);
		responseBody.put("MsgBody", msgBodyArray);

		StringEntity stringEntity = new StringEntity(responseBody.toString(),"UTF-8");
		logger.info("request : \n" + responseBody.toString());
		System.out.println("request : \n" + responseBody.toString());
		httpPost.setEntity(stringEntity);

		StringBuffer result = new StringBuffer();
		try {
			HttpResponse response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		}catch (Exception ex){

		}

		System.out.println("response : \n" + result.toString());
		logger.info("response : " + result.toString());

		ObjectMapper objectMapper = new ObjectMapper();
		Map paraMap = null;
		try{
			paraMap = objectMapper.readValue(result.toString(),Map.class);
		}catch (IOException e){
			e.printStackTrace();
		}
		String actionStatus = (String)paraMap.get("ActionStatus");
		System.out.println("actionStatus : " + actionStatus);

		if(actionStatus.equals("OK")){
			return true;
		}else {
			return false;
		}
	}
}
