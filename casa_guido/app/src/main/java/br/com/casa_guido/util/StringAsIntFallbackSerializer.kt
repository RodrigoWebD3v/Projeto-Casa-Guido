package br.com.casa_guido.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive

/*Essa funcão serve para forcar um cast no dto, porem não estou utilizando*/
object StringAsIntFallbackSerializer : KSerializer<String> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("StringAsIntFallback", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): String {
        return try {
            decoder.decodeString()
        } catch (e: Exception) {
            try {
                val input = decoder as? JsonDecoder
                val element = input?.decodeJsonElement()
                element?.jsonPrimitive?.content ?: ""
            } catch (e: Exception) {
                ""
            }
        }
    }

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
    }
}