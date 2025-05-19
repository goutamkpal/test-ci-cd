import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import com.sap.it.api.ITApiFactory;
import com.sap.it.api.securestore.SecureStoreService;
import com.sap.it.api.securestore.UserCredential;

def Message processData(Message message)
{

map = message.getProperties();
lv_credential_Name = map.get("user_pos");

def service = ITApiFactory.getApi(SecureStoreService.class, null);
def credential = service.getUserCredential(lv_credential_Name);

if (credential == null)
{
throw new IllegalStateException("No credential found");
}

String lv_username = credential.getUsername();
String lv_password = new String(credential.getPassword());

message.setProperty("UNAME", lv_username);
message.setProperty("PWD", lv_password);

return message;
}