package com.crm.config;

import com.crm.security.DatasourceAuthenticationProvider;
import com.crm.security.ProcessAuthSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home**").access("hasAnyRole('ADMIN','CLIENT','WAREHOUSE','AGENT')")
                /******** Warehouses **********/
                .antMatchers("/warehouses**").access("hasAnyRole('ADMIN')")
                .antMatchers("/warehouseDetails**").access("hasAnyRole('ADMIN')")
                .antMatchers("/createWarehouse**").access("hasAnyRole('ADMIN')")
                /******** Agents **********/
                .antMatchers("/agents**").access("hasAnyRole('ADMIN')")
                .antMatchers("/createAgent**").access("hasAnyRole('ADMIN')")
                .antMatchers("/agentDetails**").access("hasAnyRole('ADMIN')")
                /******** Clients **********/
                .antMatchers("/clients**").access("hasAnyRole('ADMIN','AGENT')")
                .antMatchers("/createClient**").access("hasAnyRole('ADMIN','AGENT')")
                .antMatchers("/clientDetails**").access("hasAnyRole('ADMIN','AGENT')")
                /*********Products ***********/
                .antMatchers("/products**").access("hasAnyRole('ADMIN')")
                .antMatchers("/createProduct**").access("hasAnyRole('ADMIN')")
                .antMatchers("/productDetails**").access("hasAnyRole('ADMIN')")

                .and().formLogin().loginPage("/login").failureUrl("/login?loginError=true")
                .successHandler(authenticationSuccessHandler())
                .and().exceptionHandling().accessDeniedPage("/denied")
                .and().logout().logoutUrl("/logout").and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.authenticationProvider(datasourceAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider datasourceAuthenticationProvider() {
        return new DatasourceAuthenticationProvider();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new ProcessAuthSuccess();
    }
}
