package org.sid.sec;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends  WebSecurityConfigurerAdapter {
	   @Autowired
	   // pour utiliser le meme  base de donner de lapplication puis on l'injecter
	    private DataSource dataSource;
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 PasswordEncoder passwordEncoder1 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        // travailler avec les utilisateur qui sont stocker dans le memoire
		 //auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN","USER");
		 //auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
		 auth.jdbcAuthentication().dataSource(dataSource)
		 .usersByUsernameQuery("select username as principal , password as credentials , active from users where username=?")
// pour Connaitre le role de chaque user
		 .authoritiesByUsernameQuery("select username as principal , role as role from users_role where username=?")
         .rolePrefix("ROLE_")
      .passwordEncoder(NoOpPasswordEncoder.getInstance());
        // .passwordEncoder(new Md5PasswordEncoder());
       //.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());


	 }
	 
	 
	 @Override

	 protected void configure(HttpSecurity http) throws Exception {
		 // definir les strategies de securiter
		 // loginPage pour personnaliser le  formulaire de login
		 http.formLogin().loginPage("/login");
		 // securiser les ressource  de l'application
		 http.authorizeRequests().antMatchers("/operations","/consulterCompte").hasRole("USER");
		 http.authorizeRequests().antMatchers("/operations","/consulterCompte","/saveOperation").hasRole("ADMIN");
		 http.exceptionHandling().accessDeniedPage("/403");


	    }
}
