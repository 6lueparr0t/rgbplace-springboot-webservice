package com.rgbplace.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("Application started ... launching browser now");
        //browse("http://localhost:8080");
    }

    public static void browse(String url) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                // Windows
                //runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                // Mac OSX
                Process p = Runtime.getRuntime().exec(new String[]{"bash","-c","/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome --kiosk " + url});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
