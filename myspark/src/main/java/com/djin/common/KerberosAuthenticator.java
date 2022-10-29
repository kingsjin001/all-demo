package com.djin.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * update by dj at 2022/10/29
 * Kerberos认证工具类
 */
public class KerberosAuthenticator {
    public static void authenticate(String krb5ConfPath, String keytabUser, String keytabPath) {
        //System.out.println("krb5.conf.path:" + krb5ConfPath);
        //System.out.println("keytabUser:" + keytabUser);
        //System.out.println("keytabPath:" + keytabPath);
        System.setProperty("java.security.krb5.conf", krb5ConfPath);
        try {
            // 用户认证
            UserGroupInformation.setConfiguration(new Configuration());
            UserGroupInformation.loginUserFromKeytab(keytabUser, keytabPath);
            System.out.println("+---------- Kerberos身份认证成功! ----------+");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startK() {
        ReadProp prop = new ReadProp();
        String KRB5_CONF_PATH_LOCAL = prop.getProperty("KRB5_CONF_PATH_LOCAL");
        String KEYTAB_USER = prop.getProperty("KEYTAB_USER");
        String KEYTAB_PATH_LOCAL = prop.getProperty("KEYTAB_PATH_LOCAL");
        KerberosAuthenticator.authenticate(KRB5_CONF_PATH_LOCAL, KEYTAB_USER, KEYTAB_PATH_LOCAL);
    }
}
