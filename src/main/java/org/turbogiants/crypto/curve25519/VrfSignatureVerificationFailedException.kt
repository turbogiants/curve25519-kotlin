package org.turbogiants.crypto.curve25519

class VrfSignatureVerificationFailedException
@JvmOverloads constructor(
    message: String? = null,
    exception: Exception? = null
): Exception(message, exception)
