/*
###############################################################################
#                                                                             #
#    Copyright 2016, AdeptJ (http://www.adeptj.com)                           #
#                                                                             #
#    Licensed under the Apache License, Version 2.0 (the "License");          #
#    you may not use this file except in compliance with the License.         #
#    You may obtain a copy of the License at                                  #
#                                                                             #
#        http://www.apache.org/licenses/LICENSE-2.0                           #
#                                                                             #
#    Unless required by applicable law or agreed to in writing, software      #
#    distributed under the License is distributed on an "AS IS" BASIS,        #
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. #
#    See the License for the specific language governing permissions and      #
#    limitations under the License.                                           #
#                                                                             #
###############################################################################
*/

package com.adeptj.runtime.common;

import com.adeptj.runtime.exception.InitializationException;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Utilities for Java KeyStore.
 *
 * @author Rakesh.Kumar, AdeptJ
 */
public final class KeyStores {

    public static KeyStore getKeyStore(String keyStoreLoc, char[] keyStorePwd) {
        try (InputStream is = Files.newInputStream(Paths.get(keyStoreLoc))) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(is, keyStorePwd);
            return keyStore;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException ex) {
            LoggerFactory.getLogger(SslUtil.class).error("Exception while loading KeyStore!!", ex);
            throw new InitializationException(ex.getMessage(), ex);
        }
    }

    public static KeyStore getDefaultKeyStore(String defaultKeyStore, char[] keyStorePwd) {
        try (InputStream is = KeyStores.class.getResourceAsStream(defaultKeyStore)) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(is, keyStorePwd);
            return keyStore;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException ex) {
            LoggerFactory.getLogger(SslUtil.class).error("Exception while loading KeyStore!!", ex);
            throw new InitializationException(ex.getMessage(), ex);
        }
    }
}
