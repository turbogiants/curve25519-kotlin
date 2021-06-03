package org.creativeturbogiants.crypto.curve25519

import org.creativeturbogiants.crypto.utils.byte

interface Curve25519Provider {

    val isNative: Boolean

    fun generatePublicKey(privateKey: byte): byte
    fun generatePrivateKey(): byte
    fun generatePrivateKey(random: byte): byte

    fun calculateAgreement(ourPrivate: byte, theirPublic: byte): byte
    fun calculateSignature(random: byte, privateKey: byte, message: byte): byte
    fun verifySignature(publicKey: byte, message: byte, signature: byte): Boolean

    fun calculateVrfSignature(random: byte, privateKey: byte, message: byte): byte
    @Throws(VrfSignatureVerificationFailedException::class)
    fun verifyVrfSignature(publicKey: byte, message: byte, signature: byte): byte

    fun getRandom(length: Int = PRIVATE_KEY_LEN): byte
    fun setRandomProvider(provider: SecureRandomProvider)
}

