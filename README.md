# Manga/Novel Reader App

## Overview

This app provides a comprehensive manga/novel reading experience with features like browsing, bookmarking, history tracking, and settings customization. It follows a Clean Architecture and MVVM pattern with Dagger Hilt and Jetpack Compose.

## Main Features

- **Bottom Navigation:** 
  - Home
  - History
  - Update
  - Library
  - Settings

- **Toolbar:**
  - Back Button
  - Filter
  - Search Text Field

## Screens and Functionalities

### Home
- **Body:** Content dependent on selected source.
- **Source Selector:** Allows switching between different sources.

### History
- **List View:** Displays read book divided by date.

### Update
- **List View:** Shows new chapters of books in favorites divided by date.

### Library
- **Default Tab:** Displays books in the library.
  - **Long Click:** Enables checkbox selection for tab, wish-fulfilling, healing categories.

### Settings
- **Tab Setting Options:** Provides various settings to customize the app.

### Favorite
- **List View:** Displays favorite books.
  - **Item Click:** Navigates to book detail.

### Book Detail
- **Details:**
  - Book Title
  - Large Cover URL
  - Author
  - Alternative Title
  - Status
  - Genre
  - Description
  - List of Chapters
  - Category

### Chapter
- **Details:**
  - Title
  - Date
  - URL

### History Book
- **Details:**
  - Book
  - Last Read Chapter

### Favorite Book
- **Details:**
  - Book Detail
  - Created At

### Book
- **Details:**
  - Cover URL
  - Title
  - URL
  - Latest Chapter (Optional)
  - Book Source

### Book Source
- **Details:**
  - Name
  - URL
  - Status
  - Type

### Reader
- **Depending on Type:**
  - Manga -> RecyclerView
  - Text
  - Chapter List

### Migration
- **Migration Tool:** 

## Architecture
This project is structured using the Clean Architecture approach with MVVM pattern and Dagger Hilt for dependency injection.

## Contributing
Feel free to contribute to this project by opening issues or submitting pull requests.

## License
This project is licensed under the MIT License.
