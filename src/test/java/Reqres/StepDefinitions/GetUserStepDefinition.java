package Reqres.StepDefinitions;

import Reqres.API.GetUserApi;
import Reqres.ReqresResponses;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import java.io.File;
import static org.hamcrest.Matchers.equalTo;

public class GetUserStepDefinition {
    @Steps
    GetUserApi get;
    //8. GET USER WITH VALID ID
    @Given("Get user with id {int}")
    public void getUserWithIdId(int id) {
        get.getUser(id);
    }
    @When("Send request user")
    public void sendRequestUser() {
        SerenityRest.when().get(GetUserApi.GET_USER);
    }
    @And("Should return email {string}")
    public void shouldReturnNameAnd(String email) {
        SerenityRest
                .then()
                .body(ReqresResponses.EMAIL_USER, equalTo(email));
    }
    @And("Get user with valid id json schema")
    public void getUserWithValidIdJsonSchema() {
        File json = new File(GetUserApi.JSON_FILE+"/SchemaValidator/GetUserValidIdJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
    //9. GET USER WITH NON EXIST ID
    @Then("Should return {int} Not Found")
    public void shouldReturnStatusNotFound(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }
    @And("Should return empty data")
    public void shouldReturnEmptyData() {
        SerenityRest.then().body(ReqresResponses.EMAIL_USER, equalTo(null));
    }
    //10. GET USER WITH INVALID ID
    @Given("Get user with id {string}")
    public void getUserWithId(String id) {
        get.getUserString(id);
    }
}
