package challenge.recargapay.tests;

import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostsPostTestCases {
	private RequestSpecification request;

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com/posts";
		request = RestAssured.given();
		request.header("Content-Type", "application/json; charset=UTF-8");
	}

	// Teste para validar retorno com sucesso do comando POST da API
	@Test
	public void verifyPostResponseStatusCode200() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "101");
		requestParams.put("title", "teste");
		requestParams.put("body", "isso é um teste");
		requestParams.put("userId", "8");
		request.body(requestParams.toString());
		Response response = request.post();
		assertTrue(response.statusCode() == 201);
	}

	// Teste para validar retorno de URI inválida do comando POST da API
	@Test
	public void verifyPostResponseStatusCode() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "101");
		requestParams.put("title", "teste");
		requestParams.put("body", "isso é um teste");
		requestParams.put("userId", "8");
		request.body(requestParams.toString());
		Response response = request.post("/posts");
		assertTrue(response.statusCode() == 404);
	}

	// Teste para validar se o body de retorno do comando POST da API possui apenas o ID quando não são passado parâmetros
	@Test
	public void verifyPostWithoutParams() {
		JSONObject requestParams = new JSONObject();
		request.body(requestParams.toString());
		Response response = request.post();
		assertTrue(response.statusCode() == 201 && response.body().path("title") == null);
	}

	// Teste para validar se o body de retorno do comando POST da API possui as infomações fornecidas
	@Test
	public void verifyPostResponseBody() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "101");
		requestParams.put("title", "teste");
		requestParams.put("body", "isso é um teste");
		requestParams.put("userId", "8");
		request.body(requestParams.toString());
		Response response = request.post();
		assertTrue(response.body().path("id").toString().equals("101")
				&& response.body().path("title").toString().equals("teste"));
	}
}
