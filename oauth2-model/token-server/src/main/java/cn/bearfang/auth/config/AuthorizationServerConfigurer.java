package cn.bearfang.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2022-06-12 22:53
 **/
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置令牌访问的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //自定义ClientCredentialsTokenEndpointFilter，用于处理客户端id，密码错误的异常
//        OAuthServerClientCredentialsTokenEndpointFilter endpointFilter = new OAuthServerClientCredentialsTokenEndpointFilter(security,authenticationEntryPoint);
//        endpointFilter.afterPropertiesSet();
//        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security
                .allowFormAuthenticationForClients()//ClientCredentialsTokenEndpointFilter生效，从而获取param中的client_id client_secret
                //开启/oauth/token_key验证端口权限访问
                .tokenKeyAccess("permitAll()")
                //开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
        //一定不要添加allowFormAuthenticationForClients，否则自定义的OAuthServerClientCredentialsTokenEndpointFilter不生效
//                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("demoApp")
                .secret("demoAppSecret")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .scopes("all")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = Base64.getEncoder().encodeToString("demoApp:demoAppSecret".getBytes("UTF-8"));
        System.out.println(s);
    }
}
