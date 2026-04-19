package com.example.pbl.ui.theme

// for Authentication Input Fields
const val userName = "Username"
const val userPassword = "Password"
const val login = "Login"

// for basic error catch
const val errorInputBlank = "Input cannot be empty"
const val errorNotAlphanumeric = "Input can only contain letters and numbers"
const val errorNotNumeric = "Input can only contain numbers"
fun errorInputTooLong(
    maxLength: Int
) = "Input is too long (max $maxLength characters)"

// for signup Hint (the little texts beneath the textbox)
const val signUpUsernameHint = "Username must be 3-20 characters, no spaces"
const val signUpEmailHint = "Enter a valid email address"
const val signUpFirstNameHint = "Enter your first name"
const val signUpFirstNameError = "First name is required"
const val signUpLastNameHint = "Enter your last name"
const val signUpLastNameError = "Last name is required"
const val signUpPasswordHint1 = "Password must be 10+ characters"
const val signUpPasswordHint2 = "Password must include uppercase"
const val signUpPasswordHint3 = "Password must include number"
const val signUpPasswordHint4 = "Password must include special character"

// if the signup hint is not fulfilled
const val errorSignUpUsername1 = "Username too short"
const val errorSignUpUsername2 = "Username too long"
const val errorSignUpUsername3 = "Username cannot contain spaces"
const val errorSignUpEmail = "Invalid email format"
const val errorSignUpPassword1 = "Password is too short"
const val errorSignUpPassword2 = "Password MUST include uppercase"
const val errorSignUpPassword3 = "Password MUST include number"
const val errorSignUpPassword4 = "Password MUST include special character"
const val errorInvalidPassword = "Password must be 10+ chars, include uppercase, number & special character"

// for login error messages
const val errorLoginEmail = "No user found with this email"
const val errorLoginPassword = "Incorrect password"
const val errorLoginInvalidEmail = "Invalid email format"

// for SignIn Page
const val signIn = "Sign In"
const val signInSubtitle = "Welcome back!"
const val signInButton = "Sign In"
const val signInForgotPassword = "Forgot password?"
const val signInNoAccount = "Don't have an account? Sign Up"

// for SignUp Page
const val signUp = "Sign Up"
const val signUpTitle = "Create Account"
const val signUpSubtitle = "Your journey begins here!"
const val signUpEmail = "Email"
const val signUpFirstName = "First Name"
const val signUpLastName = "Last Name"
const val signUpAlias = "Alias"
const val signUpButton = "Sign Up"
const val signUpHaveAccount = "Already have an account? Sign In"
const val signUpTerms = "I agree to the Terms of Service"

// for Home Page
const val home = "Home"
const val homeTitle = "Home"
const val homeSubtitle = "Your dashboard"
const val welcome = "Welcome to the Home Screen!"
const val homeEmptyState = "No items yet"
const val logoutButton = "Logout"

// for Landing Page
const val titleDrop = "title"
const val subtitleDrop = "subtitle"
const val landingPagebtn1 = "Learn more!"
const val landingPagebtn2 = "Skip to Login"
const val landingPage1 = "put smth smth here"
const val landingpagesub1 = "put smth smth here1"
const val landingPage2 = "put smth smth here2"
const val landingpagesub2 = "put smth smth here2"
const val landingPage3 = "put smth smth here3"
const val landingpagesub3 = "put smth smth here3"
const val landingPage4 = "put smth smth here4"
const val landingpagesub4 = "put smth smth here4"