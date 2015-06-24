package cn.wagentim.homeapps.utils;

public class AppUtils
{
	public static final String getOperationName(int opt)
	{
		if( opt > -1 )
		{
			switch(opt)
			{
				case Constants.OPT_ENTITY_SAVE_OR_UPDATE:
					return Constants.OPT_NAME_ENTITY_SAVE_OR_UPDATE;
				case Constants.OPT_DELETE:
					return Constants.OPT_NAME_ENTITY_DELETE;
				case Constants.OPT_ENTITY_GET_ALL:
					return Constants.OPT_NAME_ENTITY_GET_ALL;
				case Constants.OPT_START_GRABBING:
					return Constants.OPT_NAME_START_GRABBING;
				case Constants.OPT_STOP_GRABBING:
					return Constants.OPT_NAME_STOP_GRABBING;
			}
		}
		return Constants.OPT_NAME_UNKNOWN;
	}
}
