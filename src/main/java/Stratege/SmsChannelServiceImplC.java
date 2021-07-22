package Stratege;

public class SmsChannelServiceImplC implements SmsChannelService {
    public void send(String phoneNo,String content) {
        System.out.println("通过渠道C发送短信");
    }
    public String getChannelType() {
        return "CHANNEL_C";
    }
}
