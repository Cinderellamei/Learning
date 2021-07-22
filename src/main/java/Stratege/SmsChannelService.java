package Stratege;

public interface SmsChannelService {
    void send(String phoneNo,String content);
    String getChannelType();
}
