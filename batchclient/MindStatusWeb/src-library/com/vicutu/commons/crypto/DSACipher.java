package com.vicutu.commons.crypto;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;

public final class DSACipher
{
	private static final String ARITHMETIC_DSA = "DSA";

	private static final String RANDOM_SEEK = "OP{}|+_)(*&^%$#@!~";

	private DSACipher()
	{
	}

	public static boolean verify(byte[] publicKeyBytes, byte[] dataBytes, byte[] signatureBytes) throws Exception
	{
		KeyFactory keyFactory = KeyFactory.getInstance(ARITHMETIC_DSA);
		X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = keyFactory.generatePublic(bobPubKeySpec);
		Signature signature = Signature.getInstance(ARITHMETIC_DSA);
		signature.initVerify(publicKey);
		signature.update(dataBytes);
		return signature.verify(signatureBytes);
	}

	public static boolean verify(String publicKeyString, byte[] dataBytes, String signatureString) throws Exception
	{
		byte[] publicKeyBytes = Hex.decodeHex(publicKeyString.toCharArray());
		byte[] signatureBytes = Hex.decodeHex(signatureString.toCharArray());
		return verify(publicKeyBytes, dataBytes, signatureBytes);
	}

	public static String signature(byte[] input, byte[] privateKeyBytes) throws Exception
	{
		KeyFactory keyFactory = KeyFactory.getInstance(ARITHMETIC_DSA);
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKeyBytes);
		PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);

		Signature signature = Signature.getInstance(ARITHMETIC_DSA);
		signature.initSign(privateKey);
		signature.update(input);

		return new String(Hex.encodeHex(signature.sign()));
	}

	public static KeyPair key() throws Exception
	{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ARITHMETIC_DSA);
		SecureRandom random = new SecureRandom();
		random.setSeed(RANDOM_SEEK.getBytes());
		kpg.initialize(1024, random);
		return kpg.genKeyPair();
	}
}