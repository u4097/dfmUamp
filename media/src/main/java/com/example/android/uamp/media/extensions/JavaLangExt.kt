package com.example.android.uamp.media.extensions

import java.net.URLEncoder
import java.nio.charset.Charset

/**
 * This file contains extension methods for the java.lang package.
 */

/**
 * Helper method to check if a [String] contains another in a case insensitive way.
 */
fun String?.containsCaseInsensitive(other: String?) =
        if (this == null && other == null) {
            true
        } else if (this != null && other != null) {
            toLowerCase().contains(other.toLowerCase())
        } else {
            false
        }

/**
 * Helper extension to URL encode a [String]. Returns an empty string when called on null.
 */
inline val String?.urlEncoded: String
    get() = if (Charset.isSupported("UTF-8")) {
        URLEncoder.encode(this ?: "", "UTF-8")
    } else {
        // If UTF-8 is not supported, use the default charset.
        @Suppress("deprecation")
        URLEncoder.encode(this ?: "")
    }
