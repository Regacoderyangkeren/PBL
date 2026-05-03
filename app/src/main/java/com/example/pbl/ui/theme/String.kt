package com.example.pbl.ui.theme
const val userPassword = "Password"

// Password visibility
const val passwordShowLabel = "Show password"
const val passwordHideLabel = "Hide password"

// for basic error catch
const val errorInputBlank = "Input cannot be empty"
const val errorNotAlphanumeric = "Input can only contain letters and numbers"
const val errorNotNumeric = "Input can only contain numbers"
fun errorInputTooLong(
    maxLength: Int
) = "Input is too long (max $maxLength characters)"

// Hint SignUp
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
const val signUpConfirmPasswordHint = "Passwords must match"

// Kalo hint SignUp tidak terpenuhi
const val errorSignUpUsername1 = "Username too short"
const val errorSignUpUsername2 = "Username too long"
const val errorSignUpUsername3 = "Username cannot contain spaces"
const val errorSignUpEmail = "Invalid email format"
const val errorSignUpPassword1 = "Password is too short"
const val errorSignUpPassword2 = "Password MUST include uppercase"
const val errorSignUpPassword3 = "Password MUST include number"
const val errorSignUpPassword4 = "Password MUST include special character"
const val errorInvalidPassword = "Password must be 10+ chars, include uppercase, number & special character"
const val errorConfirmPasswordMismatch = "Passwords do not match"

// Login Error
const val errorLoginEmail = "No user found with this email"
const val errorLoginPassword = "Incorrect password"
const val errorLoginInvalidCredential = "Email or password is incorrect"
const val errorEmailInUse = "This email is already registered."
const val errorNetworkGeneral = "Network error. Please try again."
const val errorUnknown = "Something went wrong. Please try again."
const val errorLoadingTimeout = "Request timed out. Please try again."
const val errorVerifyTimeout = "Verification timed out. Your registration has been cancelled."

// Loading states
const val loadingRegistering = "Registering..."
const val loadingSigningIn = "Signing in..."
const val loadingCheckAccount = "Check Account & Continue"
const val loadingAccountNotFound = "Account not found. Please register again."

// Error codes (internal)
const val errorCodeEmailInUse = "EMAIL_IN_USE"
const val errorCodeInvalidEmail = "INVALID_EMAIL"
const val errorCodeUserNotFound = "USER_NOT_FOUND"
const val errorCodeWrongPassword = "WRONG_PASSWORD"
const val errorCodeInvalidCredential  = "INVALID_CREDENTIAL"
const val errorCodeNetworkError = "NETWORK_ERROR"

// Internal error messages
const val errorNoSignedInUser = "No signed-in user"
const val errorUserNotFound = "User not found"

// SignIn Screen
const val signIn = "Sign In"
const val signInSubtitle = "Welcome back!"
const val signInButton = "Sign In"
const val signInForgotPassword = "Forgot password?"
const val signInNoAccount = "Sign Up ->"

// Forgot Password Screen
const val forgotPasswordTitle = "Reset Password"
const val forgotPasswordSubtitle = "Enter your email and we'll send you a reset link."
const val forgotPasswordEmail = "Email"
const val forgotPasswordButton = "Send Reset Link"
const val forgotPasswordSuccess = "Reset link sent! Check your inbox."
const val forgotPasswordBackToLogin = "Back to Sign In"

// SignUp Screen
const val signUpTitle = "Create Account"
const val signUpSubtitle = "Your journey begins here!"
const val signUpEmail = "Email"
const val signUpFirstName = "First Name"
const val signUpLastName = "Last Name"
const val signUpAlias = "Alias"
const val signUpButton = "Sign Up"
const val signUpHaveAccount = "Already have an account? Sign In"
const val signUpConfirmPassword = "Confirm Password"
const val signUpTermsPrefix = "I agree to the "
const val signUpTermsLink = "Terms and Conditions"

// Terms and Conditions Screen
const val termsTitle = "Terms and Conditions"
const val termsLastUpdated = "Last updated: April 2026"
const val termsIntroTitle = "1. Introduction"
const val termsIntroBody = "Welcome to our app. By registering and using this application, you agree to be bound by these Terms and Conditions. Please read them carefully before proceeding."
const val termsAccountTitle = "2. Account Registration"
const val termsAccountBody = "You must provide accurate and complete information when creating an account. You are responsible for maintaining the confidentiality of your account credentials and for all activities that occur under your account. You must notify us immediately of any unauthorized use of your account."
const val termsUseTitle = "3. Acceptable Use"
const val termsUseBody = "You agree to use this application only for lawful purposes and in a manner that does not infringe the rights of others. You must not misuse the platform to distribute harmful content, attempt unauthorized access, or interfere with the service's normal operation."
const val termsPrivacyTitle = "4. Privacy"
const val termsPrivacyBody = "We collect and process your personal data in accordance with our Privacy Policy. By using this application, you consent to the collection and use of your information as described therein. We do not sell your personal data to third parties."
const val termsIpTitle = "5. Intellectual Property"
const val termsIpBody = "All content, features, and functionality of this application are owned by us and are protected by applicable intellectual property laws. You may not copy, modify, or distribute any part of the application without prior written consent."
const val termsTerminationTitle = "6. Termination"
const val termsTerminationBody = "We reserve the right to suspend or terminate your account at any time if you violate these Terms and Conditions. You may also delete your account at any time through the application settings."
const val termsLiabilityTitle = "7. Limitation of Liability"
const val termsLiabilityBody = "To the fullest extent permitted by law, we shall not be liable for any indirect, incidental, or consequential damages arising from your use of this application. The application is provided on an 'as is' basis without warranties of any kind."
const val termsChangesTitle = "8. Changes to Terms"
const val termsChangesBody = "We may update these Terms and Conditions from time to time. Continued use of the application after changes are posted constitutes your acceptance of the revised terms. We will notify users of significant changes through the application."
const val termsContactTitle = "9. Contact"
const val termsContactBody = "If you have any questions about these Terms and Conditions, please contact us through the application's support channel."

// Email Verify Screen
const val emailVerifyTitle = "Check your inbox"
const val emailVerifySubtitle = "We sent a verification link to your email.\nOpen it to continue — this page will update automatically."
const val emailVerifyWaiting = "Waiting for verification..."
const val emailVerifyResend = "Resend email"
const val emailVerifyResending = "Sending..."
const val emailVerifyResendTimer = "Resend in "
const val emailVerifyWrongEmail = "Wrong email? Go back"

// Home Screen
const val home = "Home"
const val homeTitle = "Home"
const val homeSubtitle = "Your dashboard"
const val welcome = "Welcome to the Home Screen!"
const val homeEmptyState = "No items yet"
const val logoutButton = "Logout"

// Landing Screen
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