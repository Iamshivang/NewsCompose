# NewsCompose 📰✨

A beautifully designed **Jetpack Compose** Android app built with **Kotlin**, that delivers real-time breaking news. Users can securely log in, browse news by topic, search articles, save them offline, and share effortlessly.

---

## 🚀 Features

* 🔐 **Authentication**

  * Login, Signup
  * Forgot password with reset feature
* 🔴 **Latest Breaking News** from [NewsAPI.org](https://newsapi.org)
* 🔍 **Search** for any topic or article
* 📄 **Read Full Articles** inside the app
* 📤 **Share** news externally
* 📁 **Save/Delete** articles from local Room DB

---

## 📚 Tech Stack & Libraries

### ⚖️ Architecture

* **MVVM** — clear separation of concerns
* **Clean Architecture** — `data`, `domain`, `presentation` layers
* **Generic UI State Class** — for managing API/data loading states

### ⚡ Reactive/Data

* **Kotlin Coroutines** — async operations
* **Flow & mutableStateOf** — reactive UI binding
* **DataStore** — persistent login status

### 🚚 Jetpack Components

* **Navigation Compose** — in-app screen navigation
* **Splash Screen API** — modern entry transition
* **Lifecycle ViewModel & Runtime KTX** — lifecycle-aware
* **Activity KTX** — for easy ViewModel access

### 🛠️ Dependency Injection

* **Hilt** — ViewModel and repository injection
* **Hilt Navigation Compose** — ViewModel inside Composables

### 📈 Networking

* **Retrofit + Gson** — consume NewsAPI
* **OkHttp Logging Interceptor** — for API debugging

### 📃 Local Storage

* **Room** — persist saved articles

### 📱 UI & Media

* **Jetpack Compose** — modern declarative UI
* **Material 3** — Material You + NavigationDrawer
* **Coil** — fast image loading

### 🚀 Firebase

* **Firebase Auth** — secure login/signup

### 👀 Paging

* **Paging Compose** — infinite scrolling in news feeds

---

## 🚧 Permissions Required

* **INTERNET** — to fetch breaking news from NewsAPI

---

## 📋 Getting Started

1. Clone the repo:

   ```bash
   git clone https://github.com/yourusername/ComposeNews.git
   ```
2. Add your [NewsAPI.org](https://newsapi.org) key in `local.properties`:

   ```properties
   API_KEY=your_api_key_here
   ```
3. Inject in `build.gradle(:app)`:

   ```kotlin
   buildConfigField("String", "API_KEY", API_KEY)
   ```
4. Run on Android Studio Hedgehog or later

---

## 📍 Keywords & Search Tags

NewsCompose • News • Jetpack Compose • Jetpack • Compose • Kotlin • News App • Firebase • Room • Hilt • Retrofit • MVVM • Clean Architecture • State Management • Paging • Coroutines • Flow • DataStore • Navigation • Material 3 • NewsAPI • Compose Navigation • Android

---

## 📊 Preview

<p align="center">
  <img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose1.png" width="250" />
<img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose2.png" width="250" />
<img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose3.png" width="250" />
<img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose4.png" width="250" />
<img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose6.png" width="250" />
<img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose7.png" width="250" />
<img src="https://github.com/Iamshivang/NewsCompose/blob/main/app/assets/newscompose8.png" width="250" />
</p>

---

## 🌟 Credits

Developed with ❤️ by [Shivang Yadav](https://github.com/Iamshivang)

---

## 📅 License

[MIT License](https://github.com/Iamshivang/NewsCompose/blob/main/LICENSE)
