/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model for Smart Browse
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MBrowse extends X_AD_Browse 
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2835749910242526282L;
	/**	Logger	*/
	private static CLogger	s_log = CLogger.getCLogger (MBrowse.class);
	private MView m_view = null;
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param AD_SmartBrowse_ID  InOutBound ID
	 *	@param trxName transaction name 
	 */
	public MBrowse (Properties ctx, int AD_SmartBrowse_ID, String trxName)
	{
		super (ctx, AD_SmartBrowse_ID, trxName);
		if (AD_SmartBrowse_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param AD_SmartBrowse_ID Cahs Flow ID
	 */
	public MBrowse (Properties ctx, int AD_SmartBrowse_ID)
	{
		this (ctx, AD_SmartBrowse_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MBrowse (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAsset

	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer (" MSmartBrowse[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * get Criteria Fields
	 * @return
	 */
	public Collection <MBrowseField> getCriteriaFields()
	{
				
		String whereClause = MBrowseField.COLUMNNAME_AD_Browse_ID + "=? AND "
							+ MBrowseField.COLUMNNAME_IsDisplayed 	+ "=? AND "
							+ MBrowseField.COLUMNNAME_IsQueryCriteria 	+ "=?";
						
		
		return new Query(getCtx(),MBrowseField.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{get_ID(),"Y","Y"})
		.setOnlyActiveRecords(true)
		.setOrderBy(MBrowseField.COLUMNNAME_SeqNo)
		.list();		
	}
	
	/**
	 * get Criteria Fields
	 * @return
	 */
	public Collection <MBrowseField> getFields()
	{
				
		String whereClause = MBrowseField.COLUMNNAME_AD_Browse_ID + "=? AND "
							+ MBrowseField.COLUMNNAME_IsDisplayed 	+ "=? ";
						
		
		return new Query(getCtx(),MBrowseField.Table_Name, whereClause, get_TrxName())
		.setParameters(new Object[]{get_ID(),"Y"})
		.setOnlyActiveRecords(true)
		.setOrderBy(MBrowseField.COLUMNNAME_SeqNo)
		.list();		
	}
	
	/**
	 * get field using name
	 * @param name field
	 * @return field 
	 */
	public MBrowseField getField(String name)
	{
		for (MBrowseField field : getFields())
		{
			if(field.getName().equals(name))
			{
				return field;
			}
		}
		return null;
	}
	
	public String getQuery()
	{
		String sqlSelect = "SELECT";
		String sqlFrom  = "FROM ";
		String sqlJoin  = "";
		String whereClause = "WHERE";
		/*MView view = new MView(getCtx(), p_Record_ID, get_TrxName());
		Collection <MViewJoin> joins = view.getSmartViewJoins();		
		for(MViewJoin join:joins)
		{
			String tableName = join.getTableName();
			int AD_Table_ID = join.getAD_Table_ID();
		}
		return "";*/
		return null;	
	}
	
	/**
	 * get MView
	 */
	public MView getAD_View()
	{
		if(m_view == null)
		{
			m_view = new MView(getCtx(), getAD_View_ID(), get_TrxName());
		}
		return m_view;
	}
}	