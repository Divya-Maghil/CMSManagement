package org.contentmanagement.digimenucms;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
public class DigiMenuCmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DigiMenuCmsApplication.class, args);
	}

}
