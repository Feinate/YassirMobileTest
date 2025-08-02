# YassirTest

Android application developed as part of a technical test, based on the Rick & Morty universe.  
It displays a paginated list of characters with search functionality and navigation to detailed character profiles.

---

## 🚀 Build & Run Instructions

### Prerequisites:
- Android Studio Iguana or higher (recommended: Narwhal)
- Android SDK 28+
- Kotlin 2.2.0
- JDK 11

### Steps:
1. Clone the project:
   ```bash
   git clone https://github.com/Feinate/YassirMobileTest.git
   ```
2. Open with Android Studio.
3. Run the project on an emulator or physical device using the Run button.

---

## 🧱 Architecture

The project follows a modular architecture based on **Clean Architecture + MVVM + Clean Code**.

### Structure:
```
├── core/
│   └── data/
│   └── di/
│   └── domain/
│   └── presentation/
│   └── utils/
│
├── feature_main/
│   ├── data/
│   ├── domain/
│   ├── presentation/
│   └── utils/
```

### Principles:
- Clear separation of concerns (data, domain, UI).
- **Koin** for dependency injection.
- **Paging 3** for pagination.
- **Ktor** for network calls.
- **Jetpack Compose** for user interface.
- **Coil** for image loading.

---

## 🧠 Features

- ✅ Paginated character list.
- ✅ Search bar with pagination reset.
- ✅ Navigation between home page and character details.
- ✅ Detail display (image, name, species, status).
- ⚠️ Loading and error state management (partially implemented).

---

## 📌 Assumptions and Limitations

### Limitations (due to time constraints):
❌ **No local persistence**:
   - Planned with Room + RemoteMediator for API → Cache → View architecture.

❌ **No unit/UI tests**:
   - However, the architecture easily allows adding:
     - Integration tests on the API (verify pagination, uniqueness, errors).
     - UI tests (Loading, Empty, Error, Success states).

### Assumptions:
- Search is assumed to be handled server-side by the API.
- Each item is uniquely identified (via character id).

---

## 🧩 Libraries Used

| Technology | Usage |
|------------|-------|
| **Jetpack Compose** | Declarative UI |
| **Paging 3** | Data pagination |
| **Koin** | DI (dependency injection) |
| **Ktor** | HTTP requests |
| **Coil** | Image loading |
| **Navigation Compose** | Screen navigation |
| **Kotlin Coroutines** | Asynchronicity, Flow |
| **Lifecycle** | Lifecycle management |

---

## ✅ Future Improvements

- Implementation of persistence layer with Room.
- Addition of unit, UI and integration tests.
- Enhanced error handling (retry, empty view, etc).
- Multi-language support.
