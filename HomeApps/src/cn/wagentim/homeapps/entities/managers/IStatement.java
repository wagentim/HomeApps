package cn.wagentim.homeapps.entities.managers;

public interface IStatement
{
	public static final String GET_ORDERS_BY_CUSTOMER = "select o from OrderEntity o WHERE customer = :customer_id";
	public static final String GET_ID_ENTITY_BY_TYPE = "select i from IDEntity i WHERE i.type = :type";
	public static final String GET_USER_BY_USERNAME_AND_PASSWORD = "select u from CustomerEntity u WHERE u.alias = :username AND u.pwd = :password";
	public static final String GET_ORDERS_BY_USER = "select o from OrderEntity o WHERE o.customer = :user_id";
	public static final String GET_ORDER_ITEM = "select item from OrderItemEntity item WHERE item.id = :item_id";
	public static final String DELETE_ALL_ORDERS = "DELETE from OrderEntity o WHERE o.customer = :user_id";
}
