package co.ivis.webssr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "co.ivis")
@MapperScan("co.ivis.infrastructure.repository.mybatis.generated.mapper")
public class WebSsrApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebSsrApplication.class, args);
  }
}
