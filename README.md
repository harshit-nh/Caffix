# Caffix ☕

A modern, feature-rich Android application designed for a seamless coffee ordering experience. Caffix demonstrates best practices in native Android development, utilizing a modern tech stack and robust architectural principles to deliver a highly responsive, scalable, and maintainable application.

## 📸 Screenshots

<img width="720" height="1600" alt="home_screen" src="https://github.com/user-attachments/assets/0c0500d0-27ef-49c1-8f1c-2110ceded49a" /> | <img width="720" height="1600" alt="Cart_screen" src="https://github.com/user-attachments/assets/f394b9b6-676e-49e2-9627-015baf9a0288" /> | <img width="720" height="1600" alt="Cart_screen" src="https://github.com/user-attachments/assets/b327fd96-e19a-4976-913c-83cb8aed1cb8" /> | 


## ✨ Key Features

* **Dynamic Feed Integration:** Consumes robust backend schemas to render dynamic feed APIs, ensuring real-time menu and promotional updates.
* **Live Location Services:** Integrates live location fetching to pinpoint users for precise delivery and store discovery.
* **Advanced Search Behaviors:** Features a highly responsive header search bar component with optimized query handling and state management.
* **Custom Dark Theme:** Full Material Design support with tailored dark theme customization and custom color states for a premium user experience across all device settings.
* **Reactive UI:** Fluid and responsive interfaces built entirely with declarative UI components.

## 🛠️ Tech Stack & Libraries

* **Language:** [Kotlin]
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Architecture:** Clean Architecture + MVVM (Model-View-ViewModel)
* **Asynchronous Programming:** Coroutines & StateFlow for reactive, lifecycle-aware data streams
* **Dependency Injection:** Dagger Hilt
* **Networking:** Retrofit2 & OkHttp
* **Local Persistence:** Room Database *(if applicable)*

## 🏗️ Architecture

Caffix is built adhering to **Clean Architecture** principles to ensure separation of concerns, testability, and scalability. 
* **Presentation Layer:** Jetpack Compose screens and ViewModels managing UI state via StateFlow.
* **Domain Layer:** Use cases encapsulating core business logic and repository interfaces.
* **Data Layer:** Repository implementations, local data sources (Room), and remote data sources (Retrofit).

