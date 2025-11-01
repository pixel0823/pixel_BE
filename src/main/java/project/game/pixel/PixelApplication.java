package project.game.pixel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PixelApplication {

	public static void main(String[] args) {

        System.setProperty("java.net.preferIpv4Stack", "true");
        SpringApplication.run(PixelApplication.class, args);
	}

}
