package com.payload.searchproduct;


import com.pojo.product.SearchProduct_POJO;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see Used for Search Product payload 
 */
public class SearchProductPayload 
{
public SearchProduct_POJO searchproductPayload(String text)
{
	SearchProduct_POJO searchProduct_POJO = new SearchProduct_POJO(text);
	return searchProduct_POJO;	
}
}
