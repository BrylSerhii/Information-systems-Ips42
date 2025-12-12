Admissions Committee System with Monitoring Stack

This project is a full-stack implementation of the "Admissions Committee" system, designed to automate the registration of applicants. 

Beyond the core business logic, the project focuses heavily on DevOps infrastructure and Monitoring. I have containerized the entire application and integrated a real-time monitoring stack using Prometheus and Grafana to track system performance and application metrics.

Technology Stack

Frontend: React JavaScript
Language:Java 17
FrameworkSpring Boot 3 (Web, Data JPA, Actuator)
Database: PostgreSQL 15
Monitoring: Prometheus (Time Series DB)
Visualization: Grafana
Containerization: Docker & Docker Compose


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

4. Home page
<img width="1910" height="868" alt="image" src="https://github.com/user-attachments/assets/28bf8469-6373-4cba-b4df-79a349d205f3" />

5. Faculties page
<img width="1916" height="973" alt="image" src="https://github.com/user-attachments/assets/e870764e-bed4-4ee3-8752-d8951a1b3091" />

6. Results page
<img width="1910" height="975" alt="image" src="https://github.com/user-attachments/assets/79850f10-0311-4ccd-8463-80cc6affc751" />


