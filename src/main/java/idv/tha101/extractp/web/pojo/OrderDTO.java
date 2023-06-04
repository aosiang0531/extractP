package idv.tha101.extractp.web.pojo;

public interface OrderDTO {

	String getProduct_name();
	byte[] getProduct_image();
	Integer getOrder_detail_quantity();
	double getOrder_product_price();
	Integer getOrder_id();
	Integer getOrder_detail_id();
	double getSubtotal();
	Integer getProduct_id();
	String getStatus();

}
