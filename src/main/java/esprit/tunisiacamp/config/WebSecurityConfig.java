package esprit.tunisiacamp.config;


import esprit.tunisiacamp.services.CustomOAuth2UserService;
import esprit.tunisiacamp.services.UserIService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig  {
/*
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/rest/login/oauth/**","/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("pass")
                .defaultSuccessUrl("/rest/swagger-ui/index.html#")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("AuthenticationSuccessHandler invoked");
                        System.out.println("Authentication name: " + authentication.getName());
                       // User u = new User();
                        //u.setProdiver(Provider.GOOGLE);
                        //u.setUsername(authentication.getName());
                        //userService.addUser(u);
                        //System.out.println("Authentication name: " + authentication.getCredentials());

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        userIService.processOAuthPostLogin(oauthUser.getEmail());
                        response.sendRedirect("/rest/swagger-ui/index.html#");
                    }
                })
                .and()
                //.logout().logoutUrl("/rest/logout")
                //.logoutSuccessUrl("/rest/login")
                //.invalidateHttpSession(true)
                //.deleteCookies("JSESSIONID")
                //.permitAll();
                .logout().logoutSuccessUrl("/logout").permitAll()

        ;
                //.and()
                //.exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    UserIService userIService;
*/
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.cors().disable();
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/deleteOrDisableUser/**","/register","/pay","/authenticate","/login/oauth/**","/login/**","/rest/swagger-ui/index.html#/","/process_register","/verify","/affecterUserToRole/**","/resetpwd/**","/verifiePwd/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //.formLogin().permitAll()
                //.loginPage("/login")
                //.usernameParameter("email")
                //.passwordParameter("pass")
                //.defaultSuccessUrl("/login")
                //.and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("AuthenticationSuccessHandler invoked");
                        System.out.println("Authentication name: " + authentication.getName());
                        // User u = new User();
                        //u.setProdiver(Provider.GOOGLE);
                        //u.setUsername(authentication.getName());
                        //userService.addUser(u);
                        //System.out.println("Authentication name: " + authentication.getCredentials());

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        try {
                            userIService.processOAuthPostLogin(oauthUser.getEmail());
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                        //response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
                        response.sendRedirect("/rest/swagger-ui/index.html#");
                    }
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/rest/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }
    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    UserIService userIService;
/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
            }
        };
    }
*/

}
