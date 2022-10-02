package Reqres.StepDefinitions;

import Reqres.API.PostLoginUserApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import java.io.File;

public class PostLoginUserStepDefinition {

    @Steps
    PostLoginUserApi postLogin;

    // LOGIN
    @Given("Login user with valid body request")
    public void loginUserWithValidBodyRequest() {
        File json = new File(PostLoginUserApi.JSON_FILE+"/BodyRequest/PostLoginUser.json");
        postLogin.postLoginUser(json);
    }

    @When("Send request login")
    public void sendRequestLogin() {
        SerenityRest.when().post(PostLoginUserApi.POST_LOGIN);
    }
    @And("Login user successful json schema")
    public void loginUserSuccessfulJsonSchema() {
        File json = new File(PostLoginUserApi.JSON_FILE+"/SchemaValidator/PostLoginUserSuccessfulJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    // LOGIN UNSUCCESSFUL

    @Given("Login user with invalid body request")
    public void loginUserWithInvalidBodyRequest() {
        File json = new File(PostLoginUserApi.JSON_FILE+"/BodyRequest/PostLoginUnsuccessful.json");
        postLogin.postLoginUser(json);
    }
    @And("Login user unsuccessful json schema")
    public void loginUserUnsuccessfulJsonSchema() {
        File json = new File(PostLoginUserApi.JSON_FILE+"/SchemaValidator/PostLoginUserUnsuccessfulJsonSchema.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }
}
