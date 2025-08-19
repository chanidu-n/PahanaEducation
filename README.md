# PahanaEducation

A Java web application for managing customers and items using JSP, Servlets, and MySQL database.

## Features

- **User Authentication**: Secure login system with session management
- **Customer Management**: Add and view customer information including account details and electricity consumption
- **Item Management**: Add and view items with pricing information
- **Dashboard**: User-friendly interface for navigation and system overview
- **Database Integration**: MySQL database connectivity for data persistence

## Tech Stack

- **Backend**: Java Servlets, JSP
- **Database**: MySQL
- **Build Tool**: Maven
- **Server**: Java Web Application (WAR deployment)
- **IDE**: IntelliJ IDEA

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── controller/          # Servlet controllers
│   │   │   ├── LoginServlet.java
│   │   │   ├── CustomerServlet.java
│   │   │   └── ItemServlet.java
│   │   ├── dao/                 # Data Access Objects
│   │   │   ├── UserDAO.java
│   │   │   ├── CustomerDAO.java
│   │   │   └── ItemDAO.java
│   │   ├── model/               # Entity classes
│   │   │   ├── User.java
│   │   │   ├── Customer.java
│   │   │   └── Item.java
│   │   └── util/                # Utility classes
│   │       └── DBUtil.java
│   └── webapp/
│       ├── forms/               # JSP pages
│       │   ├── login.jsp
│       │   ├── dashboard.jsp
│       │   ├── addCustomer.jsp
│       │   ├── viewCustomer.jsp
│       │   ├── addItem.jsp
│       │   ├── viewItems.jsp
│       │   └── help.jsp
│       ├── WEB-INF/
│       │   └── web.xml          # Servlet configuration
│       └── index.jsp            # Landing page
```

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- Apache Tomcat 9.0+ or similar servlet container

## Database Setup

1. **Create MySQL Database:**
   ```sql
   CREATE DATABASE pahana_edu;
   USE pahana_edu;
   ```

2. **Create Tables:**
   ```sql
   -- Users table
   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(100) NOT NULL
   );

   -- Customers table
   CREATE TABLE customers (
       account_number INT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       address VARCHAR(200),
       telephone VARCHAR(20),
       units_consumed INT DEFAULT 0
   );

   -- Items table
   CREATE TABLE items (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       price DECIMAL(10,2) NOT NULL
   );
   ```

3. **Insert Sample Data:**
   ```sql
   -- Sample user
   INSERT INTO users (username, password) VALUES ('admin', 'admin123');
   ```

## Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/chanidu-n/PahanaEducation.git
   cd PahanaEducation
   ```

2. **Configure Database Connection:**
   
   Update the database credentials in [`src/main/java/util/DBUtil.java`](src/main/java/util/DBUtil.java):
   ```java
   private static String jdbcURL = "jdbc:mysql://localhost:3306/pahana_edu?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
   private static String jdbcUsername = "your_username";
   private static String jdbcPassword = "your_password";
   ```

3. **Build the project:**
   ```bash
   mvn clean compile
   ```

4. **Package the application:**
   ```bash
   mvn package
   ```

5. **Deploy to Tomcat:**
   - Copy the generated `target/PahanaEducation.war` file to your Tomcat `webapps` directory
   - Start Tomcat server
   - Access the application at `http://localhost:8080/PahanaEducation`

## Usage

### Login
1. Navigate to `http://localhost:8080/PahanaEducation`
2. Use the credentials: `admin` / `admin123` (or create your own in the database)

### Customer Management
- **Add Customer**: Navigate to "Add Customer" from the dashboard
- **View Customers**: Access the customer list to see all registered customers

### Item Management
- **Add Item**: Add new items with name and price
- **View Items**: Browse all available items

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/login` | POST | User authentication |
| `/forms/addCustomer` | POST | Add new customer |
| `/forms/addItem` | POST | Add new item |

## Dependencies

- **javax.servlet-api** (3.1.0): Servlet API
- **mysql-connector-java** (8.0.33): MySQL JDBC driver
- **jstl** (1.2): JSP Standard Tag Library
- **junit** (3.8.1): Testing framework

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

**Developer**: Chanidu Neerada  
**Project**: PahanaEducation  
**Repository**: [https://github.com/chanidu-n/PahanaEducation](https://github.com/chanidu-n/PahanaEducation)

## Troubleshooting

### Common Issues

1. **Database Connection Issues**:
   - Verify MySQL service is running
   - Check database credentials in [`DBUtil.java`](src/main/java/util/DBUtil.java)
   - Ensure database and tables are created

2. **Servlet Not Found**:
   - Check [`web.xml`](src/main/webapp/WEB-INF/web.xml) servlet mappings
   - Verify servlet classes are compiled

3. **Form Submission Issues**:
   - Ensure form action URLs match servlet mappings
   - Check HTTP method (GET/POST) compatibility

### Development Tips

- Use IDE debugger to trace servlet execution
- Check Tomcat logs for detailed error messages
- Verify database connections in [`DBUtil.getConnection()`](src/main/java/util/DBUtil.java)
