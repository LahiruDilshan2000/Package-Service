package lk.ijse.packageservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lahiru Dilshan
 * @created Sat 11:01 AM on 10/7/2023
 * @project nexttravel
 **/
@Configuration
public class WebAppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
