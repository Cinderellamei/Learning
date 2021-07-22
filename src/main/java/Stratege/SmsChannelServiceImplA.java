package Stratege;

//@Service
public class SmsChannelServiceImplA implements SmsChannelService{
    public void send(String phoneNo,String content) {
        System.out.println("通过短信渠道A发送短信");
    }
    public String getChannelType() {
        return "CHANNEL_A";
    }
}
