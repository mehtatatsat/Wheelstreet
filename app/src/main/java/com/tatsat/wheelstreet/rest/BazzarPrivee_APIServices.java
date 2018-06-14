package com.tatsat.wheelstreet.rest;


import com.tatsat.wheelstreet.models.Questions;

import java.util.Map;

import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


/**
 * Created by Shivlabs on 8/12/2016.
 */
public interface BazzarPrivee_APIServices
{


//    /**
//     * Register API
//     *
//     * @param map
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/registration")
//    void api_userRegister(@FieldMap Map<String, String> map, CancelableCallback<UserRegisterMainResponce> callback);
//
//
//       /**
// * Login API
// *
// * @param map
// *
// */
//    @FormUrlEncoded
//    @POST("/login")
//    void api_userLogin(@FieldMap Map<String, String> map, CancelableCallback<UserRegisterMainResponce> callback);


    /**
            * Api  forgot_password
    * *
            * @param
    * @param callback
    */
    @FormUrlEncoded
    @POST("/forgot_password")
    void api_forgot_password(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);

    /**
     * Api  change_password
     * *
     * @param
     * @param callback
     */
    @FormUrlEncoded
    @POST("/change_password")
    void api_change_password(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);


    /**
     * Api  homepage_apis
     * *
     * @param
     * @param callback
     */

    @GET("/questions")
    void api_homepage_apis(CancelableCallback<Questions> callback);

    /**
     * Api  my_addresses
     * *     * @param
     * @param callback
     */
    @FormUrlEncoded
    @POST("/answers")
    void api_get_address_list(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);


//
//    /**
//     * Api  add_address
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/add_address")
//    void api_add_address(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//    /**
//     * Api  delete_address
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/delete_address")
//    void api_delete_address(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//    /**
//     * Api  my_addresses
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/get_products_list")
//    void api_get_products_list(@FieldMap Map<String, String> map, CancelableCallback<BrandProductList> callback);
//
//
//    /**
//     * Api  get_product_detail
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/get_product_detail")
//    void api_get_product_detail(@FieldMap Map<String, String> map, CancelableCallback<GetProductDetail> callback);
//
//    /**
//     * Api  todays_special_offers
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/todays_special_offers")
//    void api_todays_special_offers(@FieldMap Map<String, String> map, CancelableCallback<ToadaysAlloffers> callback);
//
//
//
//    /**
//     * Api  get_category_detail
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/get_category_detail")
//    void api_get_category_detail(@FieldMap Map<String, String> map, CancelableCallback<SubcategoryListModel> callback);
//
//    /**
//     * Api  get_group_detail
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/get_group_detail")
//    void api_get_group_detail(@FieldMap Map<String, String> map, CancelableCallback<TodaysDetail> callback);
//
//
//    /**
//     * Api  get_group_detail
//     * *
//     * @param
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/check_pincode")
//    void api_check_pincode(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//
//    @FormUrlEncoded
//    @POST("/add_single_cart")
//    void api_add_single_cart(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//
//    @FormUrlEncoded
//    @POST("/remove_cart")
//    void api_remove_cart(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//
//    @FormUrlEncoded
//    @POST("/get_delivery_datetime")
//    void api_get_delivery_datetime(@FieldMap Map<String, String> map, CancelableCallback<GetTimeDate> callback);
//
//
//    @FormUrlEncoded
//    @POST("/order_place")
//    void api_order_place(@FieldMap Map<String, String> map, CancelableCallback<PlaceOrder> callback);
//
//
//
//    @FormUrlEncoded
//    @POST("/get_my_orders")
//    void api_get_my_orders(@FieldMap Map<String, String> map, CancelableCallback<MyOrders> callback);
//
//
//    @FormUrlEncoded
//    @POST("/cancel_order")
//    void api_cancel_order(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//    @FormUrlEncoded
//    @POST("/notify_me")
//    void api_notify_me(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//    @FormUrlEncoded
//    @POST("/add_multiple_cart")
//    void api_add_multiple_cart(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//
//    @FormUrlEncoded
//    @POST("/get_my_cart")
//    void api_get_my_cart(@FieldMap Map<String, String> map, CancelableCallback<GetMyCart> callback);
//
//
//    @FormUrlEncoded
//    @POST("/set_is_current")
//    void api_set_is_current(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//
//    @FormUrlEncoded
//    @POST("/log_out")
//    void api_log_out(@FieldMap Map<String, String> map, CancelableCallback<retrofit.client.Response> callback);
//
//
//    @FormUrlEncoded
//    @POST("/social_login")
//    void api_social_login(@FieldMap Map<String, String> map, CancelableCallback<UserRegisterMainResponce> callback);
//
//
//    @FormUrlEncoded
//    @POST("/get_all_pincode")
//    void api_get_all_pincode(@FieldMap Map<String, String> map, CancelableCallback<GetPincode> callback);

}
