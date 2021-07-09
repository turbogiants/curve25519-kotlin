package org.turbogiants.crypto.ed25519

import org.turbogiants.crypto.java.Sha512

/**
 * Added VRF support, so java code won't rely on native c++ jni.
 * https://github.com/signalapp/curve25519-java/pull/37
 */

object veddsa_sigs {
    fun VRFsign(sha512provider: Sha512?, result: ByteArray?, privateKey: ByteArray?, message: ByteArray?, message_len: Int, random: ByteArray?): Int {

        val ret_val = gen_x.generalized_xveddsa_25519_sign(result, privateKey, message, message_len.toLong(), random,
                sha512provider, null, 0)
        return ret_val
    }

    fun VRFverify(sha512provider: Sha512?, vrf_out: ByteArray?, signature: ByteArray?,
                  publicKey: ByteArray?, message: ByteArray?, messagelen: Long): Int {

        val ret_val = gen_x.generalized_xveddsa_25519_verify(vrf_out, signature, publicKey,
                message, messagelen, null, 0, sha512provider)
        return ret_val
    }
}