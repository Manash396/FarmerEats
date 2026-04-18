# 🌾 FarmerEats – Farmer Registration App

FarmerEats is an Android application designed to onboard farmers through a **multi-step registration flow**.  
It provides a smooth and modern experience for entering personal details, business information, document verification, and availability setup.

---

## 📱 About the App

FarmerEats simplifies farmer onboarding by providing:

- 🧾 Step-by-step registration flow
- 👤 User profile creation with validation
- 🏪 Business information setup
- 📄 PDF upload for registration proof (Multipart API)
- ⏰ Business hours selection (day-wise slots)
- ✅ Final success confirmation screen
- 🎨 Clean and modern UI using Jetpack Compose

The app is built following modern Android development practices with a focus on **scalability, maintainability, and clean architecture**.

---

## 🛠 Tech Stack

### Language & Core
- Kotlin
- Android SDK

### Architecture
- MVVM (Model–View–ViewModel)
- Clean Architecture principles

### UI
- Jetpack Compose
- Material 3

### Networking
- Retrofit (Multipart API handling)

### State Management
- Compose State + UI State handling

### Other
- Activity Result API (PDF picker)
- URI → File conversion
- Modern Android Jetpack Libraries

---
## 🚀 Onboarding

| Screen 1 | Screen 2 | Screen 3 |
|----------|----------|----------|
| ![Onboarding1](screenshots/onboarding1.png) | ![Onboarding2](screenshots/onboarding2.png) | ![Onboarding3](screenshots/onboarding3.png) |

---

## 🔐 Authentication Screens

| Login | Step1 | Step2 |
|----------|----------|----------|
| ![Login](screenshots/login.png) | ![Step1](screenshots/step1.png) | ![Step2](screenshots/step2.png) |

---


## 📄 Verification & Other Screens

|  Step3  |  Step4  | Success |
|----------|----------|----------|
| ![Step3](screenshots/step3.png) |![Step4](screenshots/step4.png) | ![Success](screenshots/success.png) |

---


## Demo 

<img src="screenshots/demo.gif" alt="FarmerEats Demo" width="250" height="auto" style="border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);">


## 🔥 Key Implementations

### 📄 File Upload (PDF)
- Implemented using **Multipart API**
- File picker using Activity Result API
- URI converted to File before upload

---

### ⏰ Business Hours Selection
- Day-wise slot selection UI
- Dynamic state handling in Compose
- Stored using structured `BusinessHours` model

---

### ⚡ Form Validation
- Real-time error handling
- Multi-step validation logic

---

## 🚀 Project Setup (Git Commands)

### 1️⃣ Clone the repository
```bash
git clone https://github.com/Manash396/FarmerEats.git
