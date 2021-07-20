package qaops.automation.api.restTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthBearerTokenTest {
    //TO DO: refactor
    @Test
    public void bearerTokenAuthTest() {
        RestAssured.baseURI = "http://bookstore.toolsqa.com";
        RequestSpecification request = RestAssured.given();
        String payload = "{\r\n" +
                "  \"userName\": \"TOOLSQA-Test\",\r\n" +
                "  \"password\": \"Test@@123\"\r\n" +
                "}";

        request.header("Content-Type", "application/json");

        Response responseFromGenerateToken = request.body(payload).post("/Account/v1/GenerateToken");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("token");
        System.out.println(tokenGenerated);

        //do request with generated token
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        String addBookDetails = "{\r\n" +
                "  \"userId\": \"a178a104-e3c0-4f50-bbd5-3e4919f063e8\",\r\n" +
                "  \"collectionOfIsbns\": [\r\n" +
                "    {\r\n" +
                "      \"isbn\": \"9781449325862\"\r\n" +
                "    }\r\n" +
                "  ]\r\n" +
                "}";
        Response addBooksResponse = request.body(addBookDetails).post("/BookStore/v1/Books");
        Assertions.assertThat(addBooksResponse.getStatusCode()).isEqualTo(201);
        addBooksResponse.prettyPrint();

    }
}
