🔗 Scalable URL Shortener Service

A high-performance, production-oriented backend service built using Java 17 and Spring Boot 3, designed to convert long URLs into compact, unique, and shareable links with efficient redirection and persistent storage.

🌟 Overview
This project is designed with a strong focus on:

Clean layered architecture
Deterministic unique key generation
Read-heavy performance optimization
Scalability-first design principles
It leverages a Base62 encoding strategy combined with a PostgreSQL auto-generated identifier to guarantee uniqueness while keeping URLs short, human-readable, and collision-free.

🚀 Key Features
🔹 Deterministic URL Shortening
Converts long URLs into compact Base62-encoded identifiers.
Ensures uniqueness using database-generated BigInt IDs.
Avoids collision-prone random string generation.

🔹 High-Speed Redirection
Implements HTTP 302 (Found) redirection.
Optimized for read-heavy traffic patterns.
Indexed lookup on short_code for O(log n) retrieval.

🔹 Layered Architecture
Controller → Service → Repository separation.
Clean exception handling.
DTO-based API communication.

🔹 Robust Error Handling
Graceful handling of:
Invalid short codes (404)
Expired links (410 - Gone)
Malformed URLs (400)

🔹 PostgreSQL Persistence
Indexed relational storage.
BigInt ID generation for scalable encoding.
Designed for horizontal scaling with stateless services.

🛠️ Tech Stack
Language: Java 17
Framework: Spring Boot 3.x
Database: PostgreSQL 14+
ORM: Spring Data JPA (Hibernate)
Frontend: HTML5 + Tailwind CSS + Fetch API
Build Tool: Maven

📐 How It Works (Core Logic)
Unlike random string generators, this system uses a deterministic Base62 encoding strategy:

1️⃣ User submits a long URL
2️⃣ The application persists it in PostgreSQL
3️⃣ PostgreSQL generates a unique BigInt ID (e.g., 200456)
4️⃣ Service layer encodes the ID into Base62
5️⃣ Encoded value becomes the short code (e.g., Dk2)
6️⃣ Final short URL returned:

http://localhost:8080/v1/Dk2
Why Base62?
Uses a-z, A-Z, 0-9
Compact representation

Human-readable

Efficient encoding
No collision when based on unique DB ID
🔌 API Documentation
Shorten URL
POST /api/v1/shorten

Request:
{
  "url": "https://example.com/very/long/url"
}

Response:
{
  "shortUrl": "http://localhost:8080/v1/Dk2"
}
Redirect
GET /api/v1/{shortCode}

Returns:
HTTP 302 → Redirect to original URL
404 → If short code not found
410 → If expired

🧠 System Design Considerations

Read-heavy workload (redirect endpoint)
Indexed lookup on short_code
Stateless application layer
Ready for:
Redis caching layer
Load balancer
Horizontal scaling

🗺️ Roadmap & Future Enhancements
 Add Spring Security + JWT authentication
 Redis caching for sub-10ms redirect latency
 Click analytics tracking
 Custom short aliases
 Rate limiting & abuse protection
 Docker + AWS deployment

 👨‍💻 Developer

Nitish Kumar Singh
Java Backend Developer | Spring Boot | PostgreSQL | System Design
🧠 Focused on building scalable backend systems
🚀 Currently building production-oriented backend applications
