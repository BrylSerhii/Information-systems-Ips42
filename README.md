Admissions Committee System with Monitoring Stack

This project is a full-stack implementation of the "Admissions Committee" system, designed to automate the registration of applicants. 

Beyond the core business logic, the project focuses heavily on DevOps infrastructure and Monitoring. I have containerized the entire application and integrated a real-time monitoring stack using Prometheus and Grafana to track system performance and application metrics.

Technology Stack

Language:Java 17
FrameworkSpring Boot 3 (Web, Data JPA, Actuator)
Database: PostgreSQL 15
Monitoring: Prometheus (Time Series DB)
Visualization: Grafana
Containerization: Docker & Docker Compose

Implementation of Technical Requirements

Below is a summary of how I met the technical task requirements, including architectural decisions I made during development.


DBMS Launch✅ Done  I launched PostgreSQL in a Docker container (`postgres_db`) connected via a custom bridge network.
Data Generation ✅ Done  Instead of using an external Python script, I implemented a robust Java `DataSeeder` service**. It utilizes Spring's `@Scheduled` annotation to automatically generate and insert new student applicants every 5 seconds, simulating live traffic.
Data Collection✅ Done I configured Spring Boot Actuator and Micrometer to expose application metrics. Prometheus scrapes these metrics every 5 seconds via the `prometheus.yml` configuration. |
Time Series Storage✅ Done |Data is stored persistently within the Prometheus internal Time Series Database (TSDB). |
Visualization ✅ Done  I set up Grafana to visualize the data. The dashboard includes real-time graphs for "Student Registration Rate" (Business Metric) and JVM Health (Technical Metric). |
Authorization ✅ Done  For the monitoring system, I utilized Grafana's built-in Authentication (Admin/Admin) to ensure access is restricted to authorized users only. |
Containerization ✅ Done  The entire stack (Java App, Postgres, Prometheus, Grafana) is orchestrated using a single `docker-compose.yml` file. 

How to Run the Project

You need Docker and Java 17 (Maven) installed.

1.  Build the Application
    ```bash
    mvn clean package -DskipTests
    ```

2.  Start the Infrastructure
    ```bash
    docker compose up --build
    ```

3.  Access the Services
   Java API (Data): [http://localhost:8080/applicants](http://localhost:8080/applicants)
   Prometheus: [http://localhost:9090](http://localhost:9090)
   Grafana Dashboards: [http://localhost:3000](http://localhost:3000) *(Login: admin/admin)*

Screenshots

1. Grafana Dashboard (Live Monitoring)
Shows the rate of new students registering per minute.
<img width="1918" height="1020" alt="image" src="https://github.com/user-attachments/assets/d03ea9ef-4073-499f-ab30-2daa5b6c9a77" />

2. List of applicants that were created
<img width="1917" height="1035" alt="image" src="https://github.com/user-attachments/assets/e5c83d9a-180b-4221-85dd-61aced3b67c6" />

3. List of applicants that were creating in terminal IntelijIDEA
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/035bb748-caf7-4e8c-8846-bff9782e8631" />

