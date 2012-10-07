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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.google.gwt.junit.client.GWTTestCase;

public class JSONEncoderTestCase extends GWTTestCase {

	public String getModuleName() {
		return "net.ffxml.gwt.json.JsonRpc";
	}

	public void testNull() {
		JsonRpc json = new JsonRpc();
		assertEquals("null", json.encode(null));
	}

	public void testString() {
		JsonRpc json = new JsonRpc();
		assertEquals("\"String\"", json.encode("String"));
	}

	public void testEmptyString() {
		JsonRpc json = new JsonRpc();
		assertEquals("\"\"", json.encode(""));
	}

	public void testInteger() {
		JsonRpc json = new JsonRpc();
		assertEquals("13", json.encode(new Integer(13)));
	}

	public void testDouble() {
		JsonRpc json = new JsonRpc();
		assertEquals("1.45E16", json.encode(new Double(14.5E+15)));
	}

	public void testNegativeDouble() {
		JsonRpc json = new JsonRpc();
		assertEquals("-1.45E16", json.encode(new Double(-14.5E+15)));
	}

	public void testBoolean() {
		JsonRpc json = new JsonRpc();
		assertEquals("true", json.encode(Boolean.TRUE));
	}

	public void testArray() {
		JsonRpc json = new JsonRpc();
		assertEquals("[\"Florian\",\"Fankhauser\",29,true]", json
				.encode(new Object[] { "Florian", "Fankhauser",
						new Integer(29), Boolean.TRUE }));
	}

	public void testPrimitiveIntArray() {
		JsonRpc json = new JsonRpc();
		assertEquals("[1,2,3]", json.encode(new int[] { 1, 2, 3 }));
	}

	public void testPrimitiveBooleanArray() {
		JsonRpc json = new JsonRpc();
		assertEquals("[true,false,true]", json.encode(new boolean[] { true,
				false, true }));
	}

	public void testArrayOfArrays() {
		JsonRpc json = new JsonRpc();
		assertEquals("[[1,2,3],[4,5,6]]", json.encode(new Object[] {
				new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 } }));
	}

	public void testList() {
		JsonRpc json = new JsonRpc();
		List arrayList = new ArrayList();
		arrayList.add(new Integer(3));
		arrayList.add("Florian");
		arrayList.add(Boolean.FALSE);
		assertEquals("[3,\"Florian\",false]", json.encode(arrayList));
	}

	public void testDate() {
		JsonRpc json = new JsonRpc();
		Date testDate = new Date();
		assertEquals(String.valueOf(testDate.getTime()), json.encode(testDate));
	}

	public void testSet() {
		JsonRpc json = new JsonRpc();
		Set set = new HashSet();
		set.add(new Integer(3));
		set.add("Florian");
		assertTrue("[\"Florian\",3]".equals(json.encode(set))
				|| "[3,\"Florian\"]".equals(json.encode(set)));
	}

	public void testVector() {
		JsonRpc json = new JsonRpc();
		Vector vector = new Vector();
		vector.add(new Integer(3));
		vector.add("Florian");
		vector.add(Boolean.FALSE);
		assertEquals("[3,\"Florian\",false]", json.encode(vector));
	}

	public void testObject() {
		JsonRpc json = new JsonRpc();
		Map map = new HashMap();
		map.put("name", "Florian");
		map.put("age", new Integer(29));
		map.put("gwt-fan", Boolean.TRUE);
		map.put("prog-langs", new String[] { "Java", "TurboPascal" });

		String jsonData = json.encode(map);
		Map result = (Map) json.decode(jsonData);
		assertEquals("Florian", result.get("name"));
		assertEquals(new Integer(29), result.get("age"));
		Object[] arr = (Object[]) result.get("prog-langs");
		assertEquals("Java", arr[0]);
		assertEquals("TurboPascal", arr[1]);
	}
}
