# Vehicle Rental and Sales System  
**Spring 2025 – CS151 Project 1**

## 📌 Overview
This application simulates a vehicle rental and sales business with two distinct user perspectives:  
- **Customer**: Can browse, rent, or purchase vehicles from a store's inventory.  
- **Branch Owner (Business Perspective)**: Can manage employees and vehicle inventory.

The system is built using Object-Oriented Programming principles in Java, with a modular structure to support extensibility and maintainability.

---

## 🛠️ System Design

The system is structured into three core components:

### 1. **Controller Layer (Main System)**
Handles:
- User input and interaction flow.
- Coordination between different subsystems.
- Navigation between user roles (customer vs. business).

### 2. **User & Order Layer**
Classes:
- `Customer`, `Employee`, `Order`  
Responsibilities:
- Represent user interactions with the system.
- Track rentals, purchases, and orders.
- Maintain data integrity and relationships between users and vehicles.

### 3. **Model Layer (Business Entities)**
Classes:
- `Store`, `Inventory`, and vehicle subclasses (`Car`, `Truck`, etc.)
Responsibilities:
- Represent real-world entities.
- Provide core data structures for storage and manipulation.
- Act as the foundation for business logic.

---

## ⚠️ Note on Persistence

The system currently operates **in-memory only**. All data is preloaded at runtime, and no file I/O is implemented. All data will be lost once the application is closed.

---

## 🧰 Installation

1. Ensure you have **Java JDK 17 or higher** installed.  
2. Compile all `.java` files in the project folder:
   ```bash
   javac *.java
   ```
3. Run the main class:
   ```bash
   java Main
   ```

---

## ▶️ Usage Instructions

### Customer Perspective
1. Select **Customer Mode** at startup.
2. Choose a store location.
3. Search the inventory for available vehicles.
4. Choose to **rent** (for a number of days) or **purchase** a vehicle.
5. After each action, you will return to the Customer Main Menu.

### Business Perspective
1. Select **Business Mode** at startup.
2. Choose a store location.
3. Access options to:
   - Manage the **vehicle inventory** (add/remove/update vehicles).
   - Manage **employees** (view/add/remove staff).
4. After each action, you will return to the Business Main Menu.

---

## 🧱 Object-Oriented Features

- **Encapsulation**: All class fields are private with public getters/setters.
- **Inheritance**: A base `Vehicle` class with subclasses like `Car`, `Truck`, etc.
- **Polymorphism**: Common methods overridden in vehicle subclasses.
- **Modularity**: Clear separation of concerns across packages and classes.


