package SmsSend;

import Factory.SmsChannelFactory;
import Stratege.SmsChannelService;

import javax.annotation.Resource;

//@Service
public class SmsSendService {

    @Resource
    private SmsChannelFactory smsChannelFactory;

    /*private SmsSendService() {
        smsChannelFactory = new SmsChannelFactory();
    }*/

    public void send(String phoneNo,String content) {
        String channelType = "";
        SmsChannelService channelService = smsChannelFactory.buildService(channelType);
        channelService.send(phoneNo,content);
    }
}
