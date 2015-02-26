package cn.wagentim.homeapps.entities;

public interface IEntityStatus
{
	/** Customer Status */
    int CUSTOMER_STATUS_NEW = 0x00;
    int CUSTOMER_STATUS_MODIFY = 0x01;
    int CUSTOMER_STATUS_DELETE = 0x02;
    int CUSTOMER_STATUS_NULL = 0x03;

    /** Order Status */
    int ORDER_STATUS_PREPAREING = 0x00;
    int ORDER_STATUS_NOT_PAID = 0x01;
    int ORDER_STATUS_NOT_SEND = 0x02;
    int ORDER_STATUS_FINISHED = 0x03;
}
