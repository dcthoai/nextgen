package com.dct.nextgen;

import com.dct.nextgen.config.CRLFLogConverter;
import com.dct.nextgen.config.properties.Datasource;
import com.dct.nextgen.config.properties.DatasourceProperties;
import com.dct.nextgen.config.properties.Hikari;
import com.dct.nextgen.config.properties.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties({ Datasource.class, Hikari.class, DatasourceProperties.class, Security.class })
public class NextgenApplication {

	private static final Logger log = LoggerFactory.getLogger(NextgenApplication.class);

    /**
	 * Main method, used to run the application
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(NextgenApplication.class);
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String applicationName = env.getProperty("spring.application.name");
		String[] activeProfile = env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles();
		String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
		String hostAddress = "localhost";
		String serverPort = env.getProperty("server.port");
		String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path"))
				.filter(StringUtils::hasText)
				.orElse("/");

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}

		log.info(
			CRLFLogConverter.CRLF_SAFE_MARKER,
			"""
			\n----------------------------------------------------------
			\tApplication '{}' is running! Access URLs:
			\tLocal: \t\t{}://localhost:{}{}
			\tExternal: \t{}://{}:{}{}
			\tProfile(s): {}
			\n----------------------------------------------------------
			""",
			applicationName,
			protocol,
			serverPort,
			contextPath,
			protocol,
			hostAddress,
			serverPort,
			contextPath,
			activeProfile
		);

		log.info("[DCT_BASE_APPLICATION] started");
	}
}
