package com.bottle.team.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//import org.springframework.boot.bind.RelaxedPropertyResolver;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {
    private String from;
    private String host;
    private int port;
    private String username;
    private String password;
    private Properties properties;
    private String debug;

    @Bean
    public JavaMailSender mailSender() {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setPort(port);
        sender.setHost(host);

        sender.setJavaMailProperties(loadProperties());
        return sender;
    }

    private java.util.Properties loadProperties() {
        java.util.Properties properties = new java.util.Properties();
        System.out.println(this.properties);
        properties.setProperty("mail.transport.protocol", this.properties.transport.protocol);
        properties.setProperty("mail.smtp.auth", this.properties.mail.smtp.auth);
        properties.setProperty("mail.smtp.starttls.enable", this.properties.mail.smtp.starttls.enable);
        properties.setProperty("mail.debug", debug);
        return properties;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public static class Properties {
        private Mail mail;
        private Transport transport;

        public Transport getTransport() {
            return transport;
        }

        public void setTransport(Transport transport) {
            this.transport = transport;
        }

        public Mail getMail() {
            return mail;
        }

        public void setMail(Mail mail) {
            this.mail = mail;
        }

        static class Transport {
            String protocol;

            public String getProtocol() {
                return protocol;
            }

            public void setProtocol(String protocol) {
                this.protocol = protocol;
            }
        }

        public static class Mail {
            private SMTP smtp;

            public SMTP getSmtp() {
                return smtp;
            }

            public void setSmtp(SMTP smtp) {
                this.smtp = smtp;
            }

            public static class SMTP {
                private String auth;
                private Starttls starttls;

                public String getAuth() {
                    return auth;
                }

                public void setAuth(String auth) {
                    this.auth = auth;
                }

                public Starttls getStarttls() {
                    return starttls;
                }

                public void setStarttls(Starttls starttls) {
                    this.starttls = starttls;
                }

                public static class Starttls {
                    private String enable;

                    public String getEnable() {
                        return enable;
                    }

                    public void setEnable(String enable) {
                        this.enable = enable;
                    }
                }
            }
        }
    }
}
