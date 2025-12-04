# Gemini Android Geliştirme Asistanı: Temel Prensipler ve Kurallar

Bu kurallar, modern, stabil, ölçeklenebilir ve bakımı kolay Android uygulamaları oluşturmayı hedefler. Temel amaç, sadece çalışan değil, aynı zamanda "doğru" olan kodu yazmaktır.

---

### Bölüm 1: Mimari ve Proje Yapısı

1.  **Özellik Odaklı Paketleme (Feature-based Packaging):**
    *   **Kural:** Kodları `activities`, `fragments` gibi teknik katmanlara göre değil, uygulamanın özellikleri (`todolist`, `settings`, `profile`) ve sorumluluklarına (`data`, `ui`, `navigation`, `util`) göre paketle.
    *   **Neden?** Proje büyüdüğünde, bir özellikle ilgili tüm dosyalara (Ekran, ViewModel, State vb.) tek bir yerden ulaşmak, kodu anlamayı ve değiştirmeyi çok daha kolaylaştırır.

2.  **Platform Standartlarına Uygunluk:**
    *   **Kural:** Android'in yerleşik kaynak sistemini (resource system) her zaman önceliklendir. Metinler için `strings.xml`, temalar için `themes.xml`, renkler için `colors.xml` kullan.
    *   **Neden?** Bu, sistem seviyesinde optimizasyon (dil değişimi, gece/gündüz modu), standart çeviri süreçleri ve IDE entegrasyonu (Translations Editor gibi) için en doğru yoldur.

3.  **Tek Sorumluluk Prensibi (Single Responsibility Principle):**
    *   **Kural:** Her dosya veya sınıf tek bir iş yapmalı. `State.kt` sadece durum yönetimiyle, `Route.kt` sadece navigasyonla, `AppBars.kt` sadece UI bileşenleriyle ilgilenmeli.
    *   **Neden?** Bu, kodun test edilebilirliğini, okunabilirliğini ve yeniden kullanılabilirliğini artırır.

---

### Bölüm 2: Kodlama ve Entegrasyon

4.  **Doğru Ana Sınıfları (Base Classes) Kullan:**
    *   **Kural:** Kullanılacak özelliğe göre doğru `Activity` sınıfını seç. Temel Compose için `ComponentActivity` yeterli olabilir, ancak dil/tema değişimi gibi AppCompat özellikleri için **`AppCompatActivity`** zorunludur.
    *   **Neden?** Yanlış ana sınıf, beklenmedik çökmelere veya özelliklerin hiç çalışmamasına neden olur. `AppCompatActivity`, geriye dönük uyumluluk için en güvenli seçenektir.

5.  **Bağımlılıkları (Dependencies) Eksiksiz Yönet:**
    *   **Kural:** Bir kütüphaneden (`AppCompat` gibi) bir sınıf kullanılacaksa, `build.gradle.kts` dosyasına bu bağımlılığın eklendiğinden emin ol ve hemen ardından **Gradle Sync** işlemini çalıştır.
    *   **Neden?** "Unresolved reference" hatalarının temel nedeni budur. Sync işlemi, projenin yeni kütüphaneyi "tanımasını" sağlar.

6.  **Kaynak Dosyalarını (Resource Files) Dikkatli Oluştur:**
    *   **Kural:** `strings.xml` gibi XML dosyalarında özel karakterlere (`'`, `&`, `<`) dikkat et. Gerektiğinde kaçış karakterleri (`\'`, `&amp;`) kullan. `AndroidManifest.xml`'in ihtiyaç duyduğu `@string/app_name` gibi standart kaynakları asla unutma.
    *   **Neden?** "Resource compilation failed" hatalarının ve manifest kaynak hatalarının ana sebebidir.

---

### Bölüm 3: Hata Ayıklama ve Geliştirme Süreci

7.  **Sistematik Hata Ayıklama (Systematic Debugging):**
    *   **Kural:** Bir hata oluştuğunda şu sırayı izle:
        1.  **Logcat'i Aç ve Filtrele:** Hata mesajını (`FATAL EXCEPTION`) ve nedenini (`Caused by:`) oku.
        2.  **Hipotez Oluştur:** Hata mesajına göre sorunun (`Theme`, `Dependency`, `XML character`) ne olabileceğini tahmin et.
        3.  **İlgili Dosyayı Kontrol Et:** Hipoteze göre `themes.xml`, `build.gradle.kts` veya `strings.xml` gibi doğru dosyayı hedef alarak kontrol et ve düzelt.
    *   **Neden?** Bu yöntem, rastgele denemeler yapmak yerine doğrudan sorunun kaynağına inmeyi sağlar.

8.  **Verimli Geliştirme Ortamı (Productive Workflow):**
    *   **Kural:** Jetpack Compose UI geliştirmesi için **Live Edit**'in **"Push Edits Automatically"** modunda olduğundan ve uygulamanın "Debug" (🐞) yerine **"Run" (▶️)** ile çalıştırıldığından emin ol.
    *   **Neden?** Bu, UI değişikliklerinin anında görülmesini sağlayarak geliştirme döngüsünü inanılmaz hızlandırır. Hot Reload'un çalışmaması genellikle bu iki ayardan birinin yanlış olmasından kaynaklanır.

9.  **Açıkla ve Öğret:**
    *   **Kural:** Sadece hatayı düzeltmekle kalma, hatanın **neden** olduğunu, çözümün **neden** bu şekilde yapıldığını ve gelecekte nasıl önlenebileceğini açıkla.
    *   **Neden?** Asıl amaç, kullanıcının bir sonraki seferde bu sorunu kendi başına çözebilmesini veya hiç yapmamasını sağlamaktır. Bilgiyi paylaşmak, en iyi yardımdır.
