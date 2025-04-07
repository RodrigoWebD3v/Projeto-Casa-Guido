package br.com.casa_guido.customizacao

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class VisualTransformationCustom {
    class PhoneVisualTransformation : VisualTransformation {

        override fun filter(text: AnnotatedString): TransformedText {
            val original = text.text.filter { it.isDigit() }.take(11)

            val transformed = buildString {
                original.forEachIndexed { index, c ->
                    when (index) {
                        0 -> append("($c")
                        1 -> append("$c) ")
                        6 -> append("$c-")
                        else -> append(c)
                    }
                }
            }

            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        1 -> 2        // (X
                        2 -> 4        // (XX)
                        3, 4, 5, 6, 7 -> offset + 3
                        8, 9, 10, 11 -> offset + 4
                        else -> transformed.length
                    }.coerceAtMost(transformed.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        in 0..3 -> 1
                        in 4..5 -> 2
                        in 6..10 -> offset - 3
                        in 11..15 -> offset - 4
                        else -> original.length
                    }.coerceAtMost(original.length)
                }
            }

            return TransformedText(AnnotatedString(transformed), offsetMapping)
        }
    }

    class CpfVisualTransformation : VisualTransformation {
        // xxx.xxx.xxx-xx
        override fun filter(text: AnnotatedString): TransformedText {
            val original = text.text.filter { it.isDigit() }.take(11)

            val transformed = buildString {
                original.forEachIndexed { index, c ->
                    when (index) {
                        2 -> append("$c.")
                        5 -> append("$c.")
                        8 -> append("$c-")
                        else -> append(c)
                    }
                }
            }

            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        1 -> 2        // (X
                        2 -> 4        // (XX)
                        3, 4, 5, 6, 7 -> offset + 3
                        8, 9, 10, 11 -> offset + 4
                        else -> transformed.length
                    }.coerceAtMost(transformed.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        in 0..3 -> 1
                        in 4..5 -> 2
                        in 6..10 -> offset - 3
                        in 11..15 -> offset - 4
                        else -> original.length
                    }.coerceAtMost(original.length)
                }
            }

            return TransformedText(AnnotatedString(transformed), offsetMapping)
        }
    }


    class RgVisualTransformation : VisualTransformation {
        //xx.xxx.xxx-x
        override fun filter(text: AnnotatedString): TransformedText {
            val original = text.text.filter { it.isDigit() }.take(9)

            val transformed = buildString {
                original.forEachIndexed { index, c ->
                    when (index) {
                        1 -> append("$c.")
                        4 -> append("$c.")
                        7 -> append("$c-")
                        else -> append(c)
                    }
                }
            }

            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        1 -> 2        // (X
                        2 -> 4        // (XX)
                        3, 4, 5, 6, 7 -> offset + 3
                        8, 9, 10, 11 -> offset + 4
                        else -> transformed.length
                    }.coerceAtMost(transformed.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        in 0..3 -> 1
                        in 4..5 -> 2
                        in 6..10 -> offset - 3
                        in 11..15 -> offset - 4
                        else -> original.length
                    }.coerceAtMost(original.length)
                }
            }

            return TransformedText(AnnotatedString(transformed), offsetMapping)
        }
    }


    class CartSusVisualTransformation : VisualTransformation {
        // xxx xxxx xxxx xxxx
        override fun filter(text: AnnotatedString): TransformedText {
            val original = text.text.filter { it.isDigit() }.take(15)

            val transformed = buildString {
                original.forEachIndexed { index, c ->
                    when (index) {
                        2 -> append("$c ")
                        6 -> append("$c ")
                        10 -> append("$c ")
                        else -> append(c)
                    }
                }
            }

            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        1 -> 2        // (X
                        2 -> 4        // (XX)
                        3, 4, 5, 6, 7 -> offset + 3
                        8, 9, 10, 11 -> offset + 4
                        else -> transformed.length
                    }.coerceAtMost(transformed.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 0) return offset
                    return when (offset) {
                        in 0..3 -> 1
                        in 4..5 -> 2
                        in 6..10 -> offset - 3
                        in 11..15 -> offset - 4
                        else -> original.length
                    }.coerceAtMost(original.length)
                }
            }

            return TransformedText(AnnotatedString(transformed), offsetMapping)
        }
    }

}