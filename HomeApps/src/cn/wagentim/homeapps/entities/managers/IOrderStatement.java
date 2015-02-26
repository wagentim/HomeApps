package cn.wagentim.homeapps.entities.managers;

public interface IOrderStatement
{
	public static final String GET_ORDERS_BY_CUSTOMER = "select o from OrderEntity o WHERE customer = :customer_id";
}
