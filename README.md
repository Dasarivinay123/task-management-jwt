# Task Management System (JWT + CI/CD)

## 🚀 Overview
Secure backend application built using Spring Boot with JWT Authentication and deployed using Docker, Jenkins, and AWS EC2.

## 🛠 Tech Stack
- Java, Spring Boot
- JWT Authentication (Filters)
- MySQL (Docker)
- Docker
- Jenkins (CI/CD)
- AWS EC2

## 🔐 Features
- User Registration & Login
- JWT Authentication & Authorization
- Secure REST APIs
- Task Management (User-specific)
- CI/CD Pipeline Deployment

## ⚙️ API Endpoints
- POST /api/auth/register
- Image: https://github.com/user-attachments/assets/cc863929-862d-46a6-a138-a8f491efaccd

- POST /api/auth/login
- Image: https://github.com/user-attachments/assets/ec3a706a-db37-42f1-a60d-869181f88df7

- POST /api/{userId}/tasks
- Image: https://github.com/user-attachments/assets/f5754d6b-5f76-404d-8d9a-41db77fb0f53

- GET /api/{userId}/tasks
-Image: DB: https://github.com/user-attachments/assets/711b097f-1a00-4e56-a359-e6d883f597b0

## 🐳 Deployment
- Dockerized application & MySQL
- Jenkins pipeline for build & deployment
- Hosted on AWS EC2

## 🌐 Live API
http://3.95.238.122:8080

