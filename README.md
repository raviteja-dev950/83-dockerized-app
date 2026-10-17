# 🐳 Project 83 – Dockerized App + Container API | Full Stack DevOps System | Single Repo

<p align="left">

<img src="https://img.shields.io/badge/Java-21-E76F00?logo=openjdk&logoColor=white" alt="Java 21">

<img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 4.1.1">

<img src="https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker&logoColor=white" alt="Docker Ready">

<img src="https://img.shields.io/badge/Docker%20Compose-Up-2496ED?logo=docker&logoColor=white" alt="Docker Compose">

<img src="https://img.shields.io/badge/Apache%20Tomcat-11.0.24-D4AF00?logo=apachetomcat&logoColor=white" alt="Apache Tomcat 11.0.24">

<img src="https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white" alt="Maven 3.9+">

<img src="https://img.shields.io/badge/Port-9494-20B000" alt="Port 9494">

<img src="https://img.shields.io/badge/Status-Completed-20B000" alt="Completed">

</p>

---

# 📖 Project Overview

**Dockerized App System** is Project 83 of **Tier 9 – DevOps + Cloud Deployment**, developed using **Java 21**, **Spring Boot 4.1.1**, **Docker**, **Docker Compose**, and **Apache Tomcat 11.0.24** in a **single repo**.

Spring Boot REST API running on port 9494 inside Docker container returns live JSON `{"status":"Docker Ready","project":"83-dockerized-app","tier":"9 - DevOps + Cloud"}`. This single repository contains **Dockerfile**, **docker-compose.yml**, **.dockerignore**, and **Spring Boot source** - eliminating separate DevOps setup. One clone gives full dockerized app.

This is the first project of Tier 9 - Base for upcoming K8s, CI/CD, AWS EC2, S3, RDS, and Terraform projects.

**Bug Fixed:** `docker: Error response from daemon: ports are not available: exposing port TCP 0.0.0.0:9494 -> bind: Only one usage of each socket address` - Port 9494 already used by Eclipse. Fixed by `docker rm -f dockerized-app-container` + `netstat -ano | findstr :9494` + closing IDE.

**Bug Fixed:** `go-yaml load error in scanner at L9.C14-L10.C1: found a tab character that violates indentation` - Docker Compose YAML had TAB. Fixed by replacing TAB with SPACES.

---

# ✨ Features

- Docker Ready status API with JSON response - `{"status":"Docker Ready",...}`
- Fully containerized Spring Boot app - Running inside Docker, not IDE
- Dockerfile with `eclipse-temurin:21-jdk` base image - WORKDIR /app
- Docker Compose orchestration - Network Created + Container Started
- Port mapping `0.0.0.0:9494->9494/tcp` with conflict handling
- .dockerignore for faster build - 19.91MB context
- Tomcat initialized with port 9494 (http) inside container
- Started Application in 6.505 seconds (compose) vs 12.414 seconds (first run)
- Spring Boot banner v4.1.1 inside `docker logs`
- Auto-restart policy `unless-stopped`
- Single repo - Dockerfile + compose + source together
- Verified with `docker ps`, `docker logs`, browser `localhost:9494`

---

# 🛠 Technologies Used

- Java 21.0.12.1
- Spring Boot 4.1.1
- Spring Web - REST API
- Docker Desktop - desktop-linux
- Dockerfile - FROM eclipse-temurin:21-jdk
- Docker Compose - services.app.build
- .dockerignore - context optimization
- Maven 3.9+
- Apache Tomcat 11.0.24 (Embedded) - TomcatWebServer
- JavaScript (for JSON response)
- VS Code / STS / Eclipse IDE
- Windows 11 - Version 10.0.26300.9711

---

# 📂 Project Structure - Single Repo

