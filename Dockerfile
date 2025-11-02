# 1단계: 빌드 (Builder)
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Maven 래퍼와 pom.xml 파일을 먼저 복사합니다.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# mvnw 파일에 실행 권한을 부여합니다.
RUN chmod +x ./mvnw

# 의존성을 다운로드합니다.
RUN ./mvnw dependency:go-offline

# 소스 코드를 복사합니다.
COPY src ./src

# Maven을 사용하여 프로젝트를 빌드(패키징)합니다. (테스트는 스킵)
RUN ./mvnw package -DskipTests

# 2단계: 실행 (Runner)
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# 1단계(builder)에서 빌드한 JAR 파일을 복사합니다.
COPY --from=builder /app/target/pixel-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]