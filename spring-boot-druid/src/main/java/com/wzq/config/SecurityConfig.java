package com.wzq.config;

import com.wzq.util.MyPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security 配置
 * 参考https://docs.spring.io/spring-security/site/docs/current/guides/html5//helloworld-boot.html#creating-your-spring-security-configuration
 * @author
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
	 * 原因：spring security 5.X版本需要提供一个PasswordEncorder的实例
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//可以设置内存指定的登录的账号密码,指定角色
		//不加.passwordEncoder(new MyPasswordEncoder())
		//就不是以明文的方式进行匹配，会报错
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		//.passwordEncoder(new MyPasswordEncoder())。
		//这样，页面提交时候，密码以明文的方式进行匹配。
		auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("root").password("root").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//				//表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//				.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()
//				.and()
//				.authorizeRequests().anyRequest().authenticated()
//				.and()
//				.csrf().disable();

		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic()
				.and()
				.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//设置静态资源不要拦截
		web.ignoring().antMatchers("/js/**","/cs/**","/images/**");
	}
}