import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {
                
                // get a map of iflow properties
                def map = message.getProperties();
                //def refernceID = map.get("ReferenceID")
                def logException = map.get("ExceptionLogging")
                def article = map.get("Article")
                
                def body = message.getBody(java.lang.String) as String;
                
                // get an exception java class instance
                def ex = map.get("CamelExceptionCaught");
                if (ex!=null) {
                 // save the error response as a message attachment 
                def messageLog = messageLogFactory.getMessageLog(message);
                def errordetails = ex.getResponseBody()
                def attachID  = article + "-Error Details"
                if (logException != null && logException.equalsIgnoreCase("YES")) {
                messageLog.addAttachmentAsString(attachID, errordetails, "text/plain");
                }
                }

                return message;
}
