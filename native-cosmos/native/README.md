# Wordmos — Native Android Foundation

This is the first native Android foundation for Wordmos, designed to replace the web/TWA implementation over time.

## Stack
- Kotlin
- Jetpack Compose
- Room / SQLite
- Coroutines + Flow
- Offline-first local data model

## Current milestone
- Native Android application shell
- Local Room database
- Documents/portraits
- Hierarchical nodes using `parentId`
- Basic tree rendering
- Indentation-first importer engine
- Citation cleanup for `[[68]]`-style markers

## Architecture goal
The web app remains untouched. The native app is being rebuilt around a durable local data model so Import, Edit Text, Move Into, Search, Backup/Restore, and media can share the same underlying tree.

## Opening
Open this folder in Android Studio. Let Gradle sync and install on an Android 8.0+ device/emulator.
