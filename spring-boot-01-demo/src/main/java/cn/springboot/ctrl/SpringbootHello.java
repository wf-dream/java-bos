package cn.springboot.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//该组合注解相当于@Controller和@ResponseBody
@RestController
public class SpringbootHello {

	@RequestMapping("sayhello")
	public String sayhello() {
		return "helloword";
	}
}
