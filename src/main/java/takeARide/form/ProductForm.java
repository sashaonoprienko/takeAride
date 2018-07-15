package takeARide.form;

public class ProductForm {
	private Integer idProduct;
	private Integer count;
	private String fullName;
	private String passportSeries;
	private int needDriver;
	public ProductForm() {
		super();
	}
	public ProductForm(Integer idProduct, Integer count) {
		super();
		this.idProduct = idProduct;
		this.count = count;
	}
	public ProductForm(Integer idProduct, Integer count,String passportSeries,String fullName,int needDriver) {
		super();
		this.idProduct = idProduct;
		this.count = count;
		this.passportSeries = passportSeries;
		this.fullName = fullName;
		this.needDriver = needDriver;
	}
	
	public int getNeedDriver() {
		return needDriver;
	}
	public void setNeedDriver(int needDriver) {
		this.needDriver = needDriver;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassportSeries() {
		return passportSeries;
	}
	public void setPassportSeries(String passportSeries) {
		this.passportSeries = passportSeries;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
