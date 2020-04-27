/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *
 */
package com.elespada.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

/**
 * Sample standalone utility for encrypting and decrypting strings. This class
 * is not for application usage.
 *
 * @author Avinash Moram
 */
public class JasyptEncryption {

	private static String mpCryptoPassword = "ZachDanAvi";

	public static void main(String[] args) {

		String value = "esp84AdA";
		System.out.println("Original Value : " + value);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(mpCryptoPassword);
		encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		encryptor.setIvGenerator(new RandomIvGenerator());

		String encryptedPassword = encryptor.encrypt(value);
		System.out.println(encryptedPassword);

		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword(mpCryptoPassword);
		decryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		decryptor.setIvGenerator(new RandomIvGenerator());

		String decryptedPassword = decryptor.decrypt(encryptedPassword);
		System.out.println(decryptedPassword);

	}
}
