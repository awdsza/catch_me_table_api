package com.app.catchmetable.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
/** Jpa Auditing 활성화 어노테이션
 * 기본적으론 Application파일에 들어간다.
 * 컨트롤러 테스트(@WebMvcTest)시, JPA는 데이터계층이기 떄문에 어노테이션 탐색되지않아 오류가 발생하여 Config파일을 따로 만들어서 분리. (JPA metamodel ust not be empty)
 * */
@EnableJpaAuditing
public class JpaAuditingConfig {}
