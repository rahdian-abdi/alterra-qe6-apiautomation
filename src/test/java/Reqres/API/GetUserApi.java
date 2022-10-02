package Reqres.API;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GetUserApi {
    public static String URL = "https://reqres.in";
    public static String DIR = System.getProperty("user.dir");
    public static String JSON_FILE = DIR+"/src/test/resources/JSON";
    public static String GET_USER = URL+"/api/users/{id}";
    @Step("Get user with valid id")
    public void getUser(int id) {
        SerenityRest.given().pathParam("id", id);
    }
    @Step("Get user with string")
    public void getUserString(String id){
        SerenityRest.given().pathParam("id", id);
    }

}
