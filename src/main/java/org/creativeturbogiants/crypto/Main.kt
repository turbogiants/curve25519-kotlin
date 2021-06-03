package org.creativeturbogiants.crypto

import org.creativeturbogiants.crypto.curve25519.Curve25519

fun main(args: Array<String>) {

    val cipher = Curve25519.getInstance()

    val keyPair1 = cipher.generateKeyPair()

    val keyPair2 = cipher.generateKeyPair()

    val secret1 = cipher.calculateAgreement(keyPair1.publicKey, keyPair2.privateKey)

    val secret2 = cipher.calculateAgreement(keyPair2.publicKey, keyPair1.privateKey)

    println("---------------------------------------------------------------------------------------------")
    println("User 1 public key          = ${bytesToHex(keyPair1.publicKey)}")
    println("User 1 private key         = ${bytesToHex(keyPair1.privateKey)}")

    println("---------------------------------------------------------------------------------------------")
    println("User 2 public key          = ${bytesToHex(keyPair2.publicKey)}")
    println("User 2 private key         = ${bytesToHex(keyPair2.privateKey)}")

    println("---------------------------------------------------------------------------------------------")
    println("User 1 shared secret key   = ${bytesToHex(secret1)}")
    println("User 2 shared secret key   = ${bytesToHex(secret2)}")

    println("---------------------------------------------------------------------------------------------")
    val signature = cipher.calculateSignature(
            keyPair1.privateKey, "test".toByteArray()
    )

    println("Message Signature          : ${bytesToHex(signature)}")

    val isValidSignature = cipher.verifySignature(
            keyPair1.publicKey, "test".toByteArray(), signature
    )

    println("Message Signature Valid    : $isValidSignature")

    val sig = bytesToHex(signature)
    println("Message length             : ${sig.length} bit symmetric cipher ${sig.length == 128}")
    println("Message in bytes           : ${signature.size} bytes")

}

private val HEX_ARRAY = "0123456789ABCDEF".toCharArray()

fun bytesToHex(bytes: ByteArray): String {
    val hexChars = CharArray(bytes.size * 2)
    for (j in bytes.indices) {
        val v = bytes[j].toInt() and 0xFF
        hexChars[j * 2] = HEX_ARRAY[v.ushr(4)]
        hexChars[j * 2 + 1] = HEX_ARRAY[v and 0x0F]
    }

    return String(hexChars)
}