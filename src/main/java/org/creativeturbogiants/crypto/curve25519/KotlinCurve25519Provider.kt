package org.creativeturbogiants.crypto.curve25519

class KotlinCurve25519Provider constructor(
        jceSha512Provider: JCESha512Provider = JCESha512Provider(),
        jceSecureRandomProvider: JCESecureRandomProvider = JCESecureRandomProvider()
) : BaseKotlinCurve25519Provider(jceSha512Provider, jceSecureRandomProvider) {

    override val isNative: Boolean
        get() = false
}

