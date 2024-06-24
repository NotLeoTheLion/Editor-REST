package cucumber;

import io.cucumber.java.en.*;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import okhttp3.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {
    private static final String BASE_URL = "http://localhost:8082";
    private static final String CLIENT_PATH = "/clients";
    private Response response;
    private Request request;
    private OkHttpClient client = new OkHttpClient();

    @When("I get all clients")
    public void iGetAllClients() throws IOException {
        request = new Request.Builder()
                .url(BASE_URL + CLIENT_PATH)
                .build();
        Call call = client.newCall(request);
        response = call.execute();
    }

    @Then("I should get a list of clients")
    public void iShouldGetAListOfClients() throws IOException, ParseException {
        String responseBody = response.body().string();
        System.out.println("Response Body: " + responseBody); // Debugging line
        assertThat(responseBody).isNotEmpty();

        JSONParser parser = new JSONParser();
        JSONObject rootObject = (JSONObject) parser.parse(responseBody);

        if (rootObject.isEmpty()) {
            throw new AssertionError("Response body is empty");
        }

        JSONObject embedded = (JSONObject) rootObject.get("_embedded");
        assertThat(embedded).isNotNull();

        assertThat(embedded.get("clientList")).isNotNull();
    }

    @When("I get client by id {int}")
    public void iGetClientById(int id) throws IOException {
        request = new Request.Builder()
                .url(BASE_URL + CLIENT_PATH + "/" + id)
                .build();
        Call call = client.newCall(request);
        response = call.execute();
    }

    @Then("I should get client with id {int}")
    public void iShouldGetClientWithId(int id) throws IOException, ParseException {
        String responseBody = response.body().string();
        System.out.println("Response Body: " + responseBody); // Debugging line

        if (responseBody.isEmpty()) {
            throw new AssertionError("Response body is empty");
        }

        JSONParser parser = new JSONParser();
        JSONObject client = (JSONObject) parser.parse(responseBody);

        assertThat(client).isNotNull();
        assertThat(client.getAsNumber("id").intValue()).isEqualTo(id);
    }

    @When("I add new client with first name {string} last name {string} phone number {string} email {string}")
    public void iAddNewClient(String firstName, String lastName, String phoneNumber, String email) throws IOException {
        JSONObject client = new JSONObject();
        client.put("firstname", firstName);
        client.put("lastname", lastName);
        client.put("phonenumber", phoneNumber);
        client.put("email", email);

        RequestBody body = RequestBody.create(
                client.toJSONString(), MediaType.parse("application/json"));

        request = new Request.Builder()
                .url(BASE_URL + CLIENT_PATH)
                .post(body)
                .build();
        Call call = this.client.newCall(request);
        response = call.execute();
    }

    @Then("I should get client with first name {string} last name {string} phone number {string} email {string}")
    public void iShouldGetClient(String firstName, String lastName, String phoneNumber, String email) throws IOException, ParseException {
        String responseBody = response.body().string();
        System.out.println("Response Body: " + responseBody); // Debugging line

        if (responseBody.isEmpty()) {
            throw new AssertionError("Response body is empty");
        }

        JSONParser parser = new JSONParser();
        JSONObject client = (JSONObject) parser.parse(responseBody);

        assertThat(client).isNotNull();
        assertThat(client.get("firstname")).isEqualTo(firstName);
        assertThat(client.get("lastname")).isEqualTo(lastName);
        assertThat(client.get("phonenumber")).isEqualTo(phoneNumber);
        assertThat(client.get("email")).isEqualTo(email);
    }

    @When("I update client with id {int} to first name {string} last name {string} phone number {string} email {string}")
    public void iUpdateClient(int id, String firstName, String lastName, String phoneNumber, String email) throws IOException {
        JSONObject client = new JSONObject();
        client.put("firstname", firstName);
        client.put("lastname", lastName);
        client.put("phonenumber", phoneNumber);
        client.put("email", email);

        RequestBody body = RequestBody.create(
                client.toJSONString(), MediaType.parse("application/json"));

        request = new Request.Builder()
                .url(BASE_URL + CLIENT_PATH + "/" + id)
                .put(body)
                .build();
        Call call = this.client.newCall(request);
        response = call.execute();
    }

    @Then("I should get updated client with first name {string} last name {string} phone number {string} email {string}")
    public void iShouldGetUpdatedClient(String firstName, String lastName, String phoneNumber, String email) throws IOException, ParseException {
        String responseBody = response.body().string();
        System.out.println("Response Body: " + responseBody); // Debugging line

        if (responseBody.isEmpty()) {
            throw new AssertionError("Response body is empty");
        }

        JSONParser parser = new JSONParser();
        JSONObject client = (JSONObject) parser.parse(responseBody);

        assertThat(client).isNotNull();
        assertThat(client.get("firstname")).isEqualTo(firstName);
        assertThat(client.get("lastname")).isEqualTo(lastName);
        assertThat(client.get("phonenumber")).isEqualTo(phoneNumber);
        assertThat(client.get("email")).isEqualTo(email);
    }

    @When("I delete client with id {int}")
    public void iDeleteClient(int id) throws IOException {
        request = new Request.Builder()
                .url(BASE_URL + CLIENT_PATH + "/" + id)
                .delete()
                .build();
        Call call = this.client.newCall(request);
        response = call.execute();
    }

    @Then("I should not be able to get client with id {int}")
    public void iShouldNotBeAbleToGetClient(int id) throws IOException {
        iGetClientById(id);
        assertThat(response.code()).isEqualTo(404);
    }
}
