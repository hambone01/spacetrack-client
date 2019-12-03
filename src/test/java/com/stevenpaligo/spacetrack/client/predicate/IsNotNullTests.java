/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevenpaligo.spacetrack.client.predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.EqualsAndHashCode;

public class IsNotNullTests {

  @Test
  @DisplayName("IsNotNull: Constructor parameter validation")
  public void test1() {

    // disallowed values
    assertThrows(IllegalArgumentException.class, () -> {
      new IsNotNull<>(null);
    });


    // allowed values
    assertDoesNotThrow(() -> {
      new IsNotNull<>(new TestQueryField());
    });
  }


  @Test
  @DisplayName("IsNotNull: Query parameter format and correct contents")
  public void test2() {

    assertEquals("NORAD_CAT_ID/<>null-val", new IsNotNull<>(new TestQueryField()).toQueryParameter());
  }


  @Test
  @DisplayName("IsNotNull: Getters")
  public void test3() {

    // field
    assertEquals(new TestQueryField(), new IsNotNull<>(new TestQueryField()).getField());
  }


  @EqualsAndHashCode
  private static class TestQueryField implements QueryField {

    @Override
    public String getQueryFieldName() {
      return "NORAD_CAT_ID";
    }
  }
}
