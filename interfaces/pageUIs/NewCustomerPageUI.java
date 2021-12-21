package pageUIs;

public class NewCustomerPageUI {
	public static final String ADDRESS_FIELD = "name=addr";
	public static final String ERROR_ADDRESS="xpath=//textarea[@name='addr']/following-sibling::label";
	public static final String ALL_FIELD_MY_CUSTOMER = "xpath=//tbody//following-sibling::td[contains(text(),'Customer Name')]/following::td[contains(text(),'Gender')]/following::td[contains(text(),'Date of Birth')]/following::td[contains(text(),'Address')]/following::td[contains(text(),'City')]/following::td[contains(text(),'State')]/following::td[contains(text(),'PIN')]/following::td[contains(text(),'Mobile Number')]/following::td[contains(text(),'E-mail')]/following::td[contains(text(),'Password')]";
	public static final String CUSTOMER_ID="xpath=//tbody//td[text()='Customer ID']/following-sibling::td";
}
