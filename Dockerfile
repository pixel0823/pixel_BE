# 1단계: 빌드 (Builder)
# Java 17 JDK 이미지를 사용하여 프로젝트를 빌드합니다.
FROM eclipse-temurin:17-jdk AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Maven 래퍼와 pom.xml 파일을 먼저 복사합니다. (의존성 캐싱)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 의존성을 다운로드합니다.
RUN ./mvnw dependency:go-offline

# 소스 코드를 복사합니다.
COPY src ./src

# Maven을 사용하여 프로젝트를 빌드(패키징)합니다. (테스트는 스킵)
# 'pixel-0.0.1-SNAPSHOT.jar' 파일이 생성됩니다.
RUN ./mvnw package -DskipTests

# 2단계: 실행 (Runner)
# 더 가벼운 JRE(실행 환경) 이미지를 사용합니다.
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# 1단계(builder)에서 빌드한 JAR 파일을 복사합니다.
# 'pixel-0.0.1-SNAPSHOT.jar'을 'app.jar'라는 이름으로 복사합니다.
COPY --from=builder /app/target/pixel-0.0.1-SNAPSHOT.jar app.jar

# Spring Boot 애플리케이션은 기본적으로 8080 포트를 사용합니다.
EXPOSE 8080

# 컨테이너가 시작될 때 이 명령을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]