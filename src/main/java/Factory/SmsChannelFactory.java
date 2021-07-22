package Factory;

import Stratege.SmsChannelService;
import Stratege.SmsChannelServiceImplA;
import Stratege.SmsChannelServiceImplB;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmsChannelFactory {
    private Map<String, SmsChannelService> serviceMap;

    //通过spring容器，将所有实现SmsChannelService接口的类的实例注入到serviceList中
    @Resource
    private List<SmsChannelService> serviceList;

    //通过@PostConstruct注解，在SmsChannelFactory实例化后，来初始化serviceMap
    @PostConstruct
    public void init() {
        if(CollectionUtils.isEmpty(serviceList)) {
            return ;
        }
        serviceMap = new HashMap<String,SmsChannelService>(serviceList.size());
        //将serviceList转化为serviceMap(因为map可以直接通过get方法取到channelType所对应的值)
        for(SmsChannelService channelService:serviceList) {
            String channelType = channelService.getChannelType();
            //重复性校验，避免不同实现类的getChannel()方法返回同一个值
            if(serviceMap.get(channelType)!=null) {
                throw new RuntimeException("同一个短信渠道只能有一个实现类");
            }
            serviceMap.put(channelType,channelService);
        }
    }

    /*public SmsChannelFactory() {
        serviceMap = new HashMap<String,SmsChannelService>(2);
        serviceMap.put("CHANNEL_A",new SmsChannelServiceImplA());
        serviceMap.put("CHANNEL_B",new SmsChannelServiceImplB());
    }*/

    public SmsChannelService buildService(String channelType) {
        return serviceMap.get(channelType);
    }
}
