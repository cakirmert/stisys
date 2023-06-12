# STISYS - Student Information System

## Description
STISYS is a web-based Student Information System that provides comprehensive functionality for managing student data, courses, grades, and user roles within HAW Hamburg.

## Features
- User authentication and role-based access control
- Course registration and management
- Grade tracking and management
- Professor-specific features for setting PVL and Grades
- Student-specific features for viewing grades

## Prerequisites
- Java Development Kit (JDK) version 8 or above
- MySQL database server
- Maven build tool

## Installation
1. Clone the repository: `git clone https://github.com/cakirmert/stisys.git`
2. Configure the database connection by using the schema in the database folder.
3. Build the project using Maven: `mvn clean install`
4. Start the application: `java -jar stisys.jar`
5. Access the application in your web browser at `http://localhost:8080/api/login`

## Database Setup
To set up the database for this application, you can use the provided MySQL database schema SQL script. The script contains the necessary SQL statements to create the database schema.

Note: The SQL script does not include any data, only the schema structure. You can add data to the database using the application's user interface.

1. Locate the SQL script file (`your-database-schema.sql`) in the project repository.
2. Open a MySQL administration tool such as MySQL Workbench.
3. Connect to your MySQL database server.
4. Create a new empty database or choose an existing database to use.
5. Open the SQL editor in the administration tool.
6. Open the SQL script file in a text editor and copy the contents.
7. Paste the SQL script contents into the SQL editor.
8. Execute the script to create the database schema.
9. Verify that the schema has been successfully created in the chosen database.

By following these steps, you will have the database schema set up and ready to use for the application.

## Usage
- Login to the application using valid credentials.
- Depending on your role (FSB, Professor, or Student), you will have access to different features and pages.
- Follow the intuitive user interface to perform actions such as adding courses, setting grades, or viewing grades.

## Contributing
Contributions are welcome! If you'd like to contribute to STISYS, please follow these guidelines:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with descriptive messages.
4. Push your changes to your forked repository.
5. Open a pull request to the main repository.

## License
STISYS is licensed under the [MIT License](LICENSE).

## Contact
For any questions or support, please contact the development team at:
- Issue Tracker: [https://github.com/cakirmert/stisys/issues](https://github.com/cakirmert/stisys/issues)
