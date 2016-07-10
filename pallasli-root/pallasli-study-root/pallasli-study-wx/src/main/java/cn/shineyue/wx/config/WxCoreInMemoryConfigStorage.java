package cn.shineyue.wx.config;

import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.InputStream;


/**
 * @Description 基于内存的微信客户端配置
 * 由于使用了spring注入，该类暂不用
 * @author zhangchanglong
 * @created zhangchanglong  2015年8月6日
 * @version 1
 */
@XStreamAlias("xml")
public class WxCoreInMemoryConfigStorage extends WxMpInMemoryConfigStorage {

  @Override
  public String toString() {
    return "SimpleWxConfigProvider [appId=" + appId + ", secret=" + secret + ", accessToken=" + accessToken
        + ", expiresTime=" + expiresTime + ", token=" + token + ", aesKey=" + aesKey + "]";
  }


  public static WxCoreInMemoryConfigStorage fromXml(InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxCoreInMemoryConfigStorage.class);
    return (WxCoreInMemoryConfigStorage) xstream.fromXML(is);
  }

}
