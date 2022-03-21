package com.tindev.tindevapi.service;

import com.tindev.tindevapi.dto.match.MatchDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private MatchService matchService;

    private final freemarker.template.Configuration fmConfiguration;

    private static final String MAIL_TO = "tindev.dbc@gmail.com";
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;

    public void sendEmail(String email, String assunto, String mensagem) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(assunto);
            mimeMessageHelper.setText(mensagem, true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailPessoa(MatchDTO matchDTO)  {
        sendEmail(matchDTO.getMachedUserEmailFirst(), "Tindev - Match", getCreateMatchTemplateFirst(matchDTO));
        sendEmail(matchDTO.getMatchedUserEmailSecond(), "Tindev - Match", getCreateMatchTemplateSecond(matchDTO));
    }




    public String getTemplate(String username, String usernameMatched, String templateHTML) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("user", username);
        dados.put("usernameMatched", usernameMatched);
        dados.put("from", from);
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        Template template = fmConfiguration.getTemplate(templateHTML);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public String getCreateMatchTemplateFirst(MatchDTO matchDTO) {
        try {
            return getTemplate(matchDTO.getMatchedUserNameFirst(), matchDTO.getMatchedUserNameSecond(), "email-template.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCreateMatchTemplateSecond(MatchDTO matchDTO) {
        try {
            return getTemplate(matchDTO.getMatchedUserNameSecond(), matchDTO.getMatchedUserNameFirst(), "email-template.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
