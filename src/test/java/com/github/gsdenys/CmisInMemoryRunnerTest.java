/*
 * Copyright 2016 gsdenys@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gsdenys;

import com.github.gsdenys.runner.Configure;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test for the {@link CmisInMemoryRunner} class
 *
 * @author Denys G. Santos (gsdenys@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@RunWith(CmisInMemoryRunner.class)
@Configure(port = 8080)
public class CmisInMemoryRunnerTest {

    @Test
    public void getCmisURI() throws Exception {

    }

    @Test
    public void testConnection() throws Exception {

        System.out.println("\n\n\n\n" + CmisInMemoryRunner.getCmisPort() + "\n\n\n\n\n");
        URL url = new URL("http://localhost:" + CmisInMemoryRunner.getCmisPort() + "/cmis/atom");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        assertEquals("The Response code should be 200", conn.getResponseCode(), 200);

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        Assert.assertNotNull("The return should be null", br.readLine());

        conn.disconnect();
    }

    @Test
    public void getCmisPort() throws Exception {
        Integer port = CmisInMemoryRunner.getCmisPort();

        assertNotNull("The object should not be null", port);

        assertEquals(
                "The port should be 8080",
                java.util.Optional.ofNullable(port),
                java.util.Optional.ofNullable(8080)
        );
    }

    @Test
    public void getPortDefinedByUser() throws Exception {
        assertEquals(
                "The port should be 8080",
                java.util.Optional.ofNullable(CmisInMemoryRunner.getCmisPort()),
                java.util.Optional.ofNullable(8080)
        );
    }
}