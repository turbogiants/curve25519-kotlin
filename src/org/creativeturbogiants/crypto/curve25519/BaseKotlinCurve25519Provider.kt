@file:Suppress("unused")

package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.java.Sha512
import org.creativeturbogiants.crypto.java.curve_sigs
import org.creativeturbogiants.crypto.ed25519.veddsa_sigs
import org.creativeturbogiants.crypto.java.scalarmult
import org.creativeturbogiants.crypto.utils.byte
import kotlin.experimental.and
import kotlin.experimental.or

abstract class BaseKotlinCurve25519Provider constructor(
        private var sha512Provider: Sha512? = null,
        private var secureRandomProvider: SecureRandomProvider? = null
) : Curve25519Provider {

    abstract override val isNative: Boolean

    override fun generatePublicKey(privateKey: byte): byte {
        val publicKey = byte(32)
        curve_sigs.curve25519_keygen(publicKey, privateKey)

        return publicKey
    }

    override fun generatePrivateKey(): byte {
        val random = getRandom()
        return generatePrivateKey(random)
    }

    override fun generatePrivateKey(random: byte): byte {
        val privateKey = byte(32)

        System.arraycopy(random, 0, privateKey, 0, 32)

        privateKey[0] = privateKey[0] and 248.toByte()
        privateKey[31] = privateKey[31] and 127.toByte()
        privateKey[31] = privateKey[31] or 64.toByte()

        return privateKey
    }

    override fun calculateAgreement(ourPrivate: byte, theirPublic: byte): byte {
        val agreement = byte(32)
        scalarmult.crypto_scalarmult(agreement, ourPrivate, theirPublic)

        return agreement
    }

    override fun calculateSignature(random: byte, privateKey: byte, message: byte): byte {
        val result = byte(64)
        if (curve_sigs.curve25519_sign(
                        sha512Provider,
                        result,
                        privateKey,
                        message,
                        message.size,
                        random
                ) != 0) {
            throw IllegalArgumentException("Message exceeds max length!")
        }

        return result
    }

    override fun verifySignature(publicKey: byte, message: byte, signature: byte): Boolean {
        return curve_sigs.curve25519_verify(
                sha512Provider,
                signature,
                publicKey,
                message,
                message.size
        ) == 0
    }

    override fun verifyVrfSignature(publicKey: byte, message: byte, signature: byte): byte {
        val result = ByteArray(32)

        if (veddsa_sigs.VRFverify(sha512Provider, result, signature, publicKey, message, message.size.toLong()) != 0) {
            throw VrfSignatureVerificationFailedException()
        }

        return result
    }

    override fun calculateVrfSignature(random: byte, privateKey: byte, message: byte): byte {
        val result = byte(96)

        if (veddsa_sigs.VRFsign(sha512Provider, result, privateKey, message, message.size, random) != 0) {
            throw IllegalArgumentException("Message exceeds max length!");
        }

        return result
    }

    override fun getRandom(length: Int): byte {
        val result = byte(length)
        secureRandomProvider?.nextByte(result)

        return result
    }

    override fun setRandomProvider(provider: SecureRandomProvider) {
        this.secureRandomProvider = provider
    }
}