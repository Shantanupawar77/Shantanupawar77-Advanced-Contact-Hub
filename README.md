# Advanced Cloud-Based Contact Management System

A fully-featured contact management system built with Spring Boot, Google/GitHub OAuth for authentication, and AWS/Cloudinary for image storage. This application enables users to securely manage contacts, send emails with attachments, and customize their experience with a responsive dark/light theme.

## Features

- **User Authentication**:
  - Signup using email and password with email verification.
  - OAuth authentication with Google and GitHub.

- **Contact Management**:
  - Add, update, and delete contacts with profile pictures.
  - Contact pictures are uploaded to AWS/Cloudinary.
  - View all contacts with pagination.
  - Search for contacts by name.
  - Mark contacts as favorites.
  - Export contact data to Excel.

- **Email Integration**:
  - Compose and send emails directly from the application.
  - Email messages support text and attachments.

- **User Profile**:
  - View and edit personal profile details.
  - Provide feedback about the application.

- **UI Customization**:
  - Dark and light theme options for a personalized user experience.

## Tech Stack

- **Backend**: Spring Boot
- **Frontend**: Thymeleaf
- **Authentication**: Google/GitHub OAuth
- **Storage**: AWS S3/Cloudinary for image uploads
- **Database**: MySQL (or any other relational database)
- **Email Service**: JavaMail for sending emails with attachments

## Setup Instructions

### Prerequisites

- Java 8 or later
- MySQL or other database service
- AWS/Cloudinary account for image storage
- Google and GitHub OAuth credentials
- AWS S3 Bucket (if using AWS for image storage)

### Steps to Run Locally

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/advanced-contact-management.git
    ```

2. Set up your database:

    - Create a new MySQL database named `contact_management`.
    - Update the `application.properties` file with your database configuration:
    
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/contact_management
      spring.datasource.username=your_db_username
      spring.datasource.password=your_db_password
      ```

3. Set up your Google and GitHub OAuth credentials:

    - Create credentials for Google and GitHub OAuth from their respective developer consoles.
    - Add the credentials in the `application.properties` file:
    
      ```properties
      spring.security.oauth2.client.registration.google.client-id=your_google_client_id
      spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret
      spring.security.oauth2.client.registration.github.client-id=your_github_client_id
      spring.security.oauth2.client.registration.github.client-secret=your_github_client_secret
      ```

4. Set up AWS/Cloudinary configuration for image uploads:

    ```properties
    cloudinary.cloud-name=your_cloud_name
    cloudinary.api-key=your_cloudinary_api_key
    cloudinary.api-secret=your_cloudinary_api_secret
    ```

5. Run the application:

    ```bash
    mvn spring-boot:run
    ```

6. Access the application at `http://localhost:8080`.

