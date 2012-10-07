package net.ffxml.gwt.json.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RpcTest extends GWTTestCase {

	public String getModuleName() {
		return "net.ffxml.gwt.json.JsonRpc";
	}

	public void testSimpleRequest() {
		JsonRpc jsonRpc = new JsonRpc();

		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
				throw (new RuntimeException(caught));
			}

			public void onSuccess(Object result) {
				assertEquals("ok", result);
				finishTest();
			}
		};
		jsonRpc.request("simplerequest.json", "test", null, callback);
		delayTestFinish(20000);
	}

	public void testRequestArray() {
		JsonRpc jsonRpc = new JsonRpc();

		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
				throw (new RuntimeException(caught));
			}

			public void onSuccess(Object result) {
				Object[] languages = (Object[]) result;
				assertEquals(3, languages.length);
				assertEquals("Java", languages[0]);
				finishTest();
			}
		};
		jsonRpc.request("arrayrequest.json", "test", null, callback);
		delayTestFinish(20000);
	}

	public void testError() {
		JsonRpc jsonRpc = new JsonRpc();

		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
				finishTest();
			}

			public void onSuccess(Object result) {
				fail("Error not detected.");
				finishTest();
			}
		};
		jsonRpc.request("errorrequest.json", "test", null, callback);
		delayTestFinish(20000);
	}

	public void testRequestStateListener() {
		JsonRpc jsonRpc = new JsonRpc();

		final List stateLog = new ArrayList();

		JsonRpcRequestStateListener requestStateListener = new JsonRpcRequestStateListener() {
			public void requestStateChanged(boolean requestRunning) {
				stateLog.add(new Boolean(requestRunning));
				if (stateLog.size() == 2) {
					assertEquals(Boolean.TRUE, stateLog.get(0));
					assertEquals(Boolean.FALSE, stateLog.get(1));
					finishTest();
				}
			}
		};
		jsonRpc.addReqestStateListener(requestStateListener);

		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
				finishTest();
			}

			public void onSuccess(Object result) {
				assertEquals(1, stateLog.size());
				assertEquals(Boolean.TRUE, stateLog.get(0));
				if (stateLog.size() != 1) {
					finishTest();
				}
			}
		};
		jsonRpc.request("simplerequest.json", "test", null, callback);
		delayTestFinish(20000);
	}
	
	public void testFailureListener() {
		JsonRpc jsonRpc = new JsonRpc();
		
		JsonRpcFailureListener failureListener = new JsonRpcFailureListener() {
			public void onFailure(Throwable caught) {
				finishTest();
			}
		};
		jsonRpc.addFailureListener(failureListener);
		
		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Object result) {
				fail("Failure exprected.");
			}
		};
		jsonRpc.request("errorrequest.json", "test", null, callback);
		delayTestFinish(20000);
	}
}
