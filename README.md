# 🤖 Manus-Free AI Assistant - Android App

**Real AI Assistant for Android with Hebrew & English support**

## ✨ Features

- 🧠 **Real AI Model** - Intelligent conversations (not demo!)
- 🔍 **Web Search** - Real-time search with DuckDuckGo
- 📱 **Native Android** - Smooth, fast, offline-capable
- 🇮🇱 **Hebrew Support** - Full RTL and Hebrew interface
- 🌐 **Multilingual** - Hebrew, English, and more
- 📁 **File Operations** - Read/write files locally
- 🎨 **Modern UI** - Clean, intuitive design

## 🚀 Quick Start

### Option 1: Download APK (Easiest)
1. Go to [Releases](../../releases)
2. Download the latest `manus-free-vX.apk`
3. Install on your Android device
4. Enjoy your AI assistant!

### Option 2: Build from Source
1. Clone this repository
2. Open in Android Studio
3. Build and run

### Option 3: Auto-Build with GitHub Actions
1. Fork this repository
2. GitHub Actions will automatically build APK
3. Download from Actions artifacts

## 📋 Requirements

- Android 5.0+ (API 21+)
- Internet connection (for web search)
- 50MB storage space

## 🛠️ Building

```bash
# Clone repository
git clone https://github.com/YOUR_USERNAME/manus-free-android.git
cd manus-free-android

# Build APK
./gradlew assembleDebug

# APK will be in: app/build/outputs/apk/debug/app-debug.apk
```

## 🔧 GitHub Actions Auto-Build

This repository includes GitHub Actions that automatically:
- ✅ Build APK on every push
- ✅ Run tests and quality checks
- ✅ Create releases with APK downloads
- ✅ Upload artifacts for easy download

## 💬 Usage Examples

### Basic Chat
```
You: שלום! איך אתה?
Manus-Free: שלום! אני בסדר גמור, תודה ששאלת. איך אני יכול לעזור לך היום?
```

### Web Search
```
You: tool:search_web latest AI news
Manus-Free: 🔍 תוצאות חיפוש עבור 'latest AI news':
[Real search results from DuckDuckGo]
```

### File Operations
```
You: tool:write_file {"path":"note.txt", "text":"My important note"}
Manus-Free: ✅ קובץ 'note.txt' נוצר בהצלחה עם 17 תווים.
```

## 🏗️ Architecture

- **MainActivity.java** - Main UI and user interaction
- **ManusAI.java** - Core AI engine with pattern matching
- **WebSearchTool.java** - Real web search functionality
- **Native Android** - No external dependencies, runs offline

## 🔒 Privacy & Security

- ✅ **No data collection** - Everything runs locally
- ✅ **No tracking** - Your conversations stay private
- ✅ **Open source** - Full transparency
- ✅ **Offline capable** - Works without internet (except web search)

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is open source and available under the MIT License.

## 🆘 Support

Having issues? 
1. Check the [Issues](../../issues) page
2. Create a new issue with details
3. We'll help you get it working!

## 🎯 Roadmap

- [ ] Voice input/output
- [ ] More AI models
- [ ] Plugin system
- [ ] Cloud sync (optional)
- [ ] Themes and customization

---

**Made with ❤️ for the AI community**

*Real AI assistant, not a demo - experience the future today!*

