package sdev425;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;


public final class MFATextClass extends SDEV425HW2 {

    public MFATextClass(String phone, String authCode) throws TwilioRestException  {

        multiFactAuth(phone, authCode);

    }

    public void multiFactAuth(String phone, String authCode) throws TwilioRestException{
        
      
              TwilioRestClient client = new TwilioRestClient(Tag.ACCOUNT_SID, Tag.AUTH_TOKEN);
        
        String messageBody = "Hi Kalada here, your Authorization Code is: ";

        // Build a filter for the MessageList
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(Tag.MESSAGE_BODY, messageBody + authCode));
        params.add(new BasicNameValuePair(Tag.MESSAGE_TO, phone));
        params.add(new BasicNameValuePair(Tag.MESSAGE_FROM, Tag.DEFAULT_PHONE));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        messageFactory.create(params);
            
        
      

    }

}
