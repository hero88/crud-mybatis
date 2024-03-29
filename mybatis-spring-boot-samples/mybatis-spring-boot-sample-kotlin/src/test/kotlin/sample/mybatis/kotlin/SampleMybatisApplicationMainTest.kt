/*
 *    Copyright 2015-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package sample.mybatis.kotlin

import extensions.kotlin.CaptureSystemOutput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@CaptureSystemOutput
class SampleMybatisApplicationMainTest {

  @Test
  fun test(outputCapture: CaptureSystemOutput.OutputCapture) {
    main(arrayOf())
    val output = outputCapture.toString()
    assertThat(output).contains("City(id=1, name=San Francisco, state=CA, country=US)")
  }

}
