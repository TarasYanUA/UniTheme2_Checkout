package storefront;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public HomePage(){super();}

    public SelenideElement cookie = $(".cm-btn-success");
    public SelenideElement myAccount = $(".ut2-top-my-account .ut2-icon-outline-account-circle");
    public SelenideElement button_LogOut = $(".ut2-top-my-account a[href*='dispatch=auth.logout']");
    public SelenideElement button_SignIn = $(".ut2-top-my-account a[href*='login']");
    public SelenideElement button_SignIn_Popup = $("button[name='dispatch[auth.login]']");
}
