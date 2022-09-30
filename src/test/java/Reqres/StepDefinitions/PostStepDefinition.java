package Reqres.StepDefinitions;

import Reqres.API.PostApi;
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

public class PostStepDefinition {
    @Steps
    PostApi post;
    @Given("Post create new user")
    public void postCreateNewUser() {
        File json = new File(PostApi.JSON_FILE+"/PostCreateNewUser.json");
        post.postCreateNewUser(json);
    }
    @When("Send request post new user")
    public void sendRequestPostNewUser() {
        SerenityRest.when().post(PostApi.POST_CREATE_NEW_USER);
    }
    @Then("Should return {int} Created")
    public void shouldReturnCreated(int created) {
        SerenityRest.then().statusCode(created);
    }
    @And("Response body should contain name {string} and job {string}")
    public void responseBodyShouldContainNameAndJob(String name, String job) {
        SerenityRest.then()
                .body(ReqresResponses.NAME, equalTo(name))
                .body(ReqresResponses.JOB, equalTo(job));
    }
    @And("Post create new user json schema validator")
    public void postCreateNewUserJsonSchemaValidator() {
        File json = new File(PostApi.JSON_FILE+"/SchemaValidator/PostUserJsonSchemaValidator.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //10. CREATE USER WITHOUT BODY
    @Given("Post create new user with empty body")
    public void postCreateNewUserWithEmptyBody() {
        File json = new File(PostApi.JSON_FILE+"/PostEmptyUser.json");
        post.postCreateNewUser(json);
    }
    @And("Post user with empty body json schema")
    public void postUserWithEmptyBodyJsonSchema() {
        File json = new File(PostApi.JSON_FILE+"/SchemaValidator/PostUserEmptyBodyJsonSchema,json");

    }

    //11. CREATE USER WITH INVALID PATH
    @When("Send request post new user with invalid path")
    public void sendRequestPostNewUserWithInvalidPath() {
        SerenityRest.when().post(PostApi.POST_CREATE_NEW_USER_INPUT_PARAMETER);
    }
    @And("Create new user with invalid parameter json schema")
    public void createNewUserWithInvalidParameterJsonSchema() {
        File json = new File(PostApi.JSON_FILE+"/SchemaValidator/PostUserInvalidParameterSchemaValidator.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    //12. CREATE USER WITHOUT NAME BODY
    @Given("Post create new user without name body")
    public void postCreateNewUserWithoutNameBody() {
        File json = new File(PostApi.JSON_FILE+"/PostCreateNewUserNoName.json");
        post.postCreateNewUser(json);
    }
    @And("Create new user without name body json schema")
    public void createNewUserWithoutNameBodyJsonSchema() {
        File json = new File(PostApi.JSON_FILE+"/SchemaValidator/PostUserEmptyNameRequestJsonSchemaValidator.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
    //13, CREATE USER WITHOUT JOB BODY
    @Given("Post create new user without job body")
    public void postCreateNewUserWithoutJobBody() {
        File json = new File(PostApi.JSON_FILE+"/PostCreateNewUserNoJob.json");
        post.postCreateNewUser(json);
    }
    @And("Create new user without name job json schema")
    public void createNewUserWithoutNameJobJsonSchema() {
        File json = new File(PostApi.JSON_FILE+"/SchemaValidator/PostUserEmptyJobRequestJsonSchemaValidator.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    // REGISTER USER

    @Given("Post register user")
    public void postRegisterUser() {
        File json = new File(PostApi.JSON_FILE+"/PostRegisterUser.json");
        post.postCreateNewUser(json);
    }

    @When("Send request register")
    public void sendRequestRegister() {
        SerenityRest.when().post(PostApi.POST_REGISTER);
    }

    @And("Response body should contain token {string}")
    public void responseBodyShouldContainTokenToken(String token) {
        SerenityRest.then()
                .body(ReqresResponses.TOKEN, equalTo(token));
    }

    // REGISTER UNSUCCESSFUL
    @Given("Post register user without password")
    public void postRegisterUserWithoutPassword() {
        File json = new File(PostApi.JSON_FILE+"/PostRegisterUnsuccessful.json");
        post.postCreateNewUser(json);
    }

    @And("Response body should contain message {string}")
    public void responseBodyShouldContainMessage(String message) {
        SerenityRest.then()
                .body(ReqresResponses.ERROR_MESSAGE, equalTo(message));
    }


    // LOGIN
    @Given("Login user with valid body request")
    public void loginUserWithValidBodyRequest() {
        File json = new File(PostApi.JSON_FILE+"/PostLoginUser.json");
        post.postCreateNewUser(json);
    }

    @When("Send request login")
    public void sendRequestLogin() {
        SerenityRest.when().post(PostApi.POST_LOGIN);
    }

    // LOGIN UNSUCCESSFUL

    @Given("Login user with invalid body request")
    public void loginUserWithInvalidBodyRequest() {
        File json = new File(PostApi.JSON_FILE+"/PostLoginUnsuccessful.json");
        post.postCreateNewUser(json);
    }
}
