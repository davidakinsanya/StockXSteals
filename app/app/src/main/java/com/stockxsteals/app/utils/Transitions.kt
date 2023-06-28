@file:OptIn(ExperimentalAnimationApi::class)

package com.stockxsteals.app.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry


val downEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
        = { slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(350)) }

val leftEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
        = { slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(350)) }

val rightEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)
        = { slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(350)) }


val upExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
        = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(350)) }


val rightExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)
        = { slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(350)) }