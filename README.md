# incident-management
Project Overview
This application provides a RESTful API for managing incidents. Users can:
Create incidents
Retrieve a list of all incidents (with pagination)
Update incidents
Delete incidents
All data is stored in-memory using the H2 database. The application includes:
Clear API endpoints
Performance optimizations
Thorough unit tests
Exception handling with meaningful error messages
Caching mechanisms
Docker support for containerization


Prerequisites
Java 17 installed
SpringBoot 2.7.5 configured
React installed
Install dependencies for spring and react
npm install axios
mvn clean install
System Design
API Design (REST)

ID
API 
Request URL
Description
RequestBody
ResponseBody
1
createIncident
POST “/api/incidents”
Create incident 
Incident data
Ex.
{
Title: “new title”,
Description: “test desc”,
Level : 1,
Status: 1,
SubmittedBy: 123,
AssignedTo: 234,
}
Incident Entity
2
getAllIncidents
GET “/api/incidents”
Get incident list with pagination
Page and offset
{
Page: 1,
Offset: 20,
}
Page of incident list
3
updateIncident
PUT “/api/incidents/id”
Update incident data
Incident data
Ex.
{
Id: 1
Title: “new title”,
Description: “test desc”,
Level : 1,
Status: 1,
SubmittedBy: 123,
AssignedTo: 234,
}
Incident Entity
4
deleteIncident
DELETE “/api/incidents/id”
Delete incident by id
Incident Id
Response Code/Msg


Data Schema Design
Incident Entity Design

ID
Column Name
Data Type
Example Data
Notes
1
Id
Long


Auto Increment
2
Title
String


Incident Title
3
Description
String


Incident Description
4
Level
Enum
1: P0
2: P1
3: P2
4: P3
5: P4
Incident Level
From high to low
5
Status
Enum
1: Created
2: Assigned
3: Resolved
4: Backlogged
5: Obsoleted
Incident Status
6
SubmittedBy
Long
UserID
Submitted by who
7
AssignedTo
Long
UserID
Assigned to who
8
CreatedAt
Timestamp




9
UpdatedAt
Timestamp






Code Design
Java
incident-management/
├── src/
│   ├── main/
│   │   ├── java/com/xiaogang/incidentmanagement/
│   │   │   ├── IncidentManagementApplication.java
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
│       └── java/com/xiaogang/incidentmanagement/
├── pom.xml
└── Dockerfile


Front-end
incident-mgr-frontend/
├── src/
│   ├── App.js
│   ├── components/
│   │   ├── IncidentList.js
│   │   └── IncidentForm.js
│   ├── services/
│   │   └── api.js
│   ├── index.js
└── package.json

Rollout
Running the Entire Application
Backend (Spring Boot Application)
Build the Project
mvn clean install
Run the Application
mvn spring-boot:run
Frontend (React Application)
Install Dependencies
npm install
Start the Frontend
npm start
Access the Application
Frontend: http://localhost:3000
Backend API: http://localhost:8080/api/incidents

