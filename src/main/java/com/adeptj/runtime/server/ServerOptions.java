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

package com.adeptj.runtime.server;

import com.adeptj.runtime.common.Times;
import com.typesafe.config.Config;
import io.undertow.Undertow.Builder;

import java.util.Map;

/**
 * UNDERTOW Server Options.
 *
 * @author Rakesh.Kumar, AdeptJ
 */
final class ServerOptions extends BaseOptions {

    private static final String SERVER_OPTIONS = "server-options";

    private static final String OPTIONS_TYPE_STRING = "options-type-string";

    private static final String OPTIONS_TYPE_INTEGER = "options-type-integer";

    private static final String OPTIONS_TYPE_LONG = "options-type-long";

    private static final String OPTIONS_TYPE_BOOLEAN = "options-type-boolean";

    /**
     * Configures the server options dynamically.
     *
     * @param builder        Undertow.Builder
     * @param undertowConfig Undertow Typesafe Config
     */
    public Builder build(Builder builder, Config undertowConfig) {
        long startTime = System.nanoTime();
        Config serverOptionsCfg = undertowConfig.getConfig(SERVER_OPTIONS);
        stringOptions(builder, serverOptionsCfg.getObject(OPTIONS_TYPE_STRING).unwrapped());
        integerOptions(builder, serverOptionsCfg.getObject(OPTIONS_TYPE_INTEGER).unwrapped());
        longOptions(builder, serverOptionsCfg.getObject(OPTIONS_TYPE_LONG).unwrapped());
        booleanOptions(builder, serverOptionsCfg.getObject(OPTIONS_TYPE_BOOLEAN).unwrapped());
        this.logger.info("Undertow ServerOptions set in [{}] ms!!", Times.elapsedMillis(startTime));
        return builder;
    }

    private void buildServerOptions(Builder builder, Map<String, ?> options) {
        options.forEach((optKey, optVal) -> builder.setServerOption(toOption(optKey), optVal));
    }

    private void stringOptions(Builder builder, Map<String, ?> options) {
        buildServerOptions(builder, options);
    }

    private void integerOptions(Builder builder, Map<String, ?> options) {
        buildServerOptions(builder, options);
    }

    private void booleanOptions(Builder builder, Map<String, ?> options) {
        buildServerOptions(builder, options);
    }

    private void longOptions(Builder builder, Map<String, ?> options) {
        options.forEach((optKey, optVal) -> builder.setServerOption(toOption(optKey), Long.valueOf((Integer) optVal)));
    }
}
