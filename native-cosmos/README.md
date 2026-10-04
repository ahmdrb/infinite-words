# Wordmos native Android — phone-only build

This package is designed for developing Wordmos from an Android phone using GitHub Codespaces and GitHub Actions. No desktop Android Studio is required.

## Repository layout

- `native/` — Kotlin + Jetpack Compose + Room Android app scaffold.
- `.github/workflows/android-debug.yml` — cloud APK build workflow.

## Build flow

1. Put these files into `ahmdrb/infinite-words`.
2. Open the repository in GitHub Codespaces from a browser.
3. Commit/push the files.
4. Open GitHub Actions → **Build Wordmos Android APK** → **Run workflow**.
5. When it finishes, open the workflow run and download the `wordmos-debug-apk` artifact.
6. Install the APK on the Android phone.

The workflow uses Java 17 and Gradle 8.9 and produces a debug APK. The app's data layer uses Room/SQLite, so the native app is designed to be local-first.
