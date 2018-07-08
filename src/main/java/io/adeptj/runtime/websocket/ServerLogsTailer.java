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

package io.adeptj.runtime.websocket;

import io.adeptj.runtime.common.DefaultExecutorService;
import org.apache.commons.io.input.Tailer;

import javax.websocket.Session;
import java.io.File;

/**
 * Tails server log using Apache Commons IO {@link Tailer}.
 *
 * @author Rakesh.Kumar, AdeptJ
 */
class ServerLogsTailer {

    /**
     * The delay between checks of the file for new content in milliseconds.
     */
    private static final int DELAY_MILLIS = Integer.getInteger("websocket.logs.tailing.delay", 1000);

    private Tailer tailer;

    ServerLogsTailer(File logFile, Session session) {
        this.tailer = Tailer.create(logFile, new ServerLogsTailerListener(logFile, session), DELAY_MILLIS, true);
    }

    void startTailer() {
        DefaultExecutorService.getInstance().execute(this.tailer);
    }

    void stopTailer() {
        this.tailer.stop();
    }
}
