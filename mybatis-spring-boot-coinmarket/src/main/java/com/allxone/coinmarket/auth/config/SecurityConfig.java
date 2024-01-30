package com.allxone.coinmarket.auth.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.allxone.coinmarket.auth.UserDetail.CustomUserDetailService;
import com.allxone.coinmarket.auth.UserDetail.UserFactory;
import com.allxone.coinmarket.auth.jwt.JWTAuthenticationFilter;
import com.allxone.coinmarket.mapper.RolesMapper;
import com.allxone.coinmarket.mapper.UserRoleMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final CustomUserDetailService customUserDetailsService;

	private final RolesMapper rolesMapper;

	private final UserRoleMapper userRoleMapper;

	@Bean
	public JWTAuthenticationFilter jwtAuthentitationFilter() {
		return new JWTAuthenticationFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/**").permitAll();
		http.addFilterBefore(jwtAuthentitationFilter(), UsernamePasswordAuthenticationFilter.class);


		UserFactory jwtUserFactory = UserFactory.getInstance();
		jwtUserFactory.setRoleMapper(rolesMapper);
		jwtUserFactory.setUserRoleMapper(userRoleMapper);
		return http.build();
	}
}
