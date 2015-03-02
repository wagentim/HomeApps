package cn.wagentim.homeapps.utils;

public final class StatementHelper
{
	public static final String jpaGetAllEntity(Class<?> entity)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select e from ");
		sb.append(entity.getSimpleName());
		sb.append(" e;");
		return sb.toString();
	}
}
