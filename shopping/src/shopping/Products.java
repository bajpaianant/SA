package shopping;
import java.util.*;
public class Products {
int pid;
String item_name;
int item_price;
byte[] imageData;
public void setPid(int pid){
	this.pid=pid;
}
public String getItem_name() {
	return item_name;
}
public void setItem_name(String item_name) {
	this.item_name = item_name;
}
public int getItem_price() {
	return item_price;
}
public void setItem_price(int item_price) {
	this.item_price = item_price;
}
public byte[] getImageData() {
	return imageData;
}
public void setImageData(byte[] imageData) {
	this.imageData = imageData;
}
public int getPid() {
	return pid;
}
	

}
