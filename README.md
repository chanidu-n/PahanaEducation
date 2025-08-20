# PahanaEducation - Bookshop Management System

A comprehensive Java web application for managing a bookshop's operations including customer management, inventory control, and bill generation using JSP, Servlets, and MySQL database.

## Features

- **User Authentication**: Secure login system with session management
- **Customer Management**: Add, view, edit, and delete customer information
- **Item/Inventory Management**: Complete CRUD operations for bookshop items
- **Bill Generation**: Create detailed bills with multiple items and automatic calculations
- **Bill Management**: View all bills and print individual receipts
- **Dashboard**: User-friendly interface for navigation and system overview
- **Database Integration**: MySQL database connectivity for data persistence
- **Responsive Design**: Clean CSS styling for better user experience

## Tech Stack

- **Backend**: Java Servlets, JSP
- **Database**: MySQL 8.0+
- **Build Tool**: Maven
- **Testing**: JUnit 3.8.1
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
│   │   │   ├── ItemServlet.java
│   │   │   └── BillServlet.java
│   │   ├── dao/                 # Data Access Objects
│   │   │   ├── UserDAO.java
│   │   │   ├── CustomerDAO.java
│   │   │   ├── ItemDAO.java
│   │   │   └── BillDAO.java
│   │   ├── model/               # Entity classes
│   │   │   ├── User.java
│   │   │   ├── Customer.java
│   │   │   ├── Item.java
│   │   │   ├── Bill.java
│   │   │   └── BillItem.java
│   │   └── util/                # Utility classes
│   │       └── DBUtil.java
│   └── webapp/
│       ├── forms/               # JSP pages
│       │   ├── dashboard.jsp
│       │   ├── addCustomer.jsp
│       │   ├── viewCustomer.jsp
│       │   ├── editCustomer.jsp
│       │   ├── addItem.jsp
│       │   ├── viewItems.jsp
│       │   ├── editItem.jsp
│       │   ├── createBill.jsp
│       │   ├── billList.jsp
│       │   ├── viewBill.jsp
│       │   └── help.jsp
│       ├── css/
│       │   └── style.css        # Application styling
│       ├── WEB-INF/
│       │   └── web.xml          # Servlet configuration
│       └── index.jsp            # Landing page
└── test/
    └── java/
        ├── dao/                 # DAO unit tests
        └── model/               # Model unit tests
```

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+
- Apache Tomcat 9.0+ or similar servlet container


## Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd PahanaEducation
   ```

2. **Configure Database Connection:**
   
   Update the database credentials in [`src/main/java/util/DBUtil.java`](src/main/java/util/DBUtil.java):
   ```java
   private static String jdbcURL = "jdbc:mysql://localhost:3306/pahana_edu?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
   private static String jdbcUsername = "root";
   private static String jdbcPassword = "root";
   ```

3. **Build the project:**
   ```bash
   mvn clean compile
   ```

4. **Run tests:**
   ```bash
   mvn test
   ```

5. **Package the application:**
   ```bash
   mvn package
   ```

6. **Deploy to Tomcat:**
   - Copy the generated `target/PahanaEducation.war` file to your Tomcat `webapps` directory
   - Start Tomcat server
   - Access the application at `http://localhost:8080/PahanaEducation`

## Usage

### Login
1. Navigate to `http://localhost:8080/PahanaEducation`
2. Use the credentials: `Admin` / `1234`

### Customer Management
- **Add Customer**: Navigate to "Add Customer" from the dashboard
- **View Customers**: Access the customer list to see all registered customers
- **Edit Customer**: Click "Edit" next to any customer in the customer list
- **Delete Customer**: Click "Delete" to remove a customer (with confirmation)

### Item Management
- **Add Item**: Add new items with name and price
- **View Items**: Browse all available items
- **Edit Item**: Modify existing item details
- **Delete Item**: Remove items from inventory

### Bill Management
- **Create Bill**: 
  1. Select "Create Bill" from dashboard
  2. Enter customer account number to load customer details
  3. Select items and quantities
  4. System automatically calculates subtotals and total
  5. Submit to generate the bill
- **View Bills**: Access list of all generated bills
- **Print Bills**: View and print individual bill receipts

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/login` | POST | User authentication |
| `/forms/addCustomer` | POST | Add/Update/Delete customer |
| `/forms/editCustomer` | GET | Get customer for editing |
| `/forms/addItem` | POST | Add/Update/Delete item |
| `/forms/editItem` | GET | Get item for editing |
| `/forms/bill` | POST | Create bill, Get customer details |
| `/forms/bill` | GET | View bill, List all bills |

## Testing

The project includes comprehensive unit tests:

- **Model Tests**: [`UserTest`](src/test/java/model/UserTest.java), [`ItemTest`](src/test/java/model/ItemTest.java), [`CustomerTest`](src/test/java/model/CustomerTest.java)
- **DAO Tests**: [`UserDAOTest`](src/test/java/dao/UserDAOTest.java), [`ItemDAOTest`](src/test/java/dao/ItemDAOTest.java), [`CustomerDAOTest`](src/test/java/dao/CustomerDAOTest.java)

Run tests with:
```bash
mvn test
```

## Dependencies

- **javax.servlet-api** (3.1.0): Servlet API
- **mysql-connector-java** (8.0.33): MySQL JDBC driver
- **jstl** (1.2): JSP Standard Tag Library
- **junit** (3.8.1): Testing framework

## Features in Detail

### Customer Management
- Account number-based customer identification
- Complete customer information storage
- Units consumed tracking for utility billing

### Inventory Management
- Item CRUD operations
- Price management
- Stock tracking capabilities

### Billing System
- Multi-item bill generation
- Automatic calculation of subtotals and totals
- Customer information integration
- Printable bill format
- Bill history and tracking

### Security
- Session-based authentication
- Protected routes requiring login
- Session timeout configuration

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

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

4. **CSS Not Loading**:
   - Verify CSS file path in JSP pages
   - Check Tomcat static resource serving

### Development Tips

- Use IDE debugger to trace servlet execution
- Check Tomcat logs for detailed error messages
- Verify database connections using [`DBUtil.getConnection()`](src/main/java/util/DBUtil.java)
- Test individual DAOs using the provided unit tests

## License

This project is licensed under the MIT License.

## Contact

**Developer**: Chanidu Neerada  
**Project**: PahanaEducation Bookshop Management System
