import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
    //Body 
       def body = message.getBody(java.lang.String) as String;
       def root = new XmlParser().parseText(body);
       def token = root.access_token.text();
       
       message.setHeader("Authorization", "Bearer " + token);
//       message.setHeader("A", "Bearer " + token);
     
//       def map = message.getProperties();
//       def value = map.get("payload") 
//       message.setBody(value);
     
       return message;
}
