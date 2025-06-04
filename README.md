
# JIRA Lite – Simplified Bug Tracking System

JIRA Lite is a lightweight web-based bug tracking application built using Java Servlets and JSP. It provides a simple and intuitive way for teams to report, track, and resolve bugs in software development, complete with user roles (Admin, Developer, Tester) and session-based authentication.

---

## 🚀 Features

✅ User authentication with role-based dashboards (Admin, Developer, Tester)  
✅ Submit, update, view, and delete bug reports  
✅ Admin-only User Management (Add/Edit/Delete Users)  
✅ Filtered dashboards based on user role  
✅ Session tracking and cookie-based "Remember Me" support  
✅ Modular MVC-like architecture with clear separation of concerns  

---

## 🛠️ Tech Stack

- Java (Servlets, JSP)
- MySQL
- Apache Tomcat (Web server)
- JDBC
- HTML, CSS (Basic frontend)
- NetBeans / IntelliJ IDEA (for development)
- Git (for version control)

---

## 📁 Project Structure

JIRALite/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── jiralite/
│       │           ├── servlets/
│       │           │   ├── LoginServlet.java
│       │           │   ├── LogoutServlet.java
│       │           │   ├── DashboardServlet.java
│       │           │   ├── BugSubmissionServlet.java
│       │           │   ├── BugListServlet.java
│       │           │   ├── BugEditServlet.java
│       │           │   ├── BugDeleteServlet.java
│       │           │   ├── AddUserServlet.java
│       │           │   ├── UserListServlet.java
│       │           │   ├── EditUserServlet.java
│       │           │   ├── DeleteUserServlet.java
│       │           │   └── BugUpdateServlet.java
│       │           │
│       │           ├── filters/
│       │           │   └── AuthFilter.java
│       │           │
│       │           ├── listeners/
│       │           │   └── CleanUpListener.java
│       │           │
│       │           ├── models/
│       │           │   ├── User.java
│       │           │   └── Bug.java
│       │           │
│       │           ├── utils/
│       │              └── DBUtil.java
│       │                        
│
├── WebContent/
│   ├── login.jsp
│   ├── tester-dashboard.jsp
│   ├── developer-dashboard.jsp
│   ├── admin-dashboard.jsp
│   ├── bug-list.jsp
│   ├── edit-bug.jsp
│   ├── user-list.jsp
│   ├── add-user.jsp
│   ├── edit-user.jsp
│   ├── error.jsp
|   ├── submit-bug.jsp
|    ├── reportBug.jsp
|   ├── dashboard.jsp
|   ├── index.jsp
│   └── WEB-INF/
│       └── web.xml
│
│
├── lib/                          # External JARs (e.g., MySQL JDBC)
│
├── README.md
├── .gitignore
└── build.xml or pom.xml          # Depending on Ant or Maven


---

## 🔐 Authentication & Authorization

### Login

- POST `/login`
- Params: `username`, `password`, `remember` (optional)
- Redirects user to their respective dashboard based on `role` from DB

### Role-Based Access

- `AuthFilter.java` ensures:
  - Only authenticated users can access protected routes
  - Only Admins can access User Management pages

---

## 🐞 Bug Management

- **Add Bug:** `/add-bug` – for Testers  
- **View Bugs:** `/bug-list` – All roles  
- **Edit/Delete Bugs:** `/edit-bug`, `/delete-bug` – for Developers  
- **Bug Info Fields:** title, description, status (Open, In Progress, Closed), assignedTo, createdBy, createdAt

---

## 👤 User Management (Admin Only)

- **List Users:** `/user-list`
- **Add User:** `/add-user`
- **Edit User:** `/edit-user?id=x`
- **Delete User:** `/delete-user?id=x`

---

## 🧪 Testing Highlights

- ✅ Valid/invalid login scenarios
- ✅ Session & role-based redirection
- ✅ Bug creation/editing/deletion workflows
- ✅ Admin-only access enforcement via `AuthFilter`
- ✅ Invalid routes/session timeout handling

---

## 🗄️ Database Schema (MySQL)

### `users` Table

| Field     | Type          |
|-----------|---------------|
| id        | INT PRIMARY KEY AUTO_INCREMENT |
| username  | VARCHAR(255)  |
| password  | VARCHAR(255)  |
| role      | ENUM('Admin', 'Developer', 'Tester') |

### `bugs` Table

| Field       | Type          |
|-------------|---------------|
| id          | INT PRIMARY KEY AUTO_INCREMENT |
| title       | VARCHAR(255)  |
| description | TEXT          |
| status      | ENUM('Open', 'In Progress', 'Closed') |
| assignedTo  | VARCHAR(255)  |
| createdBy   | VARCHAR(255)  |
| createdAt   | TIMESTAMP DEFAULT CURRENT_TIMESTAMP |

---

## 📦 Future Enhancements

- Add project-level grouping (Project Management module)
- Use password hashing (e.g., BCrypt)
- Add pagination & search in lists
- REST API version for frontend/backend separation
- Integration with email alerts or notification system

---

## 🙌 Author

**Sahil Saifi**  
Application Support Engineer → Aspiring Java Backend Developer

---

## 📜 License

This project is for educational and demonstration purposes. Feel free to use, modify, and enhance!
