package Helper;

public class Item {

	private int key;
	private String value;
	public Item(int key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public int getKey() {
		return key;
	}	
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() { //bulundu�u s�n�f nesne olarak �a��r�ld���nda bashekimgui'deki comboboxta(selectbox) bu �al��acak ve ekranda nesne yerine de�er g�r�necek.
		return this.value;
	}
	
}
