package pageUIs;

public class NewCustomerPageUI {
	public static final String DATE_OF_BIRTH_TEXTAREA = "name=addr";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "xpath=//tbody//input[@name='%s']";
	public static final String ALL_FIELD_MY_CUSTOMER = "xpath=//tbody//following-sibling::td[contains(text(),'Customer Name')]/following::td[contains(text(),'Gender')]/following::td[contains(text(),'Date of Birth')]/following::td[contains(text(),'Address')]/following::td[contains(text(),'City')]/following::td[contains(text(),'State')]/following::td[contains(text(),'PIN')]/following::td[contains(text(),'Mobile Number')]/following::td[contains(text(),'E-mail')]/following::td[contains(text(),'Password')]";
}
