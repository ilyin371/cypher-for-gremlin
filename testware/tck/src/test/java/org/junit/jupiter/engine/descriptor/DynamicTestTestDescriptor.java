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
package org.junit.jupiter.engine.descriptor;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.UniqueId;

/**
 * {@link TestDescriptor} for a {@link DynamicTest}.
 *
 * @since 5.0
 */
class DynamicTestTestDescriptor extends JupiterTestDescriptor {

	private final DynamicTest dynamicTest;

	DynamicTestTestDescriptor(UniqueId uniqueId, DynamicTest dynamicTest, TestSource source) {
		super(uniqueId, dynamicTest.getDisplayName(), source);
		this.dynamicTest = dynamicTest;
	}

	@Override
	public Type getType() {
		return Type.TEST;
	}

	@Override
	public SkipResult shouldBeSkipped(JupiterEngineExecutionContext context) throws Exception {
        System.out.println("In shouldBeSkipped()");
		return SkipResult.doNotSkip();
	}

	@Override
	public JupiterEngineExecutionContext execute(JupiterEngineExecutionContext context,
			DynamicTestExecutor dynamicTestExecutor) throws Exception {
		executeAndMaskThrowable(dynamicTest.getExecutable());
		return context;
	}
}
