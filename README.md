<h1 align="center">
  Department of Applied Informatics -  Mobile App Development
</h1>
<p align="center">
  <img src="frontend/app/src/main/res/drawable/rcl_logo_header.png" alt="rcl_logo_header">
</p>
  
<h1 align="center">
  Recycling Reward App
</h1>


### Authors:
- [Thanos Moschou](https://github.com/thanosmoschou)
- [Andreas Hadjiantonis](https://github.com/AndreasHadjiantonis)

---
# Recycling Reward App

## Table of Contents
- [Introduction](#introduction)
  - [Purpose](#purpose)
  - [General Functions](#general-functions)
- [General Description](#general-description)
  - [Application Perspective](#application-perspective)
  - [Constraints](#constraints)
  - [Assumptions](#assumptions)
- [Data Design](#data-design)
  - [Databases](#databases)
  - [Files](#files)
- [Module Design](#module-design)
  - [Class Presentation](#class-presentation)
    - [Frontend](#frontend)
    - [Backend](#backend)
- [Links](#links)

## Introduction

### Purpose
The purpose of this document is to clearly define the functionality of the application developed as part of this course project. The aim is to implement one of the European Union's priorities: sustainability and resource saving. The app allows users to dispose of recyclable items and receive rewards through a point system.

### General Functions
The functions of the application are as follows:

#### Simple User
- R1: Mechanism for login/registration.
- R2: User profile with statistics/progress bar towards the next reward.
- R3: Form for recording the type of materials disposed of in the bin.

#### Administrator User
- R1: Approval of records and automatic allocation of points for rewards.
- R2: Viewing top statistics.
- R(2++): Form for configuring rewards based on material/amount recycled.

<h1>
 Screenshots
</h1>

## User
<p>
  <img src=screenshots/User/Register.png width="200" height="400">
  <img src=screenshots/User/Login.png width="200" height="400"> 
  <img src=screenshots/User/yourRewards.png width="200" height="400">
  <img src=screenshots/User/recycle.png width="200" height="400">  
</p>

## Admin
<p>
  <img src=screenshots/Admin/adminOpenRequestsList.png width="200" height="400"> 
  <img src=screenshots/Admin/adminOpenRequestsListOpen.png width="200" height="400">
  <img src=screenshots/Admin/top3Recyclers.png width="200" height="400">  
</p>


## General Description

### Application Perspective
The developed system is not standalone but interacts with other software systems. In addition to the under-development product described in section 1.2, it includes:

- The mobile application: Each user has the application downloaded on their mobile device, allowing them to perform the aforementioned actions to recycle products.
- The server (backend) between the application and the database implemented with Spring Boot for API calls.
- The database with some predefined values for demonstration purposes.

### Constraints
- The system requires Java 17 or higher and Android Studio Flamingo | 2022.2.1 or higher.
- The system requires Android Gradle Version 8.0 and Minimum SDK Version 24 (API 24: Android 7.0 Nougat).
- The system requires a system like XAMPP for operating an Apache Server and a database.
- The network interface of the application to the server is done locally using the iPv4 address. The file where it can be specified for each computer is located in the Frontend with the name Strings.xml and iPv4 property name.

### Assumptions
- Each user is identified via a username and password.
- For simplicity, no security policies were used regarding user identification.
- The application administrator is predefined and no more than one can be created without programming intervention.

## Data Design

### Databases
MySQL is used to store the application data in 4 tables: `users`, `recycle_items`, `recycle_requests`, and `request_list_items`. The server interacts with the database via Jakarta Persistence API (JPA), which uses class objects for database communication. Each class object corresponds to a row in the respective relational table, and each object property corresponds to a column in the relational table.

### Files
The files used and created are as follows:

- **Backend Folder**: Contains the implemented code in Spring Boot, `schema.sql` and `data.sql` files for database schema description and initialization, an `initialScriptForDB.sql` file for database creation in XAMPP, and an `application.properties` file that provides database connection details.
- **Frontend Folder**: Contains the implemented code in Java for Android Studio along with all necessary files, images, fonts, etc.

## Module Design

### Class Presentation

#### Frontend
Classes are divided into 4 packages: `Activities`, `Adapters`, `HttpRequests`, and `Model`.

- **Folder - Activities**
  - `LoginActivity`: Initial screen at application launch responsible for entering user details or transitioning to the Registration screen if the user is new.
  - `RegistrationActivity`: Registration screen responsible for entering user details.
  - `AdminOpenRequestsActivity`: Screen where the administrator sees open requests and approves or rejects them accordingly.
  - `RecycleActivity`: Screen responsible for selecting recyclable materials, their quantity, and sending them.
  - `StatisticsActivity`: Screen showing the top 3 users with the highest recorded points.
  - `UpdateRecycleItemPointsActivity`: Screen where the administrator can modify the reward points for each recyclable item.
  - `YourRewardsActivity`: Screen responsible for displaying user points and a progress bar showing the user's progress towards the next level.

- **Folder - HttpRequests**
  - Contains all classes handling data from each activity and making GET or POST API calls to the server as needed.

- **Folder - Model**
  - `OpenRequestDetails`
  - `RecycleItem`
  - `RecycleListItem` <br/>
     These classes are designed based on specific design patterns to intercommunicate between other classes, allowing the exchange of data and the storage of information for the different objects.

- **Folder - Adapters**
  - `OpenRequestBodyListAdapter`
  - `OpenRequestExpandableListViewAdapter`
  - `RecycleItemsAdapter`
  - `RequestListItemAdapter`<br/>
    These classes are designed to bridge the gap between a Display/Activity and its class and relates to elements of the UI that displays that data.
    

#### Backend
Classes are divided into 4 packages: `Controllers`, `Model`, `Repositories`, and `Services`.

- **Folder - Controllers**
  - `RequestsController`: Responsible for handling all requests to the server, calling the corresponding service for each request.

- **Folder - Model**
  - `LoginRequest`: Login details (username, password) are converted to an object of this class for checking if the user exists in the database.
  - `RecycleItem`: Represents recyclable items and corresponds to the `recycle_items` table.
  - `RecycleRequest`: User's request for reward points is converted to an object of this class for database storage, corresponding to the `recycle_requests` table.
  - `RecycleRequestDTO`: Data Transfer Object class for `RecycleRequest`, used for displaying data in the frontend.
  - `RecycleRequestListItem`: Represents items included in the user's request, corresponding to the `request_list_items` table.
  - `RecycleRequestListItemDTO`: Data Transfer Object class for `RecycleRequestListItem`.
  - `User`: Corresponds to the `users` table.
  - `UserDTO`: Data Transfer Object class for `User`.

- **Folder - Repositories**
  - `RecycleItemsRepository`: Used by JPA for storing `RecycleItems` objects.
  - `RecycleRequestListItemsRepository`: Repository for `RecycleRequestListItem` objects.
  - `RecycleRequestsRepository`: Repository for `RecycleRequest` objects.
  - `UsersRepository`: Repository for `User` objects.
  - Note: These are interfaces extending `JpaRepository`, with methods implemented automatically by JPA.

- **Folder - Services**
  - `RecycleItemsService`: Contains a `RecycleItemsRepository` object to communicate with the database, providing methods accessible by `RequestsController`.
  - `RecycleRequestListItemsService`: Contains a `RecycleRequestListItemsRepository` object, with methods called by `RequestsController`.
  - `RecycleRequestsService`: Service for handling `RecycleRequest` objects.
  - `UserService`: Service for handling `User` objects.

Note: There is also a `BackendRclApplication` class that serves as the server's entry point, as well as a `DatabaseInitializer` class which is responsible for initializing the database by executing `schema.sql` and `data.sql`.

## Links
- **Github**: [Recycling Reward App Repository](https://github.com/CodeBrosLab/RCL-App)
- **YouTube**: [RCL-App Demo](https://youtu.be/KlCXNI6E5c4)
