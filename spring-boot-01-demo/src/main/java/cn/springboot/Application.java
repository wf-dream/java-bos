package cn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 主体类（用于启动springboot）
 * @author Administrator
 *
 */

//@SpringBootApplication默认根据入口类的所在位置扫描包以及入口类所在位置以及子包范围
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//启动springboot
		SpringApplication.run(Application.class, args);
	}
}
