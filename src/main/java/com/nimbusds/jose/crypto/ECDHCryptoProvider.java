package com.nimbusds.jose.crypto;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.nimbusds.jose.JWEAlgorithm;


/**
 * The base abstract class for Elliptic Curve Diffie-Hellman encrypters and
 * decrypters of {@link com.nimbusds.jose.JWEObject JWE objects}.
 *
 * <p>Supports the following JSON Web Algorithms (JWAs):
 *
 * <ul>
 *      <li>{@link com.nimbusds.jose.JWEAlgorithm#ECDH_ES}
 *      <li>{@link com.nimbusds.jose.JWEAlgorithm#ECDH_ES_A128KW}
 *      <li>{@link com.nimbusds.jose.JWEAlgorithm#ECDH_ES_A192KW}
 *      <li>{@link com.nimbusds.jose.JWEAlgorithm#ECDH_ES_A256KW}
 * </ul>
 *
 * <p>Supports the following encryption methods:
 *
 * <ul>
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A128CBC_HS256}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A192CBC_HS384}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A256CBC_HS512}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A128GCM}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A192GCM}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A256GCM}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A128CBC_HS256_DEPRECATED}
 *     <li>{@link com.nimbusds.jose.EncryptionMethod#A256CBC_HS512_DEPRECATED}
 * </ul>
 *
 * @author Vladimir Dzhuvinov
 * @version $version$ (2015-05-16)
 */
abstract class ECDHCryptoProvider extends BaseJWEProvider {


	/**
	 * The supported JWE algorithms.
	 */
	public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;


	/**
	 * The JWE algorithms compatible with each key size.
	 */
	// public static final Map<Integer, Set<JWEAlgorithm>> COMPATIBLE_ALGORITHMS; TODO


	/**
	 * Initialises the supported algorithms and encryption methods.
	 */
	static {
		Set<JWEAlgorithm> algs = new HashSet<>();
		algs.add(JWEAlgorithm.ECDH_ES);
		algs.add(JWEAlgorithm.ECDH_ES_A128KW);
		algs.add(JWEAlgorithm.ECDH_ES_A192KW);
		algs.add(JWEAlgorithm.ECDH_ES_A256KW);
		SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(algs);
	}


	/**
	 * The Concatenation Key Derivation Function (KDF).
	 */
	private final ConcatKDF concatKDF;


	/**
	 * Creates a new Elliptic Curve Diffie-Hellman encryption /decryption
	 * provider.
	 */
	protected ECDHCryptoProvider() {

		super(SUPPORTED_ALGORITHMS, ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS);

		concatKDF = new ConcatKDF("SHA-256"); // TODO set provider
	}


	/**
	 * Returns the Concatenation Key Derivation Function (KDF).
	 *
	 * @return The concat KDF.
	 */
	protected ConcatKDF getConcatKDF() {

		return concatKDF;
	}
}