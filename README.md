Admissions Committee System with Monitoring Stack

This project is a full-stack implementation of the "Admissions Committee" system, designed to automate the registration of applicants. 

Beyond the core business logic, the project focuses heavily on DevOps infrastructure and Monitoring. I have containerized the entire application and integrated a real-time monitoring stack using Prometheus and Grafana to track system performance and application metrics.

Technology Stack

Frontend: React JavaScript

Backend: Java 17

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
<img width="1918" height="965" alt="image" src="https://github.com/user-attachments/assets/376cf7c7-2761-4e61-a217-625862d3ba4d" />


5. Faculties page
<img width="1919" height="976" alt="image" src="https://github.com/user-attachments/assets/204c1e0d-0c61-42a7-9ad6-ceddb4c8c0ab" />


6. Results page
<img width="1916" height="969" alt="image" src="https://github.com/user-attachments/assets/85a927bf-7b8b-43c2-a9cb-8e70e5d255f3" />

7. Login/register page
<img width="1919" height="873" alt="image" src="https://github.com/user-attachments/assets/e7e2661a-2cc7-4d9a-ae6c-2d161ef60266" />

8. Apply for admission 
<img width="1919" height="969" alt="image" src="https://github.com/user-attachments/assets/22afb245-4b66-4d99-b9d2-44c080e45ab6" />

9. Application received
<img width="1919" height="972" alt="image" src="https://github.com/user-attachments/assets/fc9f7a44-13ff-4d03-aa23-479dc9f356fd" />

10. Results list with new user Serhii Bryl
<img width="1426" height="853" alt="image" src="https://github.com/user-attachments/assets/b7092c12-b05b-4304-ae45-be18cb4119ba" />
