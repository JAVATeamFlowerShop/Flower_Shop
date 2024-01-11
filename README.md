# Shop Inventory Management Program

The following program was developed as part of the Java programming bootcamp at ITAcademy Barcelona.
<br><br>It serves as a stock manager for a flower shop, maintaining data persistance using both JSON files (Level 1) and a MySQL database (Level 2).
<br><br>It enables the creation and management of trees, flowers, decorations, and purchase records within a flower shop's inventory.

## Table Of Contents
1. [Requirements](#req)
2. [Functionality](#func)
3. [Usage](#use)
    1. [Data Persistance](#persistance)
       - [JSON Serialization](#json)
       - [Database Configuration](#mysql)
4. [Contributors](#members)
5. [Notes](#notes)

## 1. Requirements <a name = "req"></a>
- Java Development Kit (JDK) 8 or higher
- JSON serialization file support for Level 1 data persistance
- MySQL database and JDBC Driver for Level 2 data persistance

## 2. Functionality <a name = "func"></a>
 - Create Flower Shop: initialize the flower shop with a name
 - Add Tree: add tree in the inventory with specified height and price
 - Add Flower: add flower to the inventory with a defined color and price
 - Add Decoration: add decoration specifying the material (wood or plastic) and price
 - Remove Product (tree/flower/decoration): remove specific item from inventory
 - Print flower shop stock (with or without quantities): display current inventory
 - Create purchase tickets: generate purchase ticket registering items bought in a single list
 - Show old purchases tickets: display list of previous purchases
 - Total Value of Stock: display total value of flower shop's inventory
 - Total Money Earned: calculate and display total money earned from all sales
   
## 3. Usage <a name = "use"></a>
Clone the repository to your local machine: 
```java Cloning repo
git clone https://github.com/JAVATeamFlowerShop/Flower_Shop.git
```
Before executing the program, read **3.i. Data persistance**.
<br><br>To execute the program, use a Java IDE (developers team used IntelliJ IDE). Can also be done from the command line as you would with any maven project. Upon running the program, follow the on-screen prompts to execute the program's various functionalitites.

### 3.i. Data persistance <a name = "persistance"></a>
#### JSON Serialization <a name = "json"></a>
For level 1 data persistance, ensure the file paths in the **'LoadData.java'** file are according to your system setup. This class handles data JSON serialization and deserialization.

#### Database Configuration <a name = "mysql"></a>
For level 2 data persistance:
1. Ensure your MySQL server is running
2. You can either use the database in this repository or you can create your own.
> The SQL scripts needed to use the given database are located in 'src/main/resources'.
> <br>There you can find the scripts to create the database and populate both products and tickets.
3. Update the **'database.properties'** file with your MySQL connection details.
<br><br>Example:
```
CONNECTION_URL=jdbc:mysql://localhost:3306/flowershop
USER=user
PASSWORD=password
```

## 4. Contributors <a name = "members"></a>
This project is a collaborative efort by three team members:
- Member 1: [@SandyMrtss](https://github.com/SandyMrtss)
- Member 2: [@MarcSantasusana](https://github.com/MarcSantasusana)
- Member 3: [@mayacamps](https://github.com/mayacamps)

## 5. Notes <a name = "notes"></a>
Some potential areas for future enhancements that have been considered are:
- Maintaining data persistance using MongoDB
- Managing more than one flower shop

Contributions are welcome from anyone if you have any ideas for improvements!
