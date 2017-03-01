package com.mvp01.common.utils;

import com.bcloud.msg.http.HttpSender;
import org.apache.commons.lang.StringUtils;

/**
 * 手机号短信验证网关
 * Created by zhangyueping on 15/4/16.
 */
public class SMS2Util implements Runnable {

    private String SMSCode;
    private String mobilePhone;
    private String SMSDesc; //短信描述
    private String receiveMobile;//收短信的电话号码

    public String getSMSDesc() {
        return SMSDesc;
    }

    public void setSMSDesc(String SMSDesc) {
        this.SMSDesc = SMSDesc;
    }

    public String getSMSCode() {
        return SMSCode;
    }

    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    @Override
    public void run() {

        if (StringUtils.isNotBlank(mobilePhone)) {

            String uri = "http://sapi.253.com/msg/";//应用地址
            String account = "VIP_MVP01";//账号
            String pswd = "Yo10288150";//密码
            String mobiles = mobilePhone;//"13800210021,13800138000";//手机号码，多个号码使用","分割(一次支持5w个手机号码)
            String content = "";//String.format("您的验证码是：%s，请尽快完成验证。如非本人操作，可不用理会！", SMSCode);//短信内容

            if (StringUtils.isNotBlank(receiveMobile)) {
                mobiles = receiveMobile;//把短信发送给接受的手机方
            }

            if (!StringUtils.isBlank(SMSCode)) {
                if (StringUtils.isBlank(getSMSDesc())) {
                    if (StringUtils.isBlank(receiveMobile)) {
                        content = String.format("您的验证码是：%s，请尽快完成验证。如非本人操作，可不用理会！", SMSCode);
                    } else {
                        content = String.format(mobilePhone+"的验证码是：%s，请尽快完成验证。如非本人操作，可不用理会！", SMSCode);
                    }
                } else {
                    content = String.format(getSMSDesc(), SMSCode);
                }
            } else {
                if (StringUtils.isBlank(getSMSDesc())) {
                    return;
                } else {
                    content = getSMSDesc();
                }
            }

            boolean needstatus = true;//是否需要状态报告，需要true，不需要false
            String product = null;//产品ID
            String extno = null;//扩展码

            try {
                String returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);

                //TODO 处理返回值,参见HTTP协议文档
            } catch (Exception e) {
                //TODO 处理异常
                e.printStackTrace();
            }


        } else {
        }

    }

    public void send() {
        ThreadExecutor.exec(this);
    }

    public static void main (String[] args) {
        SMS2Util sms2Util = new SMS2Util();
        sms2Util.setMobilePhone("18500865387");
        sms2Util.setSMSCode("123456");
        sms2Util.send();
    }

}
