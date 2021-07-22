package Stratege;

public class SmsChannelServiceImplB implements SmsChannelService{
    public void send(String phoneNo,String content) {
        System.out.println("使用渠道B发送短信");
    }

    public String getChannelType() {
        return "CHANNEL_B";
    }
}
