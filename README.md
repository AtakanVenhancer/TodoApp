# Kotlin Guide App

Bu proje Kotlin ve JetBrains Compose kullanarak masaüstünde çalıştırabileceğin küçük ama kapsamlı bir örnek uygumadır. Amaç; çoklu dil desteği, basit navigasyon mantığı, state management yaklaşımı ve temel kullanıcı etkileşimlerini tek bir yerde göstermek.

## İçerik
- **Çoklu dil**: `Localization` objesi TR/EN metinlerini yönetir, `LocalePicker` bileşeni ile uygulama anında güncellenir.
- **Navigasyon**: `Route` sealed interface ve `StateStore` içindeki `Action.Navigate` ile tek yerden yönetilen basit bir yönlendirme örneği.
- **State management**: `StateStore` içinde `MutableStateFlow` + reducer yaklaşımı kullanıldı. Tüm UI `state.collectAsState()` ile beslenir.
- **Temel işlemler**: Sayaç artırma, form girişi, geçmiş listesi gibi basit etkileşimler.

## Proje Yapısı
```
KotlinBase/
 ├─ build.gradle.kts          # Kotlin + Compose konfigürasyonu
 ├─ settings.gradle.kts       # Proje adı
 ├─ gradlew / gradlew.bat     # Gradle wrapper
 ├─ gradle/wrapper            # Wrapper konfigürasyonu
 └─ src/main/kotlin/com/example/kbase/Main.kt
```

### `Main.kt`
- `StateStore`: reducer mantığı ile aksiyonları işler.
- `Localization`: TR/EN metinlerini döner.
- `KotlinGuideApp`: Compose `Scaffold` içinde top bar, nav sütunu ve ekranları yönetir.
- `HomeScreen`, `DetailScreen`, `SettingsScreen`: Navigasyon örnekleri.

## Sonraki Adımlar
- Yeni ekran eklemek için `Route` sealed interface'e yeni bir nesne ekle ve `StateStore` içinde `Action.Navigate` mantığını yeniden kullan.
- Beşten fazla dili desteklemek için `Localization` objesindeki map yapısını genişlet.
- State'i kalıcı yapmak istersen `kotlinx.serialization` + dosya sistemi veya basit bir veritabanı ile `StateStore` güncelle.
