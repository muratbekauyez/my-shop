package com.example.my_shop.service.init;

import com.example.my_shop.service.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.my_shop.util.constants.ServiceConstants.*;

public class ServiceFactory {
    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    public static ServiceFactory getInstance(){return SERVICE_FACTORY;}

    static {
        SERVICE_MAP.put(LOGIN_SERVICE,new LoginService());
        SERVICE_MAP.put(REGISTER_SERVICE,new RegisterService());
        SERVICE_MAP.put(LOGOUT_SERVICE,new LogoutService());
        SERVICE_MAP.put(CHANGE_LANGUAGE_SERVICE,new ChangeLanguageService());
        SERVICE_MAP.put(ADD_COMPANY_SERVICE,new AddCompanyService());
        SERVICE_MAP.put(ADD_CLOTH_SERVICE,new AddClothService());
        SERVICE_MAP.put(ADD_CLOTH_SIZE_SERVICE, new AddClothSizeService());
        SERVICE_MAP.put(ADD_CLOTH_DETAILS_SERVICE,new AddClothDetailsService());
        SERVICE_MAP.put(EDIT_PROFILE_SERVICE,new EditProfileService());
        SERVICE_MAP.put(EDIT_PASSWORD_SERVICE, new EditPasswordService());
        SERVICE_MAP.put(FILTER_CLOTHES_SERVICE,new FilterClothesService());
        SERVICE_MAP.put(SORT_CLOTHES_SERVICE,new SortClothesService());
        SERVICE_MAP.put(GET_CLOTH_PAGE_SERVICE, new GetClothPageService());
        SERVICE_MAP.put(ADD_REVIEW_SERVICE, new AddReviewService());
        SERVICE_MAP.put(EDIT_REVIEW_SERVICE, new EditReviewService());
        SERVICE_MAP.put(ADD_TO_CART_SERVICE, new AddCartService());
        SERVICE_MAP.put(EDIT_CART_CLOTH_AMOUNT_SERVICE, new EditCartClothAmountService());
        SERVICE_MAP.put(DELETE_CART_SERVICE, new DeleteCartService());
        SERVICE_MAP.put(MAKE_ORDER_SERVICE, new MakeOrderService());
        SERVICE_MAP.put(GET_ORDER_DETAILS_PAGE_SERVICE, new GetOrderDetailsPageService());
        SERVICE_MAP.put(EDIT_COMPANY_SERVICE, new EditCompanyService());
        SERVICE_MAP.put(EDIT_CLOTHES_SERIVCE, new EditClothesService());
        SERVICE_MAP.put(REMOVE_FROM_STORE_SERVICE, new RemoveFromStoreService());
        SERVICE_MAP.put(EDIT_CLOTH_DETAILS_SERVICE, new EditClothDetailsService());
        SERVICE_MAP.put(DELETE_CLOTH_DETAILS_SERVICE, new DeleteClothDetailsService());
        SERVICE_MAP.put(EDIT_USERS_PASSWORD_SERVICE, new EditUsersPasswordService());
        SERVICE_MAP.put(ERROR_SERVICE, new ErrorService());
    }

    public Service getService(String request) {
        Service service = SERVICE_MAP.get(ERROR_SERVICE);

        for (Map.Entry<String, Service> mapElement : SERVICE_MAP.entrySet()){
            if(request.equals(mapElement.getKey())){
                service = SERVICE_MAP.get(request);
            }
        }

        return service;
    }
}
