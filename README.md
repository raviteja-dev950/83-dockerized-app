# 83 - Dockerized App | Tier 9 - DevOps + Cloud Deployment

[Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
[Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?style=for-the-badge&logo=springboot)
[Docker](https://img.shields.io/badge/Docker-Ready-blue?style=for-the-badge&logo=docker)
[Docker Compose](https://img.shields.io/badge/Docker%20Compose-Up-blue?style=for-the-badge)
[Build](https://img.shields.io/badge/Build-Passing-success?style=for-the-badge)
[Tier](https://img.shields.io/badge/Tier-9%20DevOps%20%2B%20Cloud-red?style=for-the-badge)
[Project](https://img.shields.io/badge/Project-83%2F100-yellow?style=for-the-badge)

## 1. Project Overview

**Project 83** is the first project of **Tier 9 - DevOps + Cloud** in the **100 Java Full Stack Projects Challenge**. This project transforms a Spring Boot REST API into a fully dockerized application, demonstrating containerization fundamentals required for production deployment.

The application exposes a simple REST endpoint that returns Docker deployment status, proving the application is running inside a Docker container, not in local IDE.

> **Live Endpoint:** `http://localhost:9494/`
> **Response:** `{"status":"Docker Ready","project":"83-dockerized-app","tier":"9 - DevOps + Cloud"}`

This project is the base for upcoming Tier 9 projects: K8s Deployment, GitHub Actions CI, AWS EC2 Deploy, S3, RDS, and Terraform.

## 2. Features

- ✅ Spring Boot REST API with `/` endpoint
- ✅ Fully containerized with Docker
- ✅ Multi-container orchestration with Docker Compose
- ✅ Port mapping `9494:9494` with conflict handling
- ✅ Optimized `.dockerignore` for faster builds
- ✅ Tomcat started inside container on port 9494
- ✅ Production-ready Dockerfile using `eclipse-temurin:21-jdk`
- ✅ Auto-restart policy `unless-stopped`
- ✅ Verified with `docker logs`, `docker ps`, and browser testing

## 3. Technologies Used

| Category | Technology |
| :--- | :--- |
| **Backend** | Java 21, Spring Boot 4.1.1, Spring Web, Tomcat 11.0.24 |
| **Build Tool** | Maven 3.9+ |
| **DevOps** | Docker Desktop, Dockerfile, Docker Compose, .dockerignore |
| **Tools** | IntelliJ / VS Code, Postman / Browser, Git & GitHub |
| **OS** | Windows 11 (Docker Desktop - WSL2) |

## 4. Project Structure

```
83-dockerized-app/
├── src/main/java/com/ravi/devops/dockerizedapp/
│   └── Application.java (Main + Controller)
├── src/main/resources/
│   └── application.properties (server.port=9494)
├── target/
│   └── 83-dockerized-app-0.0.1-SNAPSHOT.jar
├── Dockerfile
├── docker-compose.yml
├── .dockerignore
├── pom.xml
└── README.md
```

**Dockerfile:**
```dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9494
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**docker-compose.yml:**
```yaml
services:
  app:
    build: .
    image: 83-dockerized-app
    container_name: dockerized-app-container
    ports:
      - "9494:9494"
    restart: unless-stopped
```

**.dockerignore:**
```
target/*.original
.git
.gitignore
.mvn
mvnw
.settings
```

## 5. How to Run

### Prerequisites
- Java 21, Maven, Docker Desktop Running

### Method 1: With Docker (Recommended for Tier 9)
```bash
# 1. Build jar
mvn clean package -DskipTests

# 2. Build image (first time takes ~149s due to JDK pull)
docker build -t 83-dockerized-app .

# 3. Run container
docker run -d -p 9494:9494 --name dockerized-app-container 83-dockerized-app

# 4. Check
docker ps
docker logs dockerized-app-container
```

### Method 2: With Docker Compose (Production Style)
```bash
docker-compose up -d
docker ps
docker logs dockerized-app-container
docker-compose down
```

### Method 3: Without Docker (For comparison)
```bash
mvn spring-boot:run
# or
java -jar target/*.jar
```

**Fix Port Conflict (Important Learning):**
If you get `ports are not available: 0.0.0.0:9494 bind...`:
```bash
docker rm -f dockerized-app-container
netstat -ano | findstr :9494
# close IDE running on 9494
docker run -d -p 9494:9494 --name dockerized-app-container 83-dockerized-app
```

## 6. Application Flow

```
Developer -> mvn package -> JAR (19.91MB)
    |
    -> Dockerfile -> docker build -> Image (eclipse-temurin:21 + app.jar)
    |
    -> docker-compose.yml -> docker-compose up -d
    |
    -> Container: dockerized-app-container
    |
    -> Tomcat starts inside container on 9494 (6.5s - 12s)
    |
    -> Browser: localhost:9494 -> JSON Response -> Docker Ready
```

## 7. Screenshots (Demo Proofs)

Place your 5 screenshots in a `/screenshots` folder in repo and link:

| Demo | Description | File |
| :--- | :--- | :--- |
| Demo 1 | Browser Proof - Docker Ready JSON | `screenshots/demo1-browser.png` |
| Demo 2 | Docker Build - 149.3s Success | `screenshots/demo2-build.png` |
| Demo 3 | Port Conflict Fix + Run + PS + Logs | `screenshots/demo3-run.png` |
| Demo 4 | Spring Boot Logs + Compose TAB Error Fix | `screenshots/demo4-compose-fix.png` |
| Demo 5 | Final Compose Logs - 6.5s Started | `screenshots/demo5-final-logs.png` |

**Your current verified output:**
[Demo1](./screenshots/demo1.png)

> Add all 5 images you shared into `/screenshots` folder for GitHub.

## 8. API Testing Responses

**Endpoint:** `GET http://localhost:9494/`

**Response 200 OK:**
```json
{
  "status": "Docker Ready",
  "project": "83-dockerized-app",
  "tier": "9 - DevOps + Cloud"
}
```

**Docker Commands Response:**
```bash
CONTAINER ID   IMAGE               STATUS                  PORTS
6f20e88900e4   83-dockerized-app   Up Less than a second   0.0.0.0:9494->9494/tcp   dockerized-app-container

Logs: Tomcat started on port 9494 (http) with context path '/'
      Started Application in 6.505 seconds
```

**Postman / Browser:** Tested and Working ✅

## 9. Learning Outcomes

From this project I learned:

1.  **Docker Fundamentals:** Difference between Image vs Container vs Dockerfile
2.  **Port Binding:** How `0.0.0.0:9494->9494/tcp` works and handling port conflicts
3.  **Build Context:** Role of `.dockerignore` in reducing build time (19.91MB context)
4.  **Compose vs Run:** Why `docker-compose up -d` is production standard over `docker run`
5.  **YAML Indentation:** TAB character violates indentation — must use SPACES only
6.  **Logging in Docker:** `docker logs <container>` shows Spring Boot banner inside container
7.  **DevOps Mindset:** App should run same in IDE and in Docker — environment parity
8.  **Tier 9 Foundation:** This image is base for K8s, AWS EC2, CI/CD in next projects

## 10. Future Enhancements

This project will evolve in Tier 9:

- **84. K8s Deploy:** Create `deployment.yaml` + `service.yaml` for this image
- **85. GitHub Actions CI:** Auto build & push to Docker Hub on git push
- **86. AWS EC2 Deploy:** Deploy this container to EC2
- **87. S3 File Service:** Add file upload to S3 inside same container
- **88. RDS Integration:** Connect container to AWS RDS MySQL
- **89. CloudWatch Logs:** Push Docker logs to CloudWatch
- **90. Load Balanced App:** Nginx + 3 replicas of this container
- **91. Terraform Script:** IaC for entire infrastructure
- **92. Blue-Green Deploy:** Zero-downtime deployment

- Optimization: Multi-stage Dockerfile to reduce image size from ~450MB to ~150MB

## 11. 👨💻 Author

**Ravi Teja**
Java Full Stack Developer
100 Java Full Stack Projects Challenge
Project 83 / 100
Tier 9 – DevOps + Cloud Deployment

## 12. Support

If you found this project helpful, consider giving it a ⭐ Star on GitHub.
