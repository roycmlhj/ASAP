package com.ssafy.api.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.EmailConfirm;
import com.ssafy.db.repository.EmailConfirmRepository;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	@Autowired
	JavaMailSender emailSender;
	@Autowired
	EmailConfirmRepository emailConfirmRepository;
	
	private MimeMessage createMessage(String to, String key) throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ key);
        MimeMessage  message = emailSender.createMimeMessage();
 
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("ASAP 회원가입 이메일 인증");//제목
 
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 ASAP입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다!<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= key+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("kwwoo516@gmail.com","ASAP"));//보내는 사람
 
        return message;
    }
	
	public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

	@Override
	@Transactional
	public String sendSimpleMessage(String to) throws Exception {
		String key = createKey();
		MimeMessage message = createMessage(to, key);
		//예외처리
        try{
            emailSender.send(message);
            EmailConfirm emailConfirm = emailConfirmRepository.findbyEmail(to).orElse(null);
            if(emailConfirm == null) {
            	emailConfirm = new EmailConfirm();
            	emailConfirm.setEmail(to);
            }
        	emailConfirm.setCode(key);
        	emailConfirmRepository.save(emailConfirm);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return key;
	}

	@Override
	public EmailConfirm getByEmail(String email) {
		return emailConfirmRepository.findbyEmail(email).orElse(null);
	}
}