```text
83-dockerized-app
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── ravi
│   │   │           └── devops
│   │   │               └── dockerizedapp
│   │   │                   ├── Application.java
│   │   │                   │
│   │   │                   └── controller
│   │   │                       └── DockerController.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│       └── java
│           └── com
│               └── ravi
│                   └── devops
│                       └── dockerizedapp
│                           └── ApplicationTests.java
│
├── screenshots
│   ├── demo1.png
│   ├── demo2.png
│   ├── demo3.png
│   ├── demo4.png
│   └── demo5.png
│
├── Dockerfile
├── docker-compose.yml
├── .dockerignore
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

# ▶ How to Run - Single Repo

## 1⃣ Clone the Repository

```bash
git clone https://github.com/raviteja-dev950/83-dockerized-app.git
cd 83-dockerized-app
```

---

## 2⃣ Build JAR First (Required for Docker)

- Open **STS / Eclipse IDE** or **VS Code**
- Verify `src/main/resources/application.properties`

```properties
server.port=9494
spring.application.name=83-dockerized-app
```

- Verify `DockerController.java`

```java
package com.ravi.devops.dockerizedapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class DockerController {

    @GetMapping("/")
    public Map<String, String> getStatus() {

        Map<String, String> response = new HashMap<>();

        response.put("status", "Docker Ready");
        response.put("project", "83-dockerized-app");
        response.put("tier", "9 - DevOps + Cloud");

        return response;
    }
}
```

- Verify `Application.java`

```java
package com.ravi.devops.dockerizedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

- Build:

```bash
mvn clean package -DskipTests
```

Check:

```text
target/83-dockerized-app-0.0.1-SNAPSHOT.jar - 19.91MB
```

---

## 3⃣ Docker Build (Port 9494 - 149.3s First Time)

Verify `Dockerfile`:

```dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9494
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Verify `.dockerignore`:

```text
target/*.original
.git
.gitignore
.mvn
mvnw
.settings
```

Run:

```bash
docker build -t 83-dockerized-app .
```

Check logs (like demo2):

```text
[+] Building 149.3s (9/9) FINISHED
=> [internal] load build definition from Dockerfile 0.1s
=> [internal] load metadata for docker.io/library/eclipse-temurin:21-jdk 5.8s
=> [1/3] FROM docker.io/library/eclipse-temurin:21-jdk 123.1s
=> [2/3] WORKDIR /app 0.7s
=> [3/3] COPY target/*.jar app.jar 0.2s
=> exporting to image 18.4s
=> naming to docker.io/library/83-dockerized-app:latest 0.0s
```

---

## 4⃣ Run Container (Fix Port Conflict Like demo3)

```bash
# If port 9494 already in use (Eclipse running)
docker rm -f dockerized-app-container

netstat -ano | findstr :9494

# Now run
docker run -d -p 9494:9494 --name dockerized-app-container 83-dockerized-app
```

Check:

```bash
docker ps
```

Should show like demo3/demo4:

```text
CONTAINER ID   IMAGE               COMMAND              STATUS                  PORTS
4abe5e549cce   83-dockerized-app   "java -jar app.jar"  Up Less than a second   0.0.0.0:9494->9494/tcp   dockerized-app-container
```

Logs:

```bash
docker logs dockerized-app-container
```

Should show like demo5:

```text
:: Spring Boot :: (v4.1.1)
Starting Application v0.0.1-SNAPSHOT using Java 21.0.12.1 with PID 1 (/app/app.jar started by root in /app)
Tomcat initialized with port 9494 (http)
Starting service [Tomcat]
Starting Servlet engine: [Apache Tomcat/11.0.24]
Root WebApplicationContext: initialization completed in 2890 ms
Tomcat started on port 9494 (http) with context path '/'
Started Application in 6.505 seconds (process running for 8.011)
```

Visit:

```text
http://localhost:9494
```

Should return JSON like demo1.

---

## 5⃣ Run with Docker Compose (Production Style Like demo4)

Verify `docker-compose.yml` - Use SPACES not TAB:

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

Run:

```bash
docker-compose up -d
```

Should show like demo4:

```text
[+] up 2/2
Network 83-dockerized-app_default Created 0.1s
Container dockerized-app-container Started 0.6s
```

Check:

```bash
docker ps
docker logs dockerized-app-container
```

Down:

```bash
docker-compose down
```

---

## 6⃣ Application Flow

```text
User (Clone Repo - 83-dockerized-app)
      │
      ▼
Maven - mvn clean package -DskipTests
      │
      ▼
target/83-dockerized-app-0.0.1-SNAPSHOT.jar (19.91MB)
      │
      ▼
Dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/*.jar app.jar
      │
      ▼
docker build -t 83-dockerized-app . (149.3s - demo2)
      │
      ▼
Image: 83-dockerized-app:latest
      │
      ├── docker run -d -p 9494:9494 (demo3)
      │      │
      │      ▼
      │   Container: dockerized-app-container
      │   4abe5e549cce / 6f20e88900e4
      │      │
      │      ▼
      │   Tomcat started on port 9494
      │   Started Application in 6.505s - 12.414s (demo5)
      │
      └── docker-compose up -d (demo4)
             │
             ▼
        Network 83-dockerized-app_default Created
        Container dockerized-app-container Started
             │
             ▼
        Browser: localhost:9494 (demo1)
             │
             ▼
        {
          status: Docker Ready,
          project: 83-dockerized-app,
          tier: 9 - DevOps + Cloud
        }
```

---

# 📸 Screenshots

### Demo 1 - Browser API Response - Docker Ready

Spring Boot API running inside Docker container on `localhost:9494` - Returns JSON with status Docker Ready.

[Demo 1](screenshots/demo1.png)

---

### Demo 2 - Docker Build - 149.3s Success

Docker build with `eclipse-temurin:21-jdk` - 9/9 steps FINISHED - 149.3s first pull + 19.91MB context.

[Demo 2](screenshots/demo2.png)

---

### Demo 3 - Docker Run + Port Conflict Fix + PS

Port conflict `bind: Only one usage...` fixed by `docker rm -f` + `netstat -ano` - Container `4abe5e549cce` Up Less than a second - Spring Boot banner v4.1.1.

[Demo 3](screenshots/demo3.png)

---

### Demo 4 - Docker Compose - TAB Error Fix + Network + Container

Compose error `found a tab character that violates indentation` fixed - Network Created + Container Started - `6f20e88900e4` - `0.0.0.0:9494->9494/tcp`.

[Demo 4](screenshots/demo4.png)

---

### Demo 5 - Docker Logs - Tomcat Started 6.5s

Docker logs `dockerized-app-container` - Tomcat initialized with port 9494 - Root WebApplicationContext initialization completed in 2890 ms - Started Application in 6.505 seconds.

[Demo 5](screenshots/demo5.png)

---

# 🧪 API Testing Examples

```bash
# GET Status - Browser
curl http://localhost:9494

# Docker PS
docker ps

# Docker Logs
docker logs dockerized-app-container

# Docker Compose Up
docker-compose up -d

# Docker Compose Down
docker-compose down
```

### Expected Response (localhost:9494)

```json
{
  "status": "Docker Ready",
  "project": "83-dockerized-app",
  "tier": "9 - DevOps + Cloud"
}
```

### Expected Docker PS Response

```text
CONTAINER ID   IMAGE               COMMAND               CREATED          STATUS                  PORTS                    NAMES
6f20e88900e4   83-dockerized-app   "java -jar app.jar"   1 second ago     Up Less than a second   0.0.0.0:9494->9494/tcp   dockerized-app-container
```

### Expected Docker Logs Response

```text
:: Spring Boot :: (v4.1.1)
2026-10-17T11:33:17.492Z INFO 1 --- [83-dockerized-app] [main] c.r.d.dockerizedapp.Application : Starting Application v0.0.1-SNAPSHOT using Java 21.0.12.1 with PID 1 (/app/app.jar started by root in /app)
2026-10-17T11:33:17.509Z INFO 1 --- [83-dockerized-app] [main] c.r.d.dockerizedapp.Application : No active profile set, falling back to 1 default profile: "default"
2026-10-17T11:33:20.437Z INFO 1 --- [83-dockerized-app] [main] o.s.boot.tomcat.TomcatWebServer : Tomcat initialized with port 9494 (http)
2026-10-17T11:33:22.869Z INFO 1 --- [83-dockerized-app] [main] o.s.boot.tomcat.TomcatWebServer : Tomcat started on port 9494 (http) with context path '/'
2026-10-17T11:33:22.908Z INFO 1 --- [83-dockerized-app] [main] c.r.d.dockerizedapp.Application : Started Application in 6.505 seconds (process running for 8.011)
```

**Testing Steps:**

1. Open `http://localhost:9494` - demo1 - Should return Docker Ready JSON
2. Run `docker build -t 83-dockerized-app .` - demo2 - Should finish 149.3s
3. Run `docker run -d -p 9494:9494 --name dockerized-app-container 83-dockerized-app` - demo3 - Should create container
4. Run `docker ps` - demo3/demo4 - Should show Up Less than a second + 0.0.0.0:9494->9494/tcp
5. Run `docker-compose up -d` - demo4 - Should show Network Created + Container Started
6. Run `docker logs dockerized-app-container` - demo5 - Should show Tomcat started on port 9494

---

# 🎯 Learning Outcomes

- Understanding Docker architecture - Image vs Container vs Dockerfile
- Creating Dockerfile with `eclipse-temurin:21-jdk` base image
- Using WORKDIR, COPY, EXPOSE, ENTRYPOINT instructions
- Building Docker image with `docker build -t` - 149.3s first build
- Understanding build context and `.dockerignore` optimization - 19.91MB
- Running container with `docker run -d -p 9494:9494 --name`
- Handling port conflicts - `ports are not available: bind: Only one usage`
- Using `docker rm -f`, `netstat -ano | findstr :9494`, `taskkill`
- Using `docker ps` to check running containers and port mapping
- Using `docker logs` to view Spring Boot logs inside container
- Understanding Docker Compose - services, build, image, container_name, ports, restart
- Fixing YAML indentation error - `found a tab character that violates indentation` - TAB vs SPACES
- Using `docker-compose up -d` and `docker-compose down`
- Understanding container networking - `Network 83-dockerized-app_default Created`
- Running Spring Boot inside Docker - Tomcat 11.0.24 on 9494
- Comparing startup time - 12.414s vs 6.505s - Layer caching
- Creating production-ready single repo with Docker
- Preparing base for Tier 9 - K8s, CI/CD, AWS

---

# 🚀 Future Enhancements

- ☸ Add K8s deployment - Project 84 - deployment.yaml + service.yaml for this image
- 🔄 Add GitHub Actions CI - Project 85 - Auto build & push to Docker Hub on git push
- ☁️ Deploy to AWS EC2 - Project 86 - Run this container on EC2 instance
- 📦 Add S3 File Service - Project 87 - File upload to S3 inside container
- 🐬 Add RDS Integration - Project 88 - Connect container to AWS RDS MySQL
- 📊 Add CloudWatch Logs - Project 89 - Push docker logs to CloudWatch
- ⚖️ Add Load Balanced App - Project 90 - Nginx + 3 replicas of this container
- 🏗️ Add Terraform Script - Project 91 - IaC for entire infrastructure
- 🔵🟢 Add Blue-Green Deploy - Project 92 - Zero-downtime deployment
- 📉 Optimize Dockerfile - Multi-stage build - Reduce image from 450MB to 150MB
- 🔍 Add Docker Health Check - HEALTHCHECK in Dockerfile
- 📝 Add Docker Compose with MySQL + Redis for full stack

---

# 👨💻 Author

Ravi Teja

Java Full Stack Developer

100 Java Full Stack Projects Challenge

Project 83 / 100

Tier 9 – DevOps + Cloud Deployment

---

## ⭐ Support

If you found this project helpful, consider giving it a ⭐ Star on GitHub.

**Single Repo:** [83-dockerized-app](https://github.com/raviteja-dev950/83-dockerized-app)

**Docker Image:** `83-dockerized-app:latest`

**Container:** `dockerized-app-container` - Port 9494

**Build:** `docker build -t 83-dockerized-app .` - 149.3s

**Run:** `docker-compose up -d`
