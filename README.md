# VlessGram — Telegram для Android с поддержкой VLESS/VPN

<div align="center">

[![License: GPL v2](https://img.shields.io/badge/License-GPL%20v2-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![GitHub Release](https://img.shields.io/github/v/release/1ShadowWarrior1/VlessGram)](https://github.com/1ShadowWarrior1/VlessGram/releases)
[![GitHub Stars](https://img.shields.io/github/stars/1ShadowWarrior1/VlessGram)](https://github.com/1ShadowWarrior1/VlessGram/stargazers)

**VlessGram** — форк Telegram для Android со встроенной поддержкой протоколов **VLESS**, **Trojan** и **Shadowsocks**

[English Version Below ⬇️](#-english-version)

</div>

---

# 🇷🇺 Русская версия

## 📖 О проекте

**VlessGram** — это модифицированная версия популярного мессенджера Telegram для Android с интегрированной поддержкой современных прокси-протоколов. В отличие от стандартного клиента, VlessGram не требует установки дополнительных приложений для работы с прокси — вся функциональность встроена непосредственно в приложение.

Приложение использует библиотеку `libvless` для создания локального SOCKS5-прокси, через который проходит весь трафик Telegram. Это обеспечивает стабильное соединение даже в условиях сетевых ограничений.

### Ключевые преимущества

- **Полная интеграция** — прокси работает на уровне приложения, без необходимости использования VPN-сервисов
- **Конфиденциальность** — весь трафик шифруется и проходит через выбранные вами серверы
- **Производительность** — минимальные задержки благодаря прямой маршрутизации
- **Удобство** — управление узлами прямо из интерфейса Telegram

---

## ✨ Возможности

### Поддерживаемые протоколы

| Протокол | Описание | Преимущества |
|----------|----------|--------------|
| **VLESS** | Современный протокол на базе V2Ray | Высокая скорость, низкое потребление ресурсов, сложность обнаружения |
| **Trojan** | Протокол, маскирующийся под HTTPS | Обход глубокой инспекции трафика (DPI), выглядит как обычный веб-трафик |
| **Shadowsocks** | Проверенный временем прокси-протокол | Стабильность, широкая поддержка, простая настройка |

### Ключевые функции

| Функция | Описание |
|---------|----------|
| **Локальный SOCKS5 прокси** | Весь трафик Telegram проходит через библиотеку libvless без внешних зависимостей |
| **Встроенная VPN-функциональность** | Не требуется установка дополнительных приложений для работы с прокси |
| **Автопереключение** | При потере соединения приложение автоматически переключается на узел с лучшим пингом |
| **Ping-мониторинг** | Постоянная проверка доступности всех узлов в реальном времени |
| **Поддержка подписок** | Импорт списков узлов из подписок по URL (обновляются автоматически) |
| **Автозапуск** | Автоматическое подключение к последнему активному узлу при запуске приложения |
| **Ручное управление** | Полная свобода выбора — подключайтесь к любому узлу одним касанием |

### Управление узлами

- **Добавление узлов** — ручное добавление через URI или импорт из подписки
- **Форматы подписок** — поддержка JSON, Base64 и обычного текста
- **Сортировка** — автоматическая сортировка узлов по значению пинга
- **Массовая проверка** — проверка пинга всех узлов одним нажатием
- **Статусы узлов** — визуальное отображение состояния каждого узла (онлайн/оффлайн)
- **Редактирование** — изменение параметров подключённых узлов
- **Удаление** — очистка списка от нерабочих узлов

---

## 📚 Как пользоваться VLESS

### Шаг 1: Добавить узел

#### Вариант A — Добавить ключ вручную

Используйте этот способ, если у вас есть готовый URI-ключ от прокси-сервера:

1. Откройте **Настройки** в Telegram
2. Перейдите в раздел **Данные и память**
3. Выберите **Настройки прокси**
4. Нажмите кнопку **➕ Добавить ключ**
5. Вставьте скопированный URI VLESS/Trojan/Shadowsocks в поле ввода
6. Нажмите **Сохранить**

Ключ появится в списке доступных узлов.

#### Вариант B — Добавить подписку

Используйте этот способ для автоматического импорта списка узлов:

1. Откройте **Настройки** в Telegram
2. Перейдите в раздел **Данные и память**
3. Выберите **Настройки прокси**
4. Нажмите кнопку **➕ Добавить подписку**
5. Введите URL вашей подписки (предоставляется провайдером)
6. Нажмите **Сохранить**

Все узлы из подписки будут автоматически импортированы и добавлены в список.

> **Примечание:** Подписки можно обновлять вручную для получения актуального списка узлов.

---

### Шаг 2: Настроить VLESS

После добавления узлов рекомендуется настроить параметры подключения:

| Настройка | Описание | Рекомендация |
|-----------|----------|--------------|
| **Автовключение** | Автоматически подключаться к VLESS при запуске Telegram | Включить для удобства |
| **Умное переключение** | Автопереключение на лучший узел при сбое соединения | Включить для стабильности |
| **Сортировка по пингу** | Сортировать узлы по задержке (узлы с меньшим пингом — выше в списке) | Включить для быстрого выбора |

---

## ⚠️ Важно: Отключение ограничений работы в фоне

### Почему это критически важно

Современные версии Android включают агрессивную систему экономии заряда батареи, которая ограничивает работу приложений в фоновом режиме. По умолчанию система может:

- **Останавливать службы VLESS** при сворачивании приложения или переходе в спящий режим
- **Отключать VPN-соединение** при выключенном экране
- **Прерывать проверку пинга** серверов, что приводит к неактуальной информации о статусе узлов
- **Завершать процесс приложения** для освобождения оперативной памяти

**Результат:** VLESS перестаёт работать, когда телефон находится в кармане, экран выключен, или приложение свёрнуто. Это делает прокси бесполезным в самый нужный момент.

### Как отключить ограничения

Настройки могут отличаться в зависимости от производителя устройства. Найдите вашу модель ниже:

#### Samsung (One UI)

```
Настройки → Приложения → VlessGram → Батарея → 
Оптимизация батареи → Все приложения (вверху справа) → 
Найти VlessGram в списке → Переключить в положение «Отключить»
```

**Дополнительно:**
```
Настройки → Уход за устройством → Батарея → 
Фоновые ограничения → Неограниченные приложения → Добавить VlessGram
```

#### Xiaomi / Redmi / POCO (MIUI / HyperOS)

```
Настройки → Приложения → Все приложения → VlessGram → 
Батарея → Фоновая работа → Разрешить
```

**Критически важно (MIUI):**
```
Безопасность → Разрешения → Автозапуск → Найти VlessGram → Включить
```

**Дополнительно:**
```
Настройки → Расширенные настройки → Батарея и производительность → 
Экономия заряда → Приложения → VlessGram → Без ограничений
```

#### Huawei / Honor (EMUI / MagicOS)

```
Настройки → Батарея → Запуск приложений → 
Найти VlessGram → Отключить «Автоматическое управление» → 
Включить все три опции (Автозапуск, Косвенный запуск, Вторичный запуск)
```

**Дополнительно:**
```
Настройки → Приложения → VlessGram → Батарея → 
Запуск в фоне → Разрешить
```

#### OnePlus / OPPO / Realme (OxygenOS / ColorOS)

```
Настройки → Приложения → VlessGram → 
Расширенные настройки → Батарея → 
Разрешить фоновую активность
```

**Дополнительно:**
```
Настройки → Батарея → Фоновая работа приложений → 
Найти VlessGram → Разрешить
```

#### Pixel / Motorola / Nokia (Stock Android)

```
Настройки → Приложения → Все приложения → VlessGram → 
Батарея → Не ограничивать
```

#### Sony (Xperia UI)

```
Настройки → Питание → STAMINA → 
Исключения → Добавить VlessGram
```

**Дополнительно:**
```
Настройки → Приложения → VlessGram → Батарея → 
Фоновая работа → Разрешить
```

#### ASUS (ZenUI)

```
Настройки → Приложения → VlessGram → 
Батарея → Фоновая работа → Разрешить
```

### Универсальный метод (если вашей модели нет в списке)

1. Откройте **Настройки** телефона
2. Найдите раздел **Батарея**, **Питание** или **Приложения**
3. Найдите в списке **VlessGram**
4. Ищите опции: **Фоновая работа**, **Оптимизация батареи**, **Автозапуск**
5. Отключите все ограничения для приложения

### Проверка настроек

После выполнения всех настроек обязательно проверьте работоспособность:

1. **Подключите VLESS узел** — выберите любой рабочий узел из списка
2. **Сверните приложение** — нажмите кнопку «Домой» или сделайте свайп вверх
3. **Выключите экран** — оставьте телефон на 1-2 минуты
4. **Включите экран** — разблокируйте устройство
5. **Проверьте соединение** — откройте Telegram и убедитесь, что сообщения отправляются

**Если соединение пропало:**
- Вернитесь к настройкам батареи
- Убедитесь, что все разрешения предоставлены
- Проверьте, что VlessGram добавлен в исключения оптимизации
- Перезапустите приложение и повторите проверку

---

### Шаг 3: Подключиться к узлу

1. Откройте **Настройки прокси** в Telegram
2. Найдите в списке нужный узел
3. Нажмите на него для подключения
4. Дождитесь завершения проверки пинга (1-3 секунды)
5. Статус узла изменится на «Подключено»

Теперь весь трафик Telegram будет направлен через выбранный VLESS-узел.

---

### Шаг 4: Мониторинг подключения

Приложение отображает статус каждого узла в реальном времени:

| Статус | Значение | Действие |
|--------|----------|----------|
| **Зелёное значение пинга** (например, 50 мс) | Узел работает, соединение активно | Можно использовать |
| **Жёлтое значение пинга** (например, 200+ мс) | Узел работает, но задержка высокая | Используйте при отсутствии альтернатив |
| **Красное «timeout»** | Узел недоступен или не отвечает | Выберите другой узел |
| **Серый статус** | Узел не проверялся | Нажмите для проверки |

**Функция автопереключения:**
- Активируется автоматически при потере соединения с текущим узлом
- Выбирает узел с наименьшим пингом из доступных
- Переключение происходит бесшовно, без прерывания работы Telegram

---

## 🔗 Поддерживаемые форматы URI

### VLESS

Пример URI:
```
vless://uuid@хост:порт?encryption=none&security=tls&sni=example.com&fp=chrome&pbk=publickey&sid=shortid&type=ws&path=%2Fpath&host=example.com#ИмяУзла
```

**Параметры:**
- `uuid` — уникальный идентификатор пользователя
- `хост:порт` — адрес и порт сервера
- `security` — тип безопасности (tls/none)
- `sni` — имя сервера для TLS
- `type` — тип транспорта (ws/tcp/grpc)
- `path` — путь для WebSocket
- `host` — заголовок Host для WebSocket

### Trojan

Пример URI:
```
trojan://пароль@хост:порт?security=tls&sni=example.com&type=ws&path=%2Ftrojan#ИмяУзла
```

**Параметры:**
- `пароль` — пароль для аутентификации
- `хост:порт` — адрес и порт сервера
- `security` — тип безопасности (обычно tls)
- `sni` — имя сервера для TLS
- `type` — тип транспорта (ws/tcp)

### Shadowsocks

Пример URI:
```
ss://base64(пароль:шифр)@хост:порт#ИмяУзла
```

Или в новом формате:
```
ss://шифр:пароль@хост:порт#ИмяУзла
```

**Параметры:**
- `шифр` — метод шифрования (aes-256-gcm, chacha20-ietf-poly1305 и др.)
- `пароль` — пароль для подключения
- `хост:порт` — адрес и порт сервера

---

## 📋 Форматы подписок

VlessGram поддерживает три формата подписок для импорта узлов:

| Формат | Описание | Пример |
|--------|----------|--------|
| **JSON** | Массив строк URI или объектов с полем `uri` | `["vless://...", "trojan://..."]` |
| **Base64** | Base64-кодированный список URI (по одному на строку) | Закодированный текст с URI |
| **Обычный текст** | Текстовый список URI (по одному на строку) | Каждый URI с новой строки |

**Как работает импорт:**
1. Приложение загружает данные по указанному URL
2. Определяет формат автоматически
3. Парсит URI и добавляет узлы в список
4. Сохраняет подписку для последующего обновления

---

## 🏗️ Архитектура приложения

### Основные компоненты

| Компонент | Назначение |
|-----------|------------|
| **VlessConfigParser** | Парсинг URI VLESS/Trojan/Shadowsocks и преобразование в JSON-конфигурацию для libvless |
| **VlessCoreBridge** | JNI-мост между Java-кодом и нативной библиотекой libvless (вызовы Start/Stop) |
| **VlessCoreManager** | Управление извлечением .so-библиотек из APK и запуском libvless |
| **VlessRepository** | Хранение данных узлов, управление подписками, ping-мониторинг, логика автопереключения |
| **VlessSubscriptionParser** | Парсинг данных подписок в различных форматах (JSON/Base64/текст) |
| **VlessUriUtils** | Утилиты для работы с URI: валидация, извлечение хост:порт, декодирование |

### Схема работы

```
Пользователь добавляет URI
         ↓
VlessConfigParser парсит URI
         ↓
VlessRepository сохраняет узел
         ↓
Пользователь подключается к узлу
         ↓
VlessCoreManager извлекает libvless.so
         ↓
VlessCoreBridge запускает libvless через JNI
         ↓
Создаётся локальный SOCKS5 прокси
         ↓
Весь трафик Telegram идёт через прокси
```

---

## 🛠️ Руководство по сборке

### Требования к среде

| Компонент | Версия | Примечание |
|-----------|--------|------------|
| **Android Studio** | 3.4 или выше | Рекомендуется последняя стабильная версия |
| **Android NDK** | rev. 20 | Требуется для компиляции нативных библиотек |
| **Android SDK** | 8.1 (API 26) | Минимальная версия для сборки |
| **Java JDK** | 8 или выше | Обязательный компонент для Gradle |
| **Оперативная память** | 8 ГБ минимум | Рекомендуется 16 ГБ для комфортной сборки |

### Пошаговая инструкция

#### Шаг 1: Клонирование репозитория

```bash
git clone https://github.com/1ShadowWarrior1/VlessGram.git
cd VlessGram
```

#### Шаг 2: Настройка ключей подписи

Для подписи релизной версии приложения:

1. Скопируйте файл `release.keystore` в директорию `TMessagesProj/config/`
2. Откройте файл `gradle.properties` в корне проекта
3. Заполните следующие параметры:

```properties
RELEASE_KEY_PASSWORD=ваш_пароль_ключа
RELEASE_KEY_ALIAS=ваш_псевдоним_ключа
RELEASE_STORE_PASSWORD=ваш_пароль_хранилища
```

> **Примечание:** Для тестовой сборки можно использовать дебаг-ключ, который генерируется автоматически.

#### Шаг 3: Настройка Firebase

Для работы push-уведомлений и аналитики:

1. Перейдите на https://console.firebase.google.com/
2. Создайте новый проект или выберите существующий
3. Добавьте Android-приложение с package ID: `org.vlessgram.messenger`
4. Скачайте файл `google-services.json`
5. Поместите файл в директорию `TMessagesProj_App/`

Для бета-версии повторите с package ID: `org.vlessgram.messenger.beta`

#### Шаг 4: Настройка API Telegram

1. Откройте файл `TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java`
2. Замените значения на ваши:

```java
public static int APP_ID = 12345678; // Ваш API ID
public static String APP_HASH = "ваш_api_hash"; // Ваш API HASH
```

> **Важно:** Получить API-ключи можно на https://my.telegram.org/apps

#### Шаг 5: Изменение имени пакета (опционально)

Если вы хотите собрать форк с другим именем пакета:

1. Откройте `gradle.properties`
2. Измените значение `APP_PACKAGE`:

```properties
APP_PACKAGE=com.example.yourmessenger
```

3. Выполните поиск и замену `org.vlessgram.messenger` на новое имя во всём проекте

#### Шаг 6: Сборка приложения

**Через Android Studio:**
1. Откройте проект в Android Studio
2. Дождитесь завершения синхронизации Gradle
3. Выберите **Build** → **Generate Signed Bundle / APK**
4. Следуйте инструкциям мастера сборки

**Через командную строку:**
```bash
# Сборка релизной версии
./gradlew assembleRelease

# Сборка отладочной версии
./gradlew assembleDebug

# Сборка всех вариантов
./gradlew assemble
```

Готовые APK-файлы будут находиться в директории `TMessagesProj/build/outputs/apk/`

---

## 📚 Документация

| Ресурс | Описание | Ссылка |
|--------|----------|--------|
| Telegram API | Официальная документация API Telegram | https://core.telegram.org/api |
| MTproto Protocol | Спецификация протокола шифрования MTproto | https://core.telegram.org/mtproto |
| VLESS Protocol | Документация протокола VLESS | https://github.com/XTLS/Xray-core |
| Project X | Реализация протоколов VLESS/Trojan | https://github.com/XTLS |

---

## ⚖️ Лицензия

Данный проект распространяется под лицензией **GNU General Public License v2.0** или любой более поздней версией по вашему выбору.

Это означает, что вы можете:
- **Использовать** — свободно использовать приложение в личных целях
- **Изучать** — изучать исходный код и принцип работы
- **Модифицировать** — вносить изменения в код для личных нужд
- **Распространять** — делиться модифицированными версиями (с соблюдением лицензии GPL)

**Обязательства:**
- При распространении модифицированных версий необходимо предоставить исходный код
- Производные работы должны распространяться под той же лицензией

Полный текст лицензии доступен в файле [LICENSE](LICENSE).

---

## ⚠️ Отказ от ответственности

**Важная информация:**

1. **Неофициальный клиент** — VlessGram является неофициальным клиентом Telegram и не связан с Telegram FZ-LLC или Telegram Messenger Inc.

2. **Образовательные цели** — Функционал работы с протоколами VLESS/Trojan/Shadowsocks предоставляется исключительно в образовательных и исследовательских целях.

3. **Ответственность пользователя** — Пользователи несут полную ответственность за:
   - Соблюдение местных законов и нормативных актов
   - Легальность использования прокси-серверов в их юрисдикции
   - Выбор и использование прокси-провайдеров

4. **Гарантии** — Приложение предоставляется «как есть», без каких-либо явных или подразумеваемых гарантий. Авторы не несут ответственности за любые убытки или ущерб, возникшие в результате использования приложения.

5. **Рекомендация** — Перед использованием ознакомьтесь с законодательством вашей страны относительно использования средств обхода сетевых ограничений.

---

## 📞 Поддержка и связь

### Обратная связь

- **Баг-трекер:** https://github.com/1ShadowWarrior1/VlessGram/issues
- **Электронная почта:** (опционально)
- **Telegram:** @linux_ssh

### Как сообщить об ошибке

При создании issue укажите:
1. Модель устройства и версию Android
2. Версию приложения
3. Подробное описание проблемы
4. Шаги для воспроизведения
5. Скриншоты или логи (если применимо)

### Предложения по улучшению

Приветствуются pull request'ы с:
- Исправлениями ошибок
- Улучшениями производительности
- Новыми функциями
- Улучшениями документации

---

<div align="center">

**VlessGram — сделано для конфиденциальности и свободы общения**

[Начать использование](#-как-пользоваться-vless) • [Собрать самостоятельно](#-руководство-по-сборке) • [Сообщить об ошибке](https://github.com/1ShadowWarrior1/VlessGram/issues)

</div>

---

---

# 🇬🇧 English Version

## 📖 About

**VlessGram** is a modified version of the popular Telegram messenger for Android with integrated support for modern proxy protocols. Unlike the standard client, VlessGram does not require additional applications for proxy functionality — all features are built directly into the app.

The application uses `libvless` library to create a local SOCKS5 proxy through which all Telegram traffic is routed. This ensures stable connectivity even under network restrictions.

### Key Advantages

- **Full Integration** — Proxy works at the application level without requiring VPN services
- **Privacy** — All traffic is encrypted and routed through your chosen servers
- **Performance** — Minimal latency through direct routing
- **Convenience** — Node management directly from Telegram interface

---

## ✨ Features

### Supported Protocols

| Protocol | Description | Advantages |
|----------|-------------|------------|
| **VLESS** | Modern protocol based on V2Ray | High speed, low resource consumption, hard to detect |
| **Trojan** | Protocol disguised as regular HTTPS | Bypasses deep packet inspection (DPI), looks like normal web traffic |
| **Shadowsocks** | Time-tested proxy protocol | Stability, wide support, simple configuration |

### Key Features

| Feature | Description |
|---------|-------------|
| **Local SOCKS5 Proxy** | All Telegram traffic goes through libvless without external dependencies |
| **Built-in VPN Functionality** | No additional apps required for proxy operation |
| **Auto-Switch** | Automatically switches to the node with best ping when connection is lost |
| **Ping Monitoring** | Continuous real-time availability checks for all nodes |
| **Subscription Support** | Import node lists from URL subscriptions (auto-updated) |
| **Auto-Start** | Automatically connect to the last active node on app launch |
| **Manual Control** | Full freedom of choice — connect to any node with one tap |

### Node Management

- **Add Nodes** — Manual addition via URI or import from subscription
- **Subscription Formats** — Support for JSON, Base64, and plain text
- **Sorting** — Automatic sorting of nodes by ping value
- **Batch Testing** — Test ping of all nodes with one press
- **Node Status** — Visual display of each node's state (online/offline)
- **Editing** — Modify parameters of connected nodes
- **Deletion** — Clean up list from non-working nodes

---

## 📚 How to Use VLESS

### Step 1: Add a Node

#### Option A — Add Manual Key

Use this method if you have a ready-made proxy server URI key:

1. Open **Settings** in Telegram
2. Go to **Data and Storage**
3. Select **Proxy Settings**
4. Tap **➕ Add Key**
5. Paste the copied VLESS/Trojan/Shadowsocks URI into the input field
6. Tap **Save**

The key will appear in the list of available nodes.

#### Option B — Add Subscription

Use this method for automatic import of node lists:

1. Open **Settings** in Telegram
2. Go to **Data and Storage**
3. Select **Proxy Settings**
4. Tap **➕ Add Subscription**
5. Enter your subscription URL (provided by your provider)
6. Tap **Save**

All nodes from the subscription will be automatically imported and added to the list.

> **Note:** Subscriptions can be manually refreshed to get an updated node list.

---

### Step 2: Configure VLESS

After adding nodes, it's recommended to configure connection settings:

| Setting | Description | Recommendation |
|---------|-------------|----------------|
| **Auto-Start** | Automatically connect to VLESS when Telegram launches | Enable for convenience |
| **Smart Switch** | Auto-switch to best node when connection fails | Enable for stability |
| **Sort by Ping** | Sort nodes by latency (lower ping nodes appear first) | Enable for quick selection |

---

## ⚠️ Important: Disable Background Restrictions

### Why This Is Critical

Modern Android versions include an aggressive battery saving system that limits background app activity. By default, the system may:

- **Stop VLESS services** when minimizing the app or entering sleep mode
- **Disconnect VPN** when the screen is off
- **Interrupt ping checks** to servers, leading to outdated node status information
- **Terminate the app process** to free up RAM

**Result:** VLESS stops working when the phone is in your pocket, the screen is off, or the app is minimized. This makes the proxy useless when you need it most.

### How to Disable Restrictions

Settings may vary depending on device manufacturer. Find your model below:

#### Samsung (One UI)

```
Settings → Apps → VlessGram → Battery → 
Optimize battery usage → All apps (top right) → 
Find VlessGram in list → Toggle to "Off"
```

**Additionally:**
```
Settings → Device care → Battery → 
Background usage limits → Unrestricted apps → Add VlessGram
```

#### Xiaomi / Redmi / POCO (MIUI / HyperOS)

```
Settings → Apps → Manage apps → VlessGram → 
Battery saver → Background activity → Allow
```

**Critically Important (MIUI):**
```
Security → Permissions → Autostart → Find VlessGram → Enable
```

**Additionally:**
```
Settings → Additional settings → Battery & performance → 
Power saving → Apps → VlessGram → No restrictions
```

#### Huawei / Honor (EMUI / MagicOS)

```
Settings → Battery → App launch → 
Find VlessGram → Disable "Manage all automatically" → 
Enable all three options (Auto-launch, Secondary launch, Run in background)
```

**Additionally:**
```
Settings → Apps → VlessGram → Battery → 
Launch in background → Allow
```

#### OnePlus / OPPO / Realme (OxygenOS / ColorOS)

```
Settings → Apps → VlessGram → 
Advanced → Battery → 
Allow background activity
```

**Additionally:**
```
Settings → Battery → Background power consumption → 
Find VlessGram → Allow
```

#### Pixel / Motorola / Nokia (Stock Android)

```
Settings → Apps → All apps → VlessGram → 
Battery → Unrestricted
```

#### Sony (Xperia UI)

```
Settings → Power → STAMINA mode → 
Exceptions → Add VlessGram
```

**Additionally:**
```
Settings → Apps → VlessGram → Battery → 
Background activity → Allow
```

#### ASUS (ZenUI)

```
Settings → Apps → VlessGram → 
Battery → Background activity → Allow
```

### Universal Method (if your model is not listed)

1. Open phone **Settings**
2. Find **Battery**, **Power**, or **Apps** section
3. Find **VlessGram** in the list
4. Look for options: **Background activity**, **Battery optimization**, **Autostart**
5. Disable all restrictions for the app

### Verify Settings

After completing all settings, be sure to verify functionality:

1. **Connect a VLESS node** — Select any working node from the list
2. **Minimize the app** — Press the "Home" button or swipe up
3. **Turn off screen** — Leave the phone for 1-2 minutes
4. **Turn on screen** — Unlock the device
5. **Check connection** — Open Telegram and make sure messages are sending

**If connection is lost:**
- Return to battery settings
- Make sure all permissions are granted
- Check that VlessGram is added to optimization exceptions
- Restart the app and repeat the test

---

### Step 3: Connect to a Node

1. Open **Proxy Settings** in Telegram
2. Find the desired node in the list
3. Tap on it to connect
4. Wait for ping check to complete (1-3 seconds)
5. Node status will change to "Connected"

All Telegram traffic will now be routed through the selected VLESS node.

---

### Step 4: Monitor Connection

The app displays each node's status in real-time:

| Status | Meaning | Action |
|--------|---------|--------|
| **Green ping value** (e.g., 50 ms) | Node is working, connection active | Ready to use |
| **Yellow ping value** (e.g., 200+ ms) | Node is working, but latency is high | Use if no alternatives |
| **Red "timeout"** | Node is unavailable or not responding | Choose another node |
| **Gray status** | Node has not been tested | Tap to test |

**Auto-Switch Feature:**
- Activates automatically when connection to current node is lost
- Selects the node with lowest ping from available options
- Switching happens seamlessly without interrupting Telegram operation

---

## 🔗 Supported URI Formats

### VLESS

Example URI:
```
vless://uuid@host:port?encryption=none&security=tls&sni=example.com&fp=chrome&pbk=publickey&sid=shortid&type=ws&path=%2Fpath&host=example.com#NodeName
```

**Parameters:**
- `uuid` — Unique user identifier
- `host:port` — Server address and port
- `security` — Security type (tls/none)
- `sni` — Server name for TLS
- `type` — Transport type (ws/tcp/grpc)
- `path` — Path for WebSocket
- `host` — Host header for WebSocket

### Trojan

Example URI:
```
trojan://password@host:port?security=tls&sni=example.com&type=ws&path=%2Ftrojan#NodeName
```

**Parameters:**
- `password` — Authentication password
- `host:port` — Server address and port
- `security` — Security type (usually tls)
- `sni` — Server name for TLS
- `type` — Transport type (ws/tcp)

### Shadowsocks

Example URI:
```
ss://base64(password:cipher)@host:port#NodeName
```

Or in new format:
```
ss://cipher:password@host:port#NodeName
```

**Parameters:**
- `cipher` — Encryption method (aes-256-gcm, chacha20-ietf-poly1305, etc.)
- `password` — Connection password
- `host:port` — Server address and port

---

## 📋 Subscription Formats

VlessGram supports three subscription formats for node import:

| Format | Description | Example |
|--------|-------------|---------|
| **JSON** | Array of URI strings or objects with `uri` field | `["vless://...", "trojan://..."]` |
| **Base64** | Base64-encoded list of URIs (one per line) | Encoded text with URIs |
| **Plain Text** | Text list of URIs (one per line) | Each URI on a new line |

**How Import Works:**
1. App downloads data from the specified URL
2. Automatically detects the format
3. Parses URIs and adds nodes to the list
4. Saves the subscription for future updates

---

## 🏗️ Application Architecture

### Core Components

| Component | Purpose |
|-----------|---------|
| **VlessConfigParser** | Parses VLESS/Trojan/Shadowsocks URIs and converts to JSON config for libvless |
| **VlessCoreBridge** | JNI bridge between Java code and native libvless library (Start/Stop calls) |
| **VlessCoreManager** | Manages extraction of .so libraries from APK and launches libvless |
| **VlessRepository** | Node data storage, subscription management, ping monitoring, auto-switch logic |
| **VlessSubscriptionParser** | Parses subscription data in various formats (JSON/Base64/text) |
| **VlessUriUtils** | URI utilities: validation, host:port extraction, decoding |

### Workflow Diagram

```
User adds URI
         ↓
VlessConfigParser parses URI
         ↓
VlessRepository saves node
         ↓
User connects to node
         ↓
VlessCoreManager extracts libvless.so
         ↓
VlessCoreBridge starts libvless via JNI
         ↓
Local SOCKS5 proxy is created
         ↓
All Telegram traffic goes through proxy
```

---

## 🛠️ Compilation Guide

### Environment Requirements

| Component | Version | Note |
|-----------|---------|------|
| **Android Studio** | 3.4 or higher | Latest stable version recommended |
| **Android NDK** | rev. 20 | Required for native library compilation |
| **Android SDK** | 8.1 (API 26) | Minimum version for building |
| **Java JDK** | 8 or higher | Required component for Gradle |
| **RAM** | 8 GB minimum | 16 GB recommended for comfortable building |

### Step-by-Step Instructions

#### Step 1: Clone the Repository

```bash
git clone https://github.com/1ShadowWarrior1/VlessGram.git
cd VlessGram
```

#### Step 2: Set Up Signing Keys

For signing the release version:

1. Copy `release.keystore` file to `TMessagesProj/config/` directory
2. Open `gradle.properties` file in the project root
3. Fill in the following parameters:

```properties
RELEASE_KEY_PASSWORD=your_key_password
RELEASE_KEY_ALIAS=your_key_alias
RELEASE_STORE_PASSWORD=your_keystore_password
```

> **Note:** For test builds, you can use the debug key that is generated automatically.

#### Step 3: Configure Firebase

For push notifications and analytics:

1. Go to https://console.firebase.google.com/
2. Create a new project or select existing one
3. Add Android app with package ID: `org.vlessgram.messenger`
4. Download `google-services.json` file
5. Place the file in `TMessagesProj_App/` directory

For beta version, repeat with package ID: `org.vlessgram.messenger.beta`

#### Step 4: Configure Telegram API

1. Open file `TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java`
2. Replace values with yours:

```java
public static int APP_ID = 12345678; // Your API ID
public static String APP_HASH = "your_api_hash"; // Your API HASH
```

> **Important:** You can get API keys at https://my.telegram.org/apps

#### Step 5: Change Package Name (Optional)

If you want to build a fork with a different package name:

1. Open `gradle.properties`
2. Change `APP_PACKAGE` value:

```properties
APP_PACKAGE=com.example.yourmessenger
```

3. Perform find and replace for `org.vlessgram.messenger` with the new name throughout the project

#### Step 6: Build the App

**Via Android Studio:**
1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Select **Build** → **Generate Signed Bundle / APK**
4. Follow the wizard instructions

**Via Command Line:**
```bash
# Build release version
./gradlew assembleRelease

# Build debug version
./gradlew assembleDebug

# Build all variants
./gradlew assemble
```

Ready APK files will be located in `TMessagesProj/build/outputs/apk/`

---

## 📚 Documentation

| Resource | Description | Link |
|----------|-------------|------|
| Telegram API | Official Telegram API documentation | https://core.telegram.org/api |
| MTproto Protocol | MTproto encryption protocol specification | https://core.telegram.org/mtproto |
| VLESS Protocol | VLESS protocol documentation | https://github.com/XTLS/Xray-core |
| Project X | VLESS/Trojan protocol implementation | https://github.com/XTLS |

---

## ⚖️ License

This project is distributed under the **GNU General Public License v2.0** or any later version at your option.

This means you can:
- **Use** — Freely use the application for personal purposes
- **Study** — Study the source code and how it works
- **Modify** — Make changes to the code for personal needs
- **Distribute** — Share modified versions (in compliance with GPL license)

**Obligations:**
- When distributing modified versions, you must provide the source code
- Derivative works must be distributed under the same license

Full license text is available in the [LICENSE](LICENSE) file.

---

## ⚠️ Disclaimer

**Important Information:**

1. **Unofficial Client** — VlessGram is an unofficial Telegram client and is not affiliated with Telegram FZ-LLC or Telegram Messenger Inc.

2. **Educational Purposes** — The VLESS/Trojan/Shadowsocks functionality is provided exclusively for educational and research purposes.

3. **User Responsibility** — Users bear full responsibility for:
   - Compliance with local laws and regulations
   - Legality of using proxy servers in their jurisdiction
   - Selection and use of proxy providers

4. **Warranties** — The application is provided "as is", without any express or implied warranties. The authors are not liable for any losses or damages arising from the use of the application.

5. **Recommendation** — Before use, familiarize yourself with the legislation of your country regarding the use of network bypass tools.

---

## 📞 Support and Contact

### Feedback

- **Bug Tracker:** https://github.com/1ShadowWarrior1/VlessGram/issues
- **Email:** (optional)
- **Telegram:** @linux_ssh

### How to Report a Bug

When creating an issue, please include:
1. Device model and Android version
2. App version
3. Detailed problem description
4. Steps to reproduce
5. Screenshots or logs (if applicable)

### Feature Suggestions

Pull requests are welcome for:
- Bug fixes
- Performance improvements
- New features
- Documentation improvements

---

<div align="center">

**VlessGram — Built for Privacy and Freedom of Communication**

[Get Started](#-how-to-use-vless) • [Build Yourself](#-compilation-guide) • [Report a Bug](https://github.com/1ShadowWarrior1/VlessGram/issues)

</div>
