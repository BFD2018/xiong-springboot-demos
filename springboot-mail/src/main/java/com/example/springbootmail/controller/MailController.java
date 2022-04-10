package com.example.springbootmail.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Api(tags = "发送邮件")
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @ApiOperation("发送简单邮件")
    @PostMapping("sendSimpleMail")
    public String sendSimpleMail(
            @ApiParam("收件地址") @RequestParam String address,
            @ApiParam("标题") @RequestParam String subject,
            @ApiParam("正文") @RequestParam String text) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(sender);
        smm.setTo(address);
        smm.setSubject(subject);
        smm.setText(text);

        javaMailSender.send(smm);
        return "发送成功";
    }

    @ApiOperation("发送HTML格式的邮件")
    @PostMapping("sendHtmlMail")
    public String sendHtmlMail(
            @ApiParam("收件人") @RequestParam String to,
            @ApiParam("标题") @RequestParam String subject,
            @ApiParam("HTML格式的内容") @RequestParam String content
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(sender);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(mimeMessage);

        return "发送成功";
    }

    @ApiOperation("发送带附件的邮件")
    @PostMapping("sendAttachmentMail")
    public String sendAttachmentMail(
            @ApiParam("收件地址") @RequestParam String address,
            @ApiParam("标题") @RequestParam String subject,
            @ApiParam("正文") @RequestParam String text,
            @ApiParam("附件") @RequestPart MultipartFile attach) throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(sender);
        helper.setTo(address);
        helper.setSubject(subject);
        helper.setText(text);

        System.out.println("attach=======>");
        System.out.println(attach);
        //文件路径
        byte[] bytes = attach.getBytes();
        String name = attach.getOriginalFilename();
        helper.addAttachment(name, new ByteArrayResource(bytes));
        log.info("fileName:{}", name);
        javaMailSender.send(mimeMessage);

        return "发送成功";
    }
}
