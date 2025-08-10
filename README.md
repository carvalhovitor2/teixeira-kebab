# Teixeira Kebab - Order Management App

A simple Android order management system for a kebab store built with modern Android development technologies.

## Features

- **Orders List**: View all orders in reverse chronological order
- **New Order Creation**: Add customer name and select items from menu
- **Order Details**: View complete order information with line items
- **Order Management**: Delete orders from list or details view
- **Static Menu**: Pre-defined menu items with categories and sizes

## Technology Stack

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit (no XML layouts)
- **MVVM Architecture**: Model-View-ViewModel pattern
- **Navigation Compose**: Type-safe navigation
- **Room Database**: Local SQLite database with KSP
- **Hilt**: Dependency injection
- **Material 3**: Modern design system

## Menu Items

### Food Items
- **Kebab**: Small (R$ 15,00) / Big (R$ 25,00)
- **Durum**: Small (R$ 20,00) / Big (R$ 30,00)

### Drinks
- **Soda**: R$ 10,00
- **Water**: R$ 12,00
- **Beer**: R$ 18,00

## Project Structure

```
app/src/main/java/com/example/teixeirakebab/
├── data/
│   ├── MenuItem.kt                 # Menu item data class
│   ├── MenuRepository.kt           # Static menu repository
│   ├── CurrencyFormatter.kt        # Currency formatting utilities
│   ├── entity/                     # Room database entities
│   ├── dao/                        # Data Access Objects
│   ├── database/                   # Room database setup
│   └── repository/                 # Repository layer
├── ui/
│   ├── model/                      # UI state models
│   ├── viewmodel/                  # ViewModels
│   ├── screen/                     # Compose screens
│   ├── navigation/                 # Navigation setup
│   └── theme/                      # Material 3 theme
└── di/                            # Dependency injection modules
```

## Architecture

### MVVM Pattern
- **Model**: Data classes, entities, and repositories
- **View**: Compose UI components
- **ViewModel**: Business logic and state management

### Database Schema
- **OrderEntity**: Orders with customer name, total, and timestamp
- **OrderItemEntity**: Individual items within orders
- **OrderWithItems**: Relationship class for queries

### Key Components
- **StateFlow**: Reactive state management
- **Coroutines**: Asynchronous operations
- **Room Transactions**: Atomic database operations
- **Hilt Modules**: Dependency injection

## Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 36 (Android 14)

### Building the Project
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on emulator or device

### Adding New Menu Items
To add new menu items, modify the `MenuRepository.kt` file:

```kotlin
private val menuItems = listOf(
    // Add new items here
    MenuItem("new_item_id", "Item Name", MenuItem.Category.FOOD, MenuItem.Size.BIG, 2500),
    // ...
)
```

### Price Guidelines
- All prices must be between 1000 (R$ 10,00) and 4000 (R$ 40,00) cents
- Store prices as integer cents to avoid floating-point precision issues

## Testing

The project includes unit tests for the currency formatter:

```bash
./gradlew test
```

## Dependencies

### Core Dependencies
- **Compose BOM**: 2024.10.00
- **Room**: 2.6.1
- **Hilt**: 2.50
- **Navigation Compose**: 2.9.3
- **Lifecycle ViewModel Compose**: 2.9.2

### Build Tools
- **KSP**: 2.0.21-1.0.27 (for Room code generation)
- **Kotlin**: 2.0.21
- **Android Gradle Plugin**: 8.12.0

## Features Not Included

As per requirements, the following features are intentionally excluded:
- Payment processing
- User authentication
- Remote APIs
- Network connectivity
- User registration/login

## Future Enhancements

Potential areas for expansion:
- Order status tracking
- Inventory management
- Customer loyalty system
- Sales analytics
- Receipt printing
- Multi-language support

## License

This project is for educational and demonstration purposes. 