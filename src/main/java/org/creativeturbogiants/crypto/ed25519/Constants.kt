package org.creativeturbogiants.crypto.ed25519

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */
object Constants {
    const val LABELSETMAXLEN = 512
    const val LABELMAXLEN = 128
    const val BUFLEN = 1024
    const val BLOCKLEN = 128 /* SHA512 */
    const val HASHLEN = 64 /* SHA512 */
    const val POINTLEN = 32
    const val SCALARLEN = 32
    const val RANDLEN = 32
    const val SIGNATURELEN = 64
    const val VRFSIGNATURELEN = 96
    const val VRFOUTPUTLEN = 32
    const val MSTART = 2048
    const val MSGMAXLEN = 1048576
}