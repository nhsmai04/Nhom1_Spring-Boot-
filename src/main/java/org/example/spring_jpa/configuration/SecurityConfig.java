    package org.example.spring_jpa.configuration;

    import org.example.spring_jpa.service.UserService;
    import org.example.spring_jpa.service.impl.UserServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    import static org.springframework.security.config.Customizer.withDefaults;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Autowired
        private UserServiceImpl userService;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            // Cấu hình các quyền truy cập vào các endpoint
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorized -> authorized
                                    .requestMatchers("/api/users/register").permitAll()
                            .requestMatchers("/users/**").hasAnyRole("ADMIN","USER")
                            .requestMatchers("/publishers/**").hasAuthority("publishers:read")
                            .anyRequest().authenticated() // yêu cầu xác thực cho các yêu cầu còn lại
                    )
                    .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) ;// Cung cấp form login mặc định
                     // Cung cấp HTTP Basic authentication

            return http.build(); // Đảm bảo bạn gọi build() trong cuối cấu hình
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return userService;
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(userService);
            provider.setPasswordEncoder(passwordEncoder());
            return provider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }


