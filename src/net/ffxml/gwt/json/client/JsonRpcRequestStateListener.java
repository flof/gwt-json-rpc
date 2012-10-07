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

public interface JsonRpcRequestStateListener {

	/**
	 * Notifies the listener that the requestRunning state has changed.
	 * <code>requestRunning</code> is true when there is at least one
	 * currently running request or false if there is no running request.
	 * 
	 * @param requestRunning
	 */
	void requestStateChanged(boolean requestRunning);
}
