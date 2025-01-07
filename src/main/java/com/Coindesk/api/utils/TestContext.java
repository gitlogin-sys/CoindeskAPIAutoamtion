package com.Coindesk.api.utils;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;


import com.Coindesk.api.globalConstants.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
		
	public RequestSpecBuilder requestSpecBuilder;
	public RequestSpecification requestSpecification;
	public HttpStatus expectedStatusCode = HttpStatus.OK;
	public String expectedResponseContentType;

	
	public Response response;
	public Map<String, Object> session = new HashMap<String, Object>();
	private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");
	
	public RequestSpecification requestSetup() {	
		RestAssured.reset();
		Options options = Options.builder().logStacktrace().build();
		RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options); 
		RestAssured.baseURI = PropertiesFile.getProperty("baseURL");	
		return RestAssured.given()
				.config(config)
				.filter(new RestAssuredRequestFilter())				
				.contentType(CONTENT_TYPE)
				.accept(CONTENT_TYPE);
	} 
	
	
	 /**
     * Defines Query Parameters to Request Specification
     *
     * @param key
     * @param value
     * @return this
     */
    public TestContext queryParam(String key, String value) {
        requestSpecBuilder.addQueryParam(key, value);
        return this;
    }
    
    /**
     * Defines Content Type Header to Request Specification
     *
     * @param contentType
     * @return this
     */
    public TestContext contentType(ContentType contentType) {
        requestSpecBuilder.setContentType(contentType);
        return this;
    }

    /**
     * Defines Headers to Request Specification
     *
     * @param headers
     * @return this
     */
    public TestContext headers(Map<String, String> headers) {
        requestSpecBuilder.addHeaders(headers);
        return this;
    }

    /**
     * Defines Cookies to Request Specification
     *
     * @param cookies
     * @return this
     */
    public TestContext cookies(Map<String, String> cookies) {
        requestSpecBuilder.addCookies(cookies);
        return this;
    }

    /**
     * Defines Cookies to Request Specification
     *
     * @param cookies
     * @return this
     */
    public TestContext cookies(Cookies cookies) {
        requestSpecBuilder.addCookies(cookies);
        return this;
    }

    /**
     * Defines Cookie to Request Specification
     *
     * @param cookie
     * @return this
     */
    public TestContext cookie(Cookie cookie) {
        requestSpecBuilder.addCookie(cookie);
        return this;
    }

    /**
     * Defines Body to Request Specification
     *
     * @param body
     * @return this
     */
    public TestContext body(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    /**
     * Defines the Expected Status Code following successful api execution for validation
     *
     * @param expectedStatusCode
     * @return this
     */
    public TestContext expectedStatusCode(HttpStatus expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
        return this;
    }

    /**
     * Defines the Expected Response Content Type following successful api execution for validation
     *
     * @param contentType
     * @return this
     */
    public TestContext expectedResponseContentType(ContentType contentType) {
        this.expectedResponseContentType = contentType.toString();
        return this;
    }

    /**
     * Defines the Expected Response Content Type following successful api execution for validation
     *
     * @param contentType
     * @return this
     */
    public TestContext expectedResponseContentType(String contentType) {
        this.expectedResponseContentType = contentType;
        return this;
    }
    
    public TestContext put() {
        requestSpecification = requestSpecBuilder.build();
        response =
                given()
                        .log().all()
                       // .filter(new APIResponseFilter())
                        .spec(requestSpecification)
                        .when()
                        .put()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as DELETE Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public TestContext delete() {
        requestSpecification = requestSpecBuilder.build();
        response =
                given()
                        .log().all()
                       // .filter(new APIResponseFilter())
                        .spec(requestSpecification)
                        .when()
                        .delete()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as POST Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public TestContext post() {
        requestSpecification = requestSpecBuilder.build();
        response =
                given()
                        .log().all()
                        //.filter(new APIResponseFilter())
                        .spec(requestSpecification)
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Hits the Pre-Defined Request Specification as GET Request
     * <p>
     * On successful response, method validates:
     * -   Status Code against the Status Code provided in Request Specification
     * -   Content Type against the Content Type provided in Request Specification
     *
     * @return this
     */
    public TestContext get() {
        requestSpecification = requestSpecBuilder.build();
        
        response =    given()
                        .log().all()
                        .spec(requestSpecification)
                        .when()
                        .get()
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode.getCode())
                        .contentType(expectedResponseContentType)
                        .and()
                        .extract()
                        .response();

        return this;
    }

    /**
     * Returns the apiResponse Object
     *
     * @return apiResponse
     */
    public Response response() {
        return response;
    }

    /**
     * Returns the apiResponse Object as String
     *
     * @return apiResponse
     */
    public String getApiResponseAsString() {
        return response.asString();
    }
    
       
    
}
