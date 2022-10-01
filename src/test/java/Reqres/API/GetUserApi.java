package Reqres.API;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GetUserApi {
    public static String URL = "https://reqres.in";
    public static String GET_LIST_USER = URL+"/api/users?page={page}";
    public static String GET_ALL_USER = URL+"/api/{users}";
    public static String GET_ALL_USER_EMPTY_PAGE = URL+"/api/users?{page}=";
    public static String GET_ALL_USER_WRONG_PAGE_TITLE = URL+"/api/users?pagexxx={page}";
    public static String GET_ALL_USER_WITHOUT_QUEST_MARK = URL+"/api/userspage={page}";
    public static String GET_USER = URL+"/api/users/{id}";
    public static String DIR = System.getProperty("user.dir");
    public static String JSON_FILE = DIR+"/src/test/resources/JSON";


    @Step("Get list user")
    public void getListUser(int page) {
        SerenityRest.given().pathParam("page", page);
    }
    @Step("Get list all user")
    public void getListAllUser(String users){
        SerenityRest.given().pathParam("users", users);
    }
    @Step("Get list user wihtout page number")
    public void getAllListUserWithoutPageNumber(String page){
        SerenityRest.given().pathParam("page", page);
    }
    @Step("Get list user with string")
    public void getListUserWithString(String page) {
        SerenityRest.given().pathParam("page", page);
    }
    @Step("Get list user wrong sequence")
    public void getListUserWrongSequence(int page){
        SerenityRest.given().pathParam("page", page);
    }
    @Step("Get user with valid id")
    public void getUser(int id) {
        SerenityRest.given().pathParam("id", id);
    }
    @Step("Get user with string")
    public void getUserString(String id){
        SerenityRest.given().pathParam("id", id);
    }

}
