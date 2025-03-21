# QR Scanner

![mockup_qrapp](https://github.com/user-attachments/assets/d88b21e6-127f-44c3-9c84-748e65e774ba)


## 📌 Project Overview

QR Scanner is an intuitive Android application designed to quickly and efficiently scan and interpret QR codes. It simplifies the process of extracting embedded data such as website URLs, contacts, or text from QR codes, making information readily accessible.

## 🚀 Features

- **Rapid QR Code Scanning:** Fast detection and decoding of QR codes using your device's camera.
- **User-Friendly Interface:** Easy-to-navigate UI, ensuring a seamless scanning experience.
- **Scan History:** Automatically logs scanned QR codes for quick reference later.

## 🛠️ Architecture

The project adopts a modular architecture pattern and MVI to maintain clean separation of concerns, enhancing readability, maintainability, and scalability:

- **Presentation Layer:** Manages UI and user interactions, built with Jetpack Compose.
- **Domain Layer:** Houses the app's business logic and application use cases.
- **Data Layer:** Handles data operations including local database interactions and potential network requests.

## 🔧 Tech Stack

- **Kotlin** - Main programming language.
- **Android SDK** - Framework for Android app development.
- **Jetpack Compose** - For building modern UI.
- **ZXing** - Library used for processing and decoding QR codes.
- **Room** - Local database persistence for Android.
- **Hilt DI** - Dependency injection library for Android.
- **MocKk - Junit4** - Testing Libraries.


## 📁 Project Structure

The project is structured clearly to improve navigation and understanding:

```
QR_Scanner/
├── app/                 # Application UI, activities, and components
├── data/                # Data management including repositories and data sources
└── domain/              # Business logic and use cases
```

---

## 🎥 Demo Video
https://github.com/user-attachments/assets/1718a3bf-ed9a-4f51-87e8-6d6eb9731a51





