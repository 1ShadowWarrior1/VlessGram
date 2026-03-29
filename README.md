# VlessGram — Telegram для Android с поддержкой VLESS/VPN

<div align="center">

[![License: GPL v2](https://img.shields.io/badge/License-GPL%20v2-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![GitHub Release](https://img.shields.io/github/v/release/1ShadowWarrior1/VlessGram)](https://github.com/1ShadowWarrior1/VlessGram/releases)

**VlessGram** — форк Telegram для Android со встроенной поддержкой протоколов **VLESS**, **Trojan** и **Shadowsocks**

[English Version Below ⬇️](#-english-version)

</div>

---

# 🇷🇺 Русская версия

## О проекте

**VlessGram** — модифицированная версия Telegram для Android с встроенной поддержкой прокси-протоколов. Приложение использует библиотеку `libvless` для создания локального SOCKS5-прокси, через который проходит весь трафик Telegram.

---

## Возможности

### Поддерживаемые протоколы

- **VLESS** — современный протокол обхода блокировок
- **Trojan** — маскировка под обычный HTTPS-трафик
- **Shadowsocks** — проверенный протокол проксирования

### Ключевые функции

| Функция | Описание |
|---------|----------|
| Локальный SOCKS5 прокси | Весь трафик Telegram идёт через libvless |
| Без внешних приложений | Встроенная VPN-функциональность |
| Автопереключение | Переключение на лучший узел при потере соединения |
| Ping-мониторинг | Проверка пинга всех узлов в реальном времени |
| Поддержка подписок | Импорт подписок с узлами по URL |
| Автозапуск | Автоматическое подключение при запуске приложения |

### Управление узлами

- Ручное добавление узлов через URI
- Импорт подписок (JSON, Base64, обычный текст)
- Сортировка узлов по пингу
- Проверка пинга всех узлов одним кликом
- Отображение статуса каждого узла

---

## Как пользоваться VLESS

### Шаг 1: Добавить узел

**Вариант A — Добавить ключ вручную:**

1. Откройте **Настройки** → **Данные и память** → **Настройки прокси**
2. Нажмите **➕ Добавить ключ**
3. Вставьте ваш URI VLESS/Trojan/Shadowsocks
4. Нажмите **Сохранить**

**Вариант B — Добавить подписку:**

1. Откройте **Настройки** → **Данные и память** → **Настройки прокси**
2. Нажмите **➕ Добавить подписку**
3. Введите URL вашей подписки
4. Нажмите **Сохранить**
5. Узлы будут автоматически импортированы

---

### Шаг 2: Настроить VLESS

| Настройка | Описание |
|-----------|----------|
| Автовключение | Автоматически подключаться к VLESS при запуске Telegram |
| Умное переключение | Автопереключение на лучший узел при сбое соединения |
| Сортировка по пингу | Сортировать узлы по задержке (меньший первый) |

---

## ⚠️ Важно: Отключение ограничений работы в фоне

Android по умолчанию ограничивает работу приложений в фоне для экономии батареи. Это может приводить к отключению VLESS при сворачивании приложения или выключенном экране.

### Почему это важно

Без proper настроек Android может:
- Останавливать службу VLESS при сворачивании приложения
- Отключать VPN при выключенном экране
- Прерывать проверку пинга серверов в фоне

### Как отключить ограничения (универсальный способ)

**Способ 1 — Через настройки приложения:**

1. Откройте **Настройки** телефона
2. Перейдите в **Приложения** → **VlessGram**
3. Нажмите **Батарея** или **Питание**
4. Выберите **Не ограничивать** или **Без ограничений**

**Способ 2 — При первом запуске:**

1. При первом подключении VLESS приложение запросит разрешение
2. Нажмите **Разрешить** в диалоге оптимизации батареи
3. Это автоматически настроит нужные разрешения

### Инструкции для популярных производителей

#### Samsung

```
Настройки → Приложения → VlessGram → Батарея → Оптимизация батареи → 
Все приложения (вверху) → Найти VlessGram → Отключить
```

#### Xiaomi / MIUI

```
Настройки → Приложения → VlessGram → Батарея → 
Фоновая работа → Разрешить
```

Дополнительно в MIUI:
```
Безопасность → Разрешения → Автозапуск → Включить для VlessGram
```

#### Huawei / EMUI

```
Настройки → Батарея → Запуск приложений → 
Найти VlessGram → Включить автозапуск и вторичный запуск
```

#### OnePlus / OxygenOS

```
Настройки → Приложения → VlessGram → 
Расширенные настройки → Батарея → Разрешить фоновую активность
```

#### Pixel / Stock Android

```
Настройки → Приложения → VlessGram → Батарея → 
Не ограничивать
```

### Проверка настроек

После настройки:
1. Подключите VLESS узел
2. Сверните приложение
3. Выключите экран на 1-2 минуты
4. Включите экран и проверьте — соединение должно сохраниться

---

### Шаг 3: Подключиться

1. Нажмите на любой узел в списке
2. Дождитесь проверки пинга
3. Статус узла покажет состояние подключения
4. Трафик Telegram будет направлен через VLESS

---

### Шаг 4: Мониторинг подключения

| Статус | Значение |
|--------|----------|
| Зелёное значение пинга | Узел работает |
| Красное "timeout" | Узел недоступен |
| Автопереключение | Активируется при сбое текущего узла |

---

## Поддерживаемые форматы URI

### VLESS
```
vless://uuid@хост:порт?encryption=none&security=tls&sni=example.com&fp=chrome&pbk=publickey&sid=shortid&type=ws&path=%2Fpath&host=example.com#ИмяУзла
```

### Trojan
```
trojan://пароль@хост:порт?security=tls&sni=example.com&type=ws&path=%2Ftrojan#ИмяУзла
```

### Shadowsocks
```
ss://base64(пароль:шифр)@хост:порт#ИмяУзла
```

---

## Форматы подписок

| Формат | Описание |
|--------|----------|
| JSON | Массив URI или объектов с полем `uri` |
| Base64 | Base64-кодированный список URI (по одному на строку) |
| Обычный текст | Текстовый список URI (по одному на строку) |

---

## Архитектура

| Компонент | Описание |
|-----------|----------|
| VlessConfigParser | Парсит URI VLESS/Trojan/Shadowsocks в JSON-конфигурацию |
| VlessCoreBridge | JNI-мост для libvless (запуск/остановка) |
| VlessCoreManager | Управление извлечением и запуском libvless.so |
| VlessRepository | Хранение узлов, подписки, ping-мониторинг, автопереключение |
| VlessSubscriptionParser | Парсинг данных подписок (JSON/Base64/текст) |
| VlessUriUtils | Утилиты URI для извлечения хост:порт |

---

## Руководство по сборке

### Требования

- Android Studio 3.4 или выше
- Android NDK rev. 20
- Android SDK 8.1

### Шаги сборки

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/1ShadowWarrior1/VlessGram.git
   cd VlessGram
   ```

2. **Настройте ключи подписи:**
   - Скопируйте `release.keystore` в `TMessagesProj/config/`
   - Обновите `gradle.properties`:
     ```properties
     RELEASE_KEY_PASSWORD=ваш_пароль
     RELEASE_KEY_ALIAS=ваш_псевдоним
     RELEASE_STORE_PASSWORD=ваш_пароль
     ```

3. **Настройте Firebase:**
   - Перейдите на https://console.firebase.google.com/
   - Создайте приложения с ID: `org.vlessgram.messenger`, `org.vlessgram.messenger.beta`
   - Скачайте `google-services.json` в `TMessagesProj_App/`

4. **Обновите учётные данные API:**
   - Отредактируйте `TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java`
   - Установите ваши `APP_ID` и `APP_HASH`

5. **Измените имя пакета (опционально):**
   - Обновите `APP_PACKAGE` в `gradle.properties`

6. **Сборка:**
   - Откройте проект в Android Studio
   - Запустите `./gradlew assembleRelease`

---

## Документация

| Ресурс | Ссылка |
|--------|--------|
| Telegram API | https://core.telegram.org/api |
| Протокол MTproto | https://core.telegram.org/mtproto |

---

## Лицензия

Данный проект лицензирован под **GNU General Public License v2.0** или более поздней версией.

Подробности см. в файле [LICENSE](LICENSE).

---

## Отказ от ответственности

VlessGram — неофициальный клиент Telegram. Это приложение не связано с Telegram FZ-LLC. Функционал VLESS предоставляется только в образовательных целях. Пользователи несут ответственность за соблюдение местных законов и нормативных актов.

---

## Поддержка

- **Issues:** https://github.com/1ShadowWarrior1/VlessGram/issues
- **Telegram:** @linux_ssh

---

<div align="center">

**Сделано для конфиденциальности и свободы**

</div>

---

---

# 🇬🇧 English Version

## About

**VlessGram** is a modified fork of Telegram for Android with built-in support for proxy protocols. The app uses `libvless` library to create a local SOCKS5 proxy through which all Telegram traffic is routed.

---

## Features

### Supported Protocols

- **VLESS** — Modern bypass protocol
- **Trojan** — Disguised as regular HTTPS traffic
- **Shadowsocks** — Proven proxy protocol

### Key Features

| Feature | Description |
|---------|-------------|
| Local SOCKS5 Proxy | All Telegram traffic goes through libvless |
| No External Apps | Built-in VPN functionality |
| Auto-Switch | Switches to best node when connection is lost |
| Ping Monitoring | Real-time ping checks for all nodes |
| Subscription Support | Import node subscriptions via URL |
| Auto-Start | Automatically connect on app launch |

### Node Management

- Manual node addition via URI
- Subscription import (JSON, Base64, Plain Text)
- Sort nodes by ping
- One-click ping test for all nodes
- Individual node status display

---

## How to Use VLESS

### Step 1: Add a Node

**Option A — Add Manual Key:**

1. Open **Settings** → **Data and Storage** → **Proxy Settings**
2. Tap **➕ Add Key**
3. Paste your VLESS/Trojan/Shadowsocks URI
4. Tap **Save**

**Option B — Add Subscription:**

1. Open **Settings** → **Data and Storage** → **Proxy Settings**
2. Tap **➕ Add Subscription**
3. Enter your subscription URL
4. Tap **Save**
5. Nodes will be automatically imported

---

### Step 2: Configure VLESS

| Setting | Description |
|---------|-------------|
| Auto-Start | Automatically connect to VLESS when Telegram starts |
| Smart Switch | Auto-switch to best node when connection fails |
| Sort by Ping | Sort nodes by latency (lowest first) |

---

## ⚠️ Important: Disable Background Restrictions

Android limits background app activity by default to save battery. This can cause VLESS to disconnect when minimizing the app or when the screen is off.

### Why This Matters

Without proper settings, Android may:
- Stop VLESS service when app is minimized
- Disconnect VPN when screen is off
- Interrupt background server ping checks

### How to Disable Restrictions (Universal Method)

**Method 1 — Through App Settings:**

1. Open phone **Settings**
2. Go to **Apps** → **VlessGram**
3. Tap **Battery** or **Power**
4. Select **Unrestricted** or **Unlimited**

**Method 2 — On First Launch:**

1. On first VLESS connection, the app will request permission
2. Tap **Allow** in the battery optimization dialog
3. This automatically configures required permissions

### Instructions for Popular Manufacturers

#### Samsung

```
Settings → Apps → VlessGram → Battery → Optimize battery usage → 
All apps (top) → Find VlessGram → Turn off
```

#### Xiaomi / MIUI

```
Settings → Apps → VlessGram → Battery → 
Background activity → Allow
```

Additionally in MIUI:
```
Security → Permissions → Autostart → Enable for VlessGram
```

#### Huawei / EMUI

```
Settings → Battery → App launch → 
Find VlessGram → Enable auto-launch and secondary launch
```

#### OnePlus / OxygenOS

```
Settings → Apps → VlessGram → 
Advanced → Battery → Allow background activity
```

#### Pixel / Stock Android

```
Settings → Apps → VlessGram → Battery → 
Unrestricted
```

### Verify Settings

After configuration:
1. Connect a VLESS node
2. Minimize the app
3. Turn off screen for 1-2 minutes
4. Turn on screen and check — connection should persist

---

### Step 3: Connect

1. Tap on any node in the list
2. Wait for ping check to complete
3. Node status will show connection state
4. Telegram traffic will be routed through VLESS

---

### Step 4: Monitor Connection

| Status | Meaning |
|--------|---------|
| Green ping value | Node is working |
| Red "timeout" | Node is unavailable |
| Auto-Switch | Will activate if current node fails |

---

## Supported URI Formats

### VLESS
```
vless://uuid@host:port?encryption=none&security=tls&sni=example.com&fp=chrome&pbk=publickey&sid=shortid&type=ws&path=%2Fpath&host=example.com#NodeName
```

### Trojan
```
trojan://password@host:port?security=tls&sni=example.com&type=ws&path=%2Ftrojan#NodeName
```

### Shadowsocks
```
ss://base64(password:cipher)@host:port#NodeName
```

---

## Subscription Formats

| Format | Description |
|--------|-------------|
| JSON | Array of URIs or objects with `uri` field |
| Base64 | Base64-encoded list of URIs (one per line) |
| Plain Text | Plain text list of URIs (one per line) |

---

## Architecture

| Component | Description |
|-----------|-------------|
| VlessConfigParser | Parses VLESS/Trojan/Shadowsocks URIs to JSON config |
| VlessCoreBridge | JNI bridge for libvless (Start/Stop) |
| VlessCoreManager | Manages libvless.so extraction and startup |
| VlessRepository | Node storage, subscriptions, ping monitoring, auto-switch |
| VlessSubscriptionParser | Parses subscription data (JSON/Base64/Plain) |
| VlessUriUtils | URI utilities for host:port extraction |

---

## Compilation Guide

### Requirements

- Android Studio 3.4 or higher
- Android NDK rev. 20
- Android SDK 8.1

### Build Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/1ShadowWarrior1/VlessGram.git
   cd VlessGram
   ```

2. **Set up signing keys:**
   - Copy your `release.keystore` to `TMessagesProj/config/`
   - Update `gradle.properties`:
     ```properties
     RELEASE_KEY_PASSWORD=your_password
     RELEASE_KEY_ALIAS=your_alias
     RELEASE_STORE_PASSWORD=your_password
     ```

3. **Configure Firebase:**
   - Go to https://console.firebase.google.com/
   - Create apps with IDs: `org.vlessgram.messenger`, `org.vlessgram.messenger.beta`
   - Download `google-services.json` to `TMessagesProj_App/`

4. **Update API credentials:**
   - Edit `TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java`
   - Set your `APP_ID` and `APP_HASH`

5. **Change package name (optional):**
   - Update `APP_PACKAGE` in `gradle.properties`

6. **Build:**
   - Open project in Android Studio
   - Run `./gradlew assembleRelease`

---

## Documentation

| Resource | Link |
|----------|------|
| Telegram API | https://core.telegram.org/api |
| MTproto Protocol | https://core.telegram.org/mtproto |

---

## License

This project is licensed under the **GNU General Public License v2.0** or later.

See [LICENSE](LICENSE) for details.

---

## Disclaimer

VlessGram is an unofficial Telegram client. This application is not affiliated with Telegram FZ-LLC. VLESS functionality is provided for educational purposes only. Users are responsible for compliance with local laws and regulations.

---

## Support

- **Issues:** https://github.com/1ShadowWarrior1/VlessGram/issues
- **Telegram:** @linux_ssh

---

<div align="center">

**Built for privacy and freedom**

</div>
