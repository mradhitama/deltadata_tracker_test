# Task Tracker Application

This is a simple Task Management application built using Spring Boot. The application provides basic CRUD operations for managing tasks.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Usage](#usage)

## Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK) 20 or higher
- Maven
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

## Getting Started

1. Clone the repository:

   ```bash
       git clone https://github.com/mradhitama/deltadata_tracker_test.git

2. Navigate to the project directory:

    ```bash
       cd deltadata_tracker_test/tracker

2. Build the project:

    ```bash
       mvn clean install

3. Run the application:

    ```bash
       java -jar target/tracker-1.0.0.jar

The application will be accessible at http://localhost:8080.

## Endpoints

* GET /tasks: Get a list of all tasks.
* GET /tasks/{id}: Get details of a specific task.
* POST /tasks: Create a new task.
* PUT /tasks/{id}: Update an existing task.
* PUT /duedate/{id}: Update dueDate from existing task.
* DELETE /tasks/{id}: Delete a task.


## Usage

The application also can be accessible using Swagger at http://localhost:8080/swagger-ui/index.html#/.

### Get All Tasks

- **URL:** `/tasks`
- **Method:** `GET`
- **Description:** Get a list of all tasks.

### Get Task by ID

- **URL:** `/tasks/{id}`
- **Method:** `GET`
- **Description:** Get details of a specific task by its ID.

### Create New Task

- **URL:** `/tasks`
- **Method:** `POST`
- **Description:** Create a new task.
- **Request Body:**
  ```json
  {
    "title": "Task Title",
    "description": "Task Description"
  }

### Update Task

- **URL:** `/tasks/{id}`
- **Method:** `PUT`
- **Description:** Update task.
- **Request Body:**
  ```json
  {
    "title": "Task Title",
    "description": "Task Description",
    "completed": true,
    "dueDate": "dd/MM/yyyy"
  }

### Update Due Date

- **URL:** `/duedate/{id}`
- **Method:** `PUT`
- **Description:** Update dueDate.
- **Request Body:**
  ```json
  {
    "dueDate": "dd/MM/yyyy"
  }

### Delete Task

- **URL:** `/task/{id}`
- **Method:** `DELETE`
- **Description:** Delete task.

