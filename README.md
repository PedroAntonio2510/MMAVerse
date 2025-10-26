# MMAVerse

## About The Project

MMAVerse is a comprehensive RESTful API designed to manage and provide data for a Mixed Martial Arts (MMA) universe. This backend service allows users to interact with a rich dataset of fighters, organizations, events, rankings, and more. It serves as the backbone for any application aiming to deliver up-to-date information about the world of MMA.

The project is built with Java and the Spring Boot framework, ensuring a robust and scalable architecture.

## Core Features

*   **User Authentication:** Secure JWT-based authentication for users.
*   **Organization Management:** CRUD operations for MMA organizations (e.g., UFC, Bellator).
*   **Contender Management:** Manage fighter profiles, including their records, weight class, and gym affiliations.
*   **Event Tracking:** Create and manage fight events, including dates, locations, and fight cards.
*   **Fight Management:** Detailed information about individual fights within an event.
*   **Gym Profiles:** Manage information about the gyms where contenders train.
*   **Rankings System:** Manage and display official rankings for different weight classes.
*   **Streaming Information:** Link events to the platforms where they can be streamed.

## Future Implementations

*   **Detailed Fight Statistics:** Track and serve detailed stats for each fight (e.g., takedowns, significant strikes, submission attempts).
*   **Event Scheduling & Notifications:** Allow users to subscribe to upcoming events and receive notifications.
*   **User Fight Predictions:** A system for users to make and track their predictions for upcoming fights.
*   **Public API Documentation:** Enhance the existing Swagger UI with more detailed examples and guides.
*   **Advanced Search:** Implement more complex search queries (e.g., find all undefeated fighters in a specific weight class).

## Technologies Used

*   **Java**
*   **Spring Boot**
*   **Maven**
*   **Spring Data JPA (Hibernate)**
*   **PostgreSQL (or other SQL database)**
*   **Flyway (for database migrations)**
*   **Spring Security & JWT**
*   **Docker**