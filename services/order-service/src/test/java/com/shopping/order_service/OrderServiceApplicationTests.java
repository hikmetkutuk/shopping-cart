package com.shopping.order_service;

import com.shopping.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@AutoConfigureWireMock(port = 0)
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15.5");

	@LocalServerPort
	private int port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgresContainer.start();
	}
	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
                {
                     "skuCode": "book_8",
                     "price": 1000,
                     "quantity": 1
                }
                """;
		InventoryClientStub.stubInventoryCall("book_8", 1);

		RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order/create")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

	}

	@Test
	void shouldFailOrderWhenProductIsNotInStock() {
		String submitOrderJson = """
                {
                     "skuCode": "book_8",
                     "price": 1000,
                     "quantity": 1000
                }
                """;
		InventoryClientStub.stubInventoryCall("book_8", 1000);

		RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order/create")
				.then()
				.log().all()
				.statusCode(500);
	}
}
