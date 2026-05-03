package com.example.pbl.ui.animation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically

// ─────────────────────────────────────────────
// Easing
// ─────────────────────────────────────────────

private val XEasing = Easing { t ->
    val c1 = 1.70158f
    val c3 = c1 + 1f
    1 + c3 * Math.pow((t - 1).toDouble(), 3.0).toFloat() + c1 * Math.pow((t - 1).toDouble(), 2.0).toFloat()
}

private const val DURATION = 500
private const val FADE_DURATION = 550

// ─────────────────────────────────────────────
// X-style horizontal (push/pop)
// Used by: login, register, forgotpassword
// ─────────────────────────────────────────────

// FORWARD → incoming slides in from right
val PushEnter = slideInHorizontally(
    animationSpec = tween(DURATION, easing = XEasing),
    initialOffsetX = { (it * 0.35f).toInt() }
) + fadeIn(tween(DURATION, easing = XEasing), initialAlpha = 0.4f)

// FORWARD → outgoing collapses: shrinks + slides slightly LEFT + fades
val PushExit = slideOutHorizontally(
    animationSpec = tween(DURATION, easing = XEasing),
    targetOffsetX = { -(it * 0.12f).toInt() }
) + scaleOut(
    animationSpec = tween(DURATION, easing = XEasing),
    targetScale = 0.92f
) + fadeOut(tween(DURATION, easing = XEasing))

// BACKWARD → incoming slides in from left
val PopEnter = slideInHorizontally(
    animationSpec = tween(DURATION, easing = XEasing),
    initialOffsetX = { -(it * 0.35f).toInt() }
) + fadeIn(tween(DURATION, easing = XEasing), initialAlpha = 0.4f)

// BACKWARD → outgoing collapses: shrinks + slides slightly RIGHT + fades
val PopExit = slideOutHorizontally(
    animationSpec = tween(DURATION, easing = XEasing),
    targetOffsetX = { (it * 0.12f).toInt() }
) + scaleOut(
    animationSpec = tween(DURATION, easing = XEasing),
    targetScale = 0.92f
) + fadeOut(tween(DURATION, easing = XEasing))

// ─────────────────────────────────────────────
// Slide up / down (modal feel)
// Used by: learn, terms
// ─────────────────────────────────────────────

val slideUpEnter = slideInVertically(
    animationSpec = tween(DURATION, easing = XEasing),
    initialOffsetY = { (it * 0.35f).toInt() }
) + fadeIn(tween(DURATION, easing = XEasing), initialAlpha = 0.4f)

val slideUpExit = fadeOut(tween(DURATION, easing = XEasing))

val slideDownEnter = fadeIn(tween(DURATION, easing = XEasing), initialAlpha = 0.4f)

val slideDownExit = slideOutVertically(
    animationSpec = tween(DURATION, easing = XEasing),
    targetOffsetY = { (it * 0.35f).toInt() }
) + fadeOut(tween(DURATION, easing = XEasing))

// ─────────────────────────────────────────────
// Fade only (big destination / state change)
// Used by: verify, home
// ─────────────────────────────────────────────

val fadeEnter = fadeIn(tween(FADE_DURATION))
val fadeExit = fadeOut(tween(FADE_DURATION))

// ─────────────────────────────────────────────
// No transition (app entry — just appears)
// Used by: landing
// ─────────────────────────────────────────────

val noEnter = fadeIn(tween(1))
val noExit = fadeOut(tween(1))