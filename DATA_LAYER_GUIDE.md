# PBL Data Layer Architecture

## Overview
The data layer follows a clean architecture pattern with:
- **Repositories** - Direct Firebase integration
- **ViewModels** - State management & business logic
- **Models** - Data classes
- **State Management** - Using StateFlow for reactive data

---

## 1. Models (`data/model/`)

### `userData.kt`
```kotlin
data class userData(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val alias: String = "",
    val email: String = "",
    val role: String = "member",
    val status: userstatus = userstatus.OFFLINE
)

enum class userstatus {
    OFFLINE, ONLINE, STANDBY
}
```

---

## 2. Repositories (`data/repository/`)

### `authRepo.kt`
Authentication operations:
- `register(email, password)` - Create new user
- `login(email, password)` - Sign in user
- `logout()` - Sign out user
- `getCurrentUser()` - Get current authenticated user
- `resetPassword(email)` - Send password reset email
- `changePassword(newPassword)` - Update user password
- `updateEmail(newEmail)` - Update user email
- `isUserLoggedIn()` - Check if user is authenticated

### `userRepo.kt`
User data operations:
- `saveUser(user)` - Create or save user document
- `getUser(id)` - Fetch single user
- `updateUser(id, updates)` - Update user fields
- `deleteUser(id)` - Delete user document
- `getAllUsers()` - Fetch all users

All methods return `Result<T>` for proper error handling.

---

## 3. ViewModels (`data/viewmodel/`)

### `AuthViewModel.kt`
State management for authentication:

**State:**
```kotlin
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val message: String) : AuthState()
}
```

**Methods:**
- `register(email, password)` - Calls repo.register()
- `login(email, password)` - Calls repo.login()
- `logout()` - Calls repo.logout()
- `resetAuthState()` - Reset state to Idle

**Exposed Flows:**
- `authState: StateFlow<AuthState>` - Current auth state
- `currentUser: StateFlow<FirebaseUser?>` - Current logged-in user

### `UserViewModel.kt`
State management for user data:

**States:**
```kotlin
sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: userData) : UserState()
    data class Error(val message: String) : UserState()
}

sealed class UserActionState {
    object Idle : UserActionState()
    object Loading : UserActionState()
    object Success : UserActionState()
    data class Error(val message: String) : UserActionState()
}
```

**Methods:**
- `getUser(id)` - Fetch user by ID
- `saveUser(user)` - Save/create user
- `resetActionState()` - Reset action state

**Exposed Flows:**
- `userState: StateFlow<UserState>` - Current user data state
- `actionState: StateFlow<UserActionState>` - Action result state

---

## 4. Usage Pattern in UI

### Example: Login Screen
```kotlin
@Composable
fun LoginScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.authState.collectAsState()
    
    // Handle state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> navController.navigate("home")
            is AuthState.Error -> showError((authState as AuthState.Error).message)
            else -> {}
        }
    }
    
    // On login button click
    authViewModel.login(email, password)
}
```

### Example: User Profile Screen
```kotlin
@Composable
fun ProfileScreen(userId: String) {
    val userViewModel: UserViewModel = viewModel()
    val userState by userViewModel.userState.collectAsState()
    
    LaunchedEffect(Unit) {
        userViewModel.getUser(userId)
    }
    
    when (userState) {
        is UserState.Loading -> LoadingIndicator()
        is UserState.Success -> UserProfile((userState as UserState.Success).user)
        is UserState.Error -> ErrorMessage((userState as UserState.Error).message)
        else -> {}
    }
}
```

---

## 5. Dependencies Added

```gradle
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
```

---

## 6. Error Handling Strategy

All repository methods return `Result<T>` which provides:
- `onSuccess(block)` - Handle successful result
- `onFailure(block)` - Handle error with exception
- Direct access to throwable for logging

ViewModels convert Result to State objects for UI consumption.

---

## 7. Next Steps

1. ✅ Fix UI component errors (homepage.kt, signuppage.kt, etc.)
2. ✅ Integrate ViewModels with UI screens
3. ✅ Add form validation in login/signup screens
4. ✅ Implement error dialogs/toasts
5. ✅ Add loading indicators during API calls
6. ⭕ Add SharedPreferences for caching user data (optional)
7. ⭕ Add Dependency Injection (Hilt) for better testability

---

## 8. Testing the Data Layer

You can test repositories directly without UI:
```kotlin
// In a CoroutineScope
val authRepo = authRepo()
val result = authRepo.login("test@example.com", "password123")
result.onSuccess { user -> println("Logged in: ${user.email}") }
result.onFailure { error -> println("Login failed: ${error.message}") }
```

