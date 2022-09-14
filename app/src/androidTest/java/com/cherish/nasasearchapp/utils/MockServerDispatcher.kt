package com.cherish.nasasearchapp.utils

import com.cherish.nasasearchapp.utils.TestUtils.getJsonContent
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {

    internal inner class RequestDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(200)
                .setBody(getJsonContent("milky_way_test_response.json"))

        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400).setBody(getJsonContent("response_error.json"))
        }
    }
}