package com.ipiecoles.java.java240;

import org.springframework.context.annotation.*;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.BitSet;

@Configuration
@ComponentScan(basePackages = "com.ipiecoles.java.java240")
public class SpringConfig {

    @Bean
    public Boolean disableSSLValidation() throws Exception {
        final SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        return true;
    }

    @Bean (name = "bitcoinService")
    @Scope("singleton")
    public BitcoinService bitcoinService(){
        System.out.println("Instantiation du bean BitcoinService par Spring");
        BitcoinService bitcoinService = new BitcoinService(/*webPageManager()*/);
        return bitcoinService;
    }

    @Bean (name = "bitcoinServiceNC")
    @Primary
    public BitcoinService bitcoinServiceNoCache(){
        System.out.println("Instantiation du bean BitcoinService sans cache par Spring");
        BitcoinService bitcoinService = new BitcoinService(/*webPageManager()*/);
        bitcoinService.setForceRefresh(true);
        return bitcoinService;
    }

    /*@Bean
    @Scope("singleton")
    public ProduitManager produitManager() {
        return new ProduitManager(bitcoinService(), webPageManager());

    }*/

    /*@Bean
    @Scope("singleton")
    public WebPageManager webPageManager(){
        return new WebPageManager();
    }*/
}
