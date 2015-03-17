package cn.wagentim.homeapps.entities.managers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.wagentim.homeapps.entities.CustomerEntity;
import cn.wagentim.homeapps.entities.IEntity;
import cn.wagentim.homeapps.entities.OrderEntity;
import cn.wagentim.homeapps.entities.ProductEntity;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.Validator;

public final class EntityFactory
{
	public static final IEntity createEntity(final int type, final HttpServletRequest request)
	{
		switch(type)
		{
			case Constants.ENTITY_CUSTOMER:
				return getCustomerEntity(request);
			case Constants.ENTITY_PRODUCT:
			    return getProductEntity(request);
			case Constants.ENTITY_ORDER:
			    return getOrder(request);
			default:
				return null;
		}
	}

	private static OrderEntity getOrder(HttpServletRequest request)
    {

	    return null;
    }

    private static ProductEntity getProductEntity(final HttpServletRequest request)
    {
        ProductEntity product = new ProductEntity();

        String prodID = request.getParameter(Constants.ID);

        if( Validator.isNull(prodID) )
        {
            // DO NOTHING
        }
        else
        {
            Long id = Long.valueOf(prodID);
            product.setId(id);
        }

        product.setName(request.getParameter(Constants.PRODUCT_NAME));
        product.setCategorie(Integer.valueOf(request.getParameter(Constants.PRODUCT_CATEGORIE)));
        product.setDefaultAmount(Integer.valueOf(request.getParameter(Constants.PRODUCT_DEFAULT_AMOUNT)));
        product.setDefaultPrice(Double.valueOf(request.getParameter(Constants.PRODUCT_DEFAULT_PRICE)));
        product.setNettoWeigth(Integer.valueOf(request.getParameter(Constants.PRODUCT_NETTO_WEIGHT)));

        return product;
    }

    private static CustomerEntity getCustomerEntity(final HttpServletRequest request)
	{
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameters = request.getParameterMap();

    	CustomerEntity customer = new CustomerEntity();

    	String[] userID = parameters.get(Constants.ID);

    	if( Validator.isNull(userID) )
    	{
    	}
    	else
    	{
    		Long id = Long.valueOf(userID[0]);
    		customer.setId(id);
    	}

    	customer.setAlias(parameters.get(Constants.CUSTOMER_ALISA)[0]);
    	customer.setFirstName(parameters.get(Constants.CUSTOMER_FIRST_NAME)[0]);
    	customer.setLastName(parameters.get(Constants.CUSTOMER_LAST_NAME)[0]);
    	customer.setTelefon(parameters.get(Constants.CUSTOMER_TELEPHONE)[0]);
    	customer.setEmail(parameters.get(Constants.CUSTOMER_EMAIL)[0]);
    	customer.setCountry(parameters.get(Constants.CUSTOMER_COUNTRY)[0]);
    	customer.setProvince(parameters.get(Constants.CUSTOMER_PROVINCE)[0]);
    	customer.setCity(parameters.get(Constants.CUSTOMER_CITY)[0]);
    	customer.setZipcode(parameters.get(Constants.CUSTOMER_ZIPCODE)[0]);
    	customer.setAddress(parameters.get(Constants.CUSTOMER_ADDRESS)[0]);

    	return customer;
	}
}
