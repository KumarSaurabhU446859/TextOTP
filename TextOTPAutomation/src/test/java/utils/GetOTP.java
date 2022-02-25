package utils;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class GetOTP {
	
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	public static final String CONTACT_NUM = "+18647277536";
	
	public static String getOTPNumber() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		String messageBody="";
		for(Message messg:messages)
		{
			if (messg.getTo().equals(CONTACT_NUM)) {
				messageBody= messg.getBody();
				break;
			}
		}
		String OTPNumber = messageBody.toString().replaceAll("[^-?0-9]+", " ");
		return OTPNumber;
		//return getMessages().toString();
	}

}
