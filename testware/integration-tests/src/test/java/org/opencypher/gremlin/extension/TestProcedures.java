/*
 * Copyright (c) 2018 "Neo4j, Inc." [https://neo4j.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opencypher.gremlin.extension;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.opencypher.gremlin.extension.CypherBinding.binding;
import static org.opencypher.gremlin.extension.CypherProcedure.cypherProcedure;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class TestProcedures implements Supplier<Set<CypherProcedure>> {

    private final Set<CypherProcedure> procedures = new HashSet<>();

    public TestProcedures() {
        procedures.add(cypherProcedure(
            "test.getName",
            emptyList(),
            singletonList(binding("name", String.class)),
            arguments -> asList(
                singletonMap("name", "marko"),
                singletonMap("name", "vadas")
            )
        ));

        procedures.add(cypherProcedure(
            "test.inc",
            singletonList(binding("a", Long.class)),
            singletonList(binding("r", Long.class)),
            arguments -> {
                long a = (long) arguments.get("a");
                return singletonList(singletonMap("r", a + 1));
            }
        ));

        procedures.add(cypherProcedure(
            "test.incF",
            singletonList(binding("a", Double.class)),
            singletonList(binding("r", Double.class)),
            arguments -> {
                double a = (double) arguments.get("a");
                return singletonList(singletonMap("r", a + 1));
            }
        ));

        procedures.add(cypherProcedure(
            "test.multi",
            emptyList(),
            asList(binding("foo", String.class), binding("bar", String.class)),
            arguments -> {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("bar", "bar");
                row.put("foo", "foo");
                return singletonList(row);
            }
        ));

        procedures.add(cypherProcedure(
            "test.void",
            emptyList(),
            emptyList(),
            arguments -> emptyList()
        ));
    }

    @Override
    public Set<CypherProcedure> get() {
        return new HashSet<>(procedures);
    }
}
