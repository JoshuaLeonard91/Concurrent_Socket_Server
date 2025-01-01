# Concurrent Socket Server

## Overview
This project involves the implementation of a **concurrent (multi-threaded) server** and a **multi-threaded client** to study the impact of concurrency on the efficiency (average turnaround time) of processing client requests. The project demonstrates how the server handles multiple client requests in parallel by spawning a new thread for each connection.

## Features
- **Concurrent Server**:
  - Spawns a new server thread for each client request, enabling parallel processing.
  - Supports the following client requests:
    - Current Date and Time
    - Server Uptime
    - Memory Usage
    - Netstat Output
    - List of Current Users
    - Running Processes
  - Handles clean-up and resource management for each thread after the request is processed.

- **Multi-Threaded Client**:
  - Simulates multiple simultaneous client connections to the server.
  - Collects metrics on turnaround time for performance analysis.
  - Provides a user-friendly interface to configure and transmit requests.

## Requirements
- **Java Development Kit (JDK) 8 or higher**
- **Access to a Linux-based system** for executing system-level commands like `netstat`, `ps`, and `w`.
- Basic knowledge of multi-threaded programming and socket communication.
 
