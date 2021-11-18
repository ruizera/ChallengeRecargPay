package challenge.recargapay.tests;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostsGetTestCases {
	private RequestSpecification request;

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		request = RestAssured.given();
	}

	// Teste para validar retorno com sucesso do comando GET da API
	@Test
	public void verifyGetResponseStatusCode200() {
		Response response = request.get("/posts");
		assertTrue(response.statusCode() == 200);
	}

	// Teste para validar retorno com falha do comando GET da API
	@Test
	public void verifyGetResponseInvalidURI() {
		Response response = request.get("/post");
		assertTrue(response.statusCode() == 404);
	}

	// Teste para validar se o header do comando GET da API existe
	@Test
	public void verifyGetResponseHeader() {
		Response response = request.get("/posts");
		assertTrue(response.headers().exist());
	}

	// Teste para validar se os retornos do comando GET da API possuem o userId informado
	@Test
	public void verifyGetByUserId() {
		Response response = request.get("/posts?userId=1");
		List<LinkedHashMap<String, Object>> responseBodyList = response.jsonPath().getList("$");
		for (LinkedHashMap<String, Object> responseIterator : responseBodyList) {
			assertTrue(Integer.parseInt(responseIterator.get("userId").toString()) == 1);
		}
	}

	// Teste para validar se o body do comando GET da API retorna vazio quando o userId não existe
	@Test
	public void verifyGetByEmptyUserId() {
		Response response = request.get("/posts?userId=0");
		List<LinkedHashMap<String, Object>> responseBodyList = response.jsonPath().getList("$");
		assertTrue(responseBodyList.isEmpty());
	}

}
