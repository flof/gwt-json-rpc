/*
 * Copyright 2006 Florian Fankhauser f.fankhauser@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */ 

package net.ffxml.gwt.json.client;

import java.util.Arrays;
import java.util.Map;

import com.google.gwt.junit.client.GWTTestCase;

public class JSONDecoderTest extends GWTTestCase {

	public void testNull() {
		JsonRpc json = new JsonRpc();
		Object val = json.decode("null");
		assertNull(val);
	}

	public String getModuleName() {
		return "net.ffxml.gwt.json.JsonRpc";
	}

	public void testString() {
		JsonRpc json = new JsonRpc();
		assertEquals("Florian", json.decode("\"Florian\""));
	}

	public void testInteger() {
		JsonRpc json = new JsonRpc();
		assertEquals(new Integer(14), json.decode("14"));
	}

	public void testWhitespace() {
		JsonRpc json = new JsonRpc();
		assertEquals(new Integer(14), json.decode("\t  14  \n \r \t "));
	}

	public void testBoolean() {
		JsonRpc json = new JsonRpc();
		assertEquals(Boolean.TRUE, json.decode(" true "));
	}

	public void testArray() {
		JsonRpc json = new JsonRpc();
		assertEquals(Arrays.asList(new Object[] { "Florian", new Integer(29),
				Boolean.TRUE }), Arrays.asList((Object[]) json
				.decode("[\"Florian\",29,true]")));
	}

	public void testArrayOfArrays() {
		JsonRpc json = new JsonRpc();
		Object[] innerArray1 = new Object[] { new Integer(1), new Integer(2),
				new Integer(3) };
		Object[] innerArray2 = new Object[] { new Integer(4), new Integer(5),
				new Integer(6) };

		Object[] decoded = (Object[]) json.decode("[ [1, 2, 3], [4, 5, 6] ]");

		assertEquals(Arrays.asList(innerArray1), Arrays
				.asList((Object[]) decoded[0]));
		assertEquals(Arrays.asList(innerArray2), Arrays
				.asList((Object[]) decoded[1]));
	}

	public void testDouble() {
		JsonRpc json = new JsonRpc();
		assertEquals(new Double("3.1415"), json.decode("3.1415"));
	}

	public void testArrayWithNull() {
		JsonRpc json = new JsonRpc();
		Object[] result = (Object[]) json.decode("[1,2,3,null,4]");
		assertEquals(result[1], new Integer(2));
		assertNull(result[3]);
	}

	public void testObject() {
		JsonRpc json = new JsonRpc();
		Map map = (Map) json
				.decode("{\"name\" : \"Florian\", \"age\" : 29, \"gwt-fan\": true, \"children\": null, \"languages\": [\"Java\", \"VisualBasic\", null, 3.1415E-17]}");
		assertEquals(5, map.size());
		assertEquals("Florian", map.get("name"));
		assertEquals(new Integer(29), map.get("age"));
		assertTrue(((Boolean) map.get("gwt-fan")).booleanValue());
		assertNull(map.get("childred"));

		Object[] languages = (Object[]) map.get("languages");
		assertEquals(4, languages.length);
		assertEquals("Java", languages[0]);
		assertEquals("VisualBasic", languages[1]);
		assertNull(languages[2]);
		assertEquals(new Double("3.1415E-17"), languages[3]);
	}

	public void testUnicode() {
		JsonRpc json = new JsonRpc();
		assertEquals("€", json.decode("\"\\u20AC\""));
	}

	public void testNullAndNullString() {
		JsonRpc json = new JsonRpc();
		try {
			json.decode("");
			fail("Nullstring not detected.");
		} catch (Exception e) {
		}
		try {
			json.decode(null);
			fail("Null not detected.");
		} catch (Exception e) {
		}
	}
}
