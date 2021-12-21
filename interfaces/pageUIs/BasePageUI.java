package pageUIs;

public class BasePageUI {
	public static final String DYNAMIC_MENU_SUB_TAB_BY_NAME ="xpath=//ul[@class='menusubnav']/li/a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "xpath=//tbody//input[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_BY_FIELD_NAME ="xpath=//td/input[@name='%s']/following-sibling::label";
	public static final String DYNAMIC_BUTTON_BY_VALUE ="xpath=//input[@value='%s']";
}
