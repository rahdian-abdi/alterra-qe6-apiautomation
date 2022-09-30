package Reqres.StepDefinitions;

import Reqres.API.GetApi;
import Reqres.ReqresResponses;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Steps;
import io.restassured.module.jsv.JsonSchemaValidator;


import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class GetStepDefinition {
    @Steps
    GetApi get;

    //1. GET LIST USER WITH VALID PAGE QUERY
    @Given("Get list user with parameter page {int}")
    public void get_list_user_with_parameter_page(int page) {
        get.getListUser(page);
    }
    @When("Send request get list user")
    public void send_request_get_list_user() {
        SerenityRest.when().get(GetApi.GET_LIST_USER);
    }
    @Then("Should return {int} OK")
    public void should_return_ok(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }
    @And("Response body page should be {int}")
    public void responseBodyPageShouldBe(int pageNumber) {
        SerenityRest.then().body(ReqresResponses.PAGE, equalTo(pageNumber));
    }
    @And("Get list user json schema validator")
    public void getListUserJsonSchemaValidator() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListUserJsonSchemaValidator.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //2. GET ALL LIST USER
    @When("Send request get all user")
    public void sendRequestGetAllUser() {
        SerenityRest.when().get(GetApi.GET_ALL_USER);
    }
    @And("Get list all user with json schema validator")
    public void getListAllUserWithJsonSchemaValidator() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListAllUserJsonSchemaValidator.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //3. GET USER WITH EMPTY PAGE QUERY
    @When("Send request get list user without page query")
    public void sendRequestGetListUserWithoutPageQuery() {
        SerenityRest.when().get(GetApi.GET_ALL_USER_EMPTY_PAGE);
    }
    @Then("Should return {int} Bad Request")
    public void shouldReturnStatusBadRequest(int status) {
        SerenityRest.then().statusCode(status);
    }
    @And("Get list user without page query json schema")
    public void getListUserWithoutPageQueryJsonSchema() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListUserWithoutPageQueryJsonSchema.json");
    }

    //4. GET LIST USER WITH NON EXIST PAGE
    @And("Response body should contain empty data")
    public void responseBodyShouldContainEmptyData() {
        SerenityRest.then()
                .body(ReqresResponses.EMAIL, equalTo(null));
    }
    @And("Get list user with non exist page json schema")
    public void getListUserWithNonExistPageJsonSchema() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListUserNonExistPageJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //5. GET LIST USER WITH INVALID PAGE
    @Given("Get list user with parameter page {string}")
    public void getListUserWithParameterPage(String param) {
        get.getListUserWithString(param);
    }
    @And("Get list user with invalid page json schema")
    public void getListUserWithInvalidPageJsonSchema() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListUserInvalidPageJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //6. GET LIST USER WITH INVALID PAGE TITLE
    @When("Send request with wrong page title")
    public void sendRequestWithWrongPageTitle() {
        SerenityRest.when().get(GetApi.GET_ALL_USER_WRONG_PAGE_TITLE);
    }
    @And("Get list user with wrong title page json schema")
    public void getListUserWithWrongTitlePageJsonSchema() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListUserWrongPageTitleParameter.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //7. GET LIST USER WITHOUT QUESTION MARK
    @When("Send request without question mark")
    public void sendRequestWithoutQuestionMark() {
        SerenityRest.when().get(GetApi.GET_ALL_USER_WITHOUT_QUEST_MARK);
    }

    @And("Get list user without question mark json schema")
    public void getListUserWithoutQuestionMarkJsonSchema() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetListUserWithoutQuestionMarkJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //8. GET USER WITH VALID ID
    @Given("Get user with id {int}")
    public void getUserWithIdId(int id) {
        get.getUser(id);
    }
    @When("Send request user")
    public void sendRequestUser() {
        SerenityRest.when().get(GetApi.GET_USER);
    }
    @And("Should return email {string}")
    public void shouldReturnNameAnd(String email) {
        SerenityRest
                .then()
                .body(ReqresResponses.EMAIL_USER, equalTo(email));
    }
    @And("Get user with valid id json schema")
    public void getUserWithValidIdJsonSchema() {
        File json = new File(GetApi.JSON_FILE+"/SchemaValidator/GetUserValidIdJsonSchema.json");
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
