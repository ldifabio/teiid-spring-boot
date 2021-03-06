/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.teiid.spring.example;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class, TestConfiguration.class })
public class TestExample {

    @Autowired
    JdbcTemplate template;

    @Test
    public void test() {
        template.query("select id, name from foo.foo where id = 10", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                assertEquals(10, rs.getInt(1));
                assertEquals("Joseph Smith", rs.getString(2));
            }
        });
        template.query("select id, name from bar.bar", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                assertEquals(12, rs.getInt(1));
                assertEquals("Jane Aire", rs.getString(2));
            }
        });
    }
}
