package com.example.pbl.ui.theme

// for Authentication Input Fields
const val userName = "Username"
const val password = "Password"
const val login = "Login"

// for basic error catch
const val errorInputBlank = "Input cannot be empty"
const val errorNotAlphanumeric = "Input can only contain letters and numbers"
const val errorNotNumeric = "Input can only contain numbers"
fun errorInputTooLong(
    maxLength: Int
) = "Input is too long (max $maxLength characters)"

// for register Hint (the little texts beneath the textbox)
const val registerUsernameHint = "Username must be 3-20 characters, no spaces"
const val registerEmailHint = "Enter a valid email address"
const val registerPasswordHint1 = "Password must be 10+ characters"
const val registerPasswordHint2 = "Password must include uppercase"
const val registerPasswordHint3 = "Password must include number"
const val registerPasswordHint4 = "Password must include special character"

// if the register hint is not fulfilled
const val errorRegisterUsername1 = "Username too short"
const val errorRegisterUsername2 = "Username too long"
const val errorRegisterUsername3 = "Username cannot contain spaces"
const val errorRegisterEmail = "Invalid email format"
const val errorRegisterPassword1 = "Password is too short"
const val errorRegisterPassword2 = "Password MUST include uppercase"
const val errorRegisterPassword3 = "Password MUST include number"
const val errorRegisterPassword4 = "Password MUST include special character"

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