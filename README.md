# 🛒 Ecommerce_Project
An open-source e-commerce platform developed with React (Vite) for the frontend and Spring Boot for the backend. This project includes a full shopping experience, RESTful integration, and modern development tools.

#### Table of Contents

- [🛒 Ecommerce\_Project](#-ecommerce_project)
      - [Table of Contents](#table-of-contents)
- [🚀 Features](#-features)
- [🧰 Tech Stack](#-tech-stack)
- [🐳 How to Use Docker](#-how-to-use-docker)
- [💻 Initialization Steps](#-initialization-steps)
- [🧪 Testing \& Coverage](#-testing--coverage)
- [🗂️ Project Structure](#️-project-structure)
- [🧑‍💻 Development Notes](#-development-notes)

# 🚀 Features
- 🔐 User Authentication – Secure login and registration
- 🛍️ Product Management – CRUD for products
- 🛒 Shopping Cart – Add, remove, and update item quantities
- 📦 Order System – Order creation and tracking
- 🌐 RESTful APIs – Seamless front-end/back-end communication
- ✅ Testing – Front-end testing with Vitest

# 🧰 Tech Stack
- Frontend: React, Vite, MUI, Axios, React Hook Form, React Toastify, Vitest
- Backend: Java, Spring Boot, REST APIs
- Dev Tools: Docker, Docker Compose, Git, IntelliJ, Node.js


# 🐳 How to Use Docker
To run both backend and frontend services using Docker(Make sure Docker is installed and running on your system):
```
docker-compose up -d
```

# 💻 Initialization Steps
📦 Setup Frontend
1. Clone the repo and navigate into the project:
```
git clone https://github.com/Linsen-gao-457/Ecommerce_Project.git
cd Ecommerce_Project
```
2. Initialize the React frontend:
```
cd client
npm install
```
3. Run locally:
```
npm start
```
# 🧪 Testing & Coverage
Run front-end tests:
```
npm t
```
Run UI tests:
```
npm run test:ui
```
Generate coverage report:
```
npx vitest --coverage
```

# 🗂️ Project Structure
```
Ecommerce_Project/
├── client/                 # Frontend (React + Vite)
├── sportscenter-Backend/  # Backend (Java + Spring Boot)
├── docker-compose.yml     # Docker orchestration
└── README.md
```

# 🧑‍💻 Development Notes
- IDE recommendation: IntelliJ IDEA for Java backend
- Follow component-based architecture on the frontend
- Use RESTful conventions for all API endpoints