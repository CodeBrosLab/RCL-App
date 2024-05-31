<h1 align="center">
  Recycling Reward App
  
</h1>

<h1 align="center">
  Department of Applied Informatics
  
</h1>

<h2 align="center">
  Mobile App Development
  
</h2>




### Authors:
- [Thanos Moschou](https://github.com/thanosmoschou)
- [Andreas Hadjiantonis](https://github.com/AndreasHadjiantonis)

---

### Developed using Android Studio Flamingo | 2022.2.1

---

## Table of Contents
1. [Introduction](#introduction)
   1. [Purpose](#purpose)
   2. [General Functions](#general-functions)
2. [General Description](#general-description)
   1. [Application Perspective](#application-perspective)
   2. [Constraints](#constraints)
   3. [Assumptions](#assumptions)
3. [Data Design](#data-design)
   1. [Databases](#databases)
   2. [Files](#files)

---

## Introduction

### Purpose
The purpose of this document is to clearly define the functionality of the application developed as part of this course project. The goal is to implement one of the European Union's priorities, which is sustainability and resource conservation. The app allows users to dispose of recyclable items and receive rewards through a point system.

### General Functions
The functions of the application are as follows:

#### Regular User
- **R1:** Mechanism for login/registration
- **R2:** User profile with statistics/progress bar towards the next reward
- **R3:** Form for recording types of materials discarded in the bin

#### Admin User
- **R1:** Approval of records and automatic awarding of points for rewards
- **R2:** Viewing top statistics
- **R2++:** Form for configuring rewards based on material/amount of recycling

---

## General Description

### Application Perspective
The developed system is not standalone but related to other software systems. Besides the under-development product described in section 1.2, it includes:

- **Mobile Application:** Each user has the app installed on their mobile device, allowing them to perform the aforementioned actions to recycle products.
- **Server (Backend):** Mediates between the application and the database, implemented with Spring Boot for API Calls.
- **Database:** Contains predefined values for demo purposes.

### Constraints
- The system requires Java version 17 or higher and Android Studio libraries.
- The system requires a setup like XAMPP for running an Apache Server and a database.

### Assumptions
- Each user is identified via a username and password.
- For simplicity, no security policies regarding user authentication were used.
- The application admin is predefined, and no more than one admin can be created without programming intervention.

---

## Data Design

### Databases
MySQL is used to store application data in four tables: `users`, `recycle_items`, `recycle_requests`, and `request_list_items`. The interaction between the server and the database is managed through Jakarta Persistence API (JPA), which maps classes to relational tables. Each class object corresponds to a row in the respective relational table, and each object property corresponds to a column.

### Files
The files used and created are as follows:

- **Backend Folder:** Contains the implemented code in Spring Boot, `schema.sql` and `data.sql` files for describing the database schema and initializing the database respectively, an `initialScriptForDB.sql` file for creating the database in XAMPP, and an `application.properties` file providing the database connection details.
- **Frontend Folder:** Contains the implemented code in Java for Android Studio along with all the necessary files (images, fonts).
