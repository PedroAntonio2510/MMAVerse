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

*   - [ ] **Detailed Fight Statistics:** Track and serve detailed stats for each fight (e.g., takedowns, significant strikes, submission attempts).
*   - [ ] **Event Scheduling & Notifications:** Allow users to subscribe to upcoming events and receive notifications.
*   - [ ] **User Fight Predictions:** A system for users to make and track their predictions for upcoming fights.
*   - [ ] **Public API Documentation:** Enhance the existing Swagger UI with more detailed examples and guides.
*   - [ ] **Advanced Search:** Implement more complex search queries (e.g., find all undefeated fighters in a specific weight class).
*   - [ ] **Fight Card Endpoint:** Add an endpoint to retrieve the full fight card for a specific event (e.g., `GET /mmaverse/event/{eventId}/fights`).
*   - [ ] **Contender Fight History:** Create an endpoint to list all past and upcoming fights for a specific contender (e.g., `GET /mmaverse/contender/{contenderId}/fights`).
*   - [ ] **User Roles & Permissions:** Implement Role-Based Access Control (RBAC) with roles like "admin" and "editor" to restrict access to sensitive operations.
*   - [ ] **Fight Results Submission:** Develop a dedicated and secure endpoint for submitting and verifying fight outcomes (e.g., `POST /mmaverse/fighting/{fightingId}/result`).
*   - [ ] **Media/Image Uploads:** Implement functionality for uploading images for contenders, events, and organizations.
*   - [ ] **Advanced Search & Filtering:** Enhance the API with advanced filtering options, such as filtering contenders by weight class (e.g., `GET /mmaverse/contender?weightClass=Heavyweight`).
*   - [ ] **Pagination:** Implement pagination for all `findAll` endpoints to efficiently handle large datasets.

## Technologies Used

*   **Java**
*   **Spring Boot**
*   **Maven**
*   **Spring Data JPA (Hibernate)**
*   **PostgreSQL (or other SQL database)**
*   **Flyway (for database migrations)**
*   **Spring Security & JWT**
*   **Docker**