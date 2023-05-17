package store.dropthebeatbox.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/*
	ContextInstanceDataAutoConfiguration은 스프링 클라우드 AWS가 AWS EC2 인스턴스의 메타데이터를 자동으로 조회하려는 설정을 담당합니다.
	이 설정이 활성화되면, 애플리케이션은 AWS EC2 환경에서 실행되고 있을 것으로 가정하고, AWS EC2 인스턴스 메타데이터를 조회하려고 시도합니다.
	하지만 당신의 애플리케이션은 EC2 환경에서 실행되지 않는 것 같으므로, 이 설정을 제외함으로써 이러한 메타데이터 조회 시도를 방지할 수 있습니다.
 */
@SpringBootApplication(exclude = { ContextInstanceDataAutoConfiguration.class })
@EnableJpaAuditing
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
