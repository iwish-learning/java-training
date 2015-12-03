package com.augmentum.test.wx;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;

@RestController
@SpringBootApplication
@RequestMapping("/wx")
public class ConnectWxController {
	
	private static final String ACCESS_TOKEN = "iwish";
	
	@RequestMapping(value = "/wxConnect", method = RequestMethod.GET)
	public void DealWithWxConnect(String signature, String timestamp, String nonce, String echostr,  HttpServletResponse response) throws AesException, IOException {
		String sha = SHA1.getPublicWxSHA1(ACCESS_TOKEN, timestamp, nonce);
		PrintWriter out = response.getWriter();
		if (signature.equals(sha)) {
			out.print(echostr);
		} else {
			throw new AesException(AesException.ValidateSignatureError);
		}
		System.out.println("deal with wx");
	}
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("this is test case");
		return "oh no";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConnectWxController.class, args);
	}
}
