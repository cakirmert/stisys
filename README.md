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
1. Clone the repository: `git clone https://github.com/yourusername/stisys.git`
2. Configure the database connection by using the schema in the database folder.
3. Build the project using Maven: `mvn clean install`
4. Start the application: `java -jar stisys.jar`
5. Access the application in your web browser at `http://localhost:8080/api/login`

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
