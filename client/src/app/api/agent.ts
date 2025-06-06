import { Brand } from './../models/Brand';
import axios, { AxiosError, AxiosResponse } from "axios";
import { router } from "../router/Routes";
import { toast } from "react-toastify";
import { Product } from "../models/product";
import { Basket } from "../models/basket";
import { Dispatch } from "redux";
import basketService from "./basketService";
import { Search } from "@mui/icons-material";

axios.defaults.baseURL = "http://localhost:8081/api/";

const idle = () => new Promise((r) => setTimeout(r, 500));

const responseBody = (Response: AxiosResponse) => Response.data;

const requests = {
  get: (url: string) => axios.get(url).then(responseBody),
  post: (url: string, body: object) => axios.post(url, body).then(responseBody),
  put: (url: string, body: object) => axios.post(url, body).then(responseBody),
  delete: (url: string) => axios.post(url).then(responseBody),
};

axios.interceptors.response.use(
  async (response) => {
    
    return response;
  },
  (error: AxiosError) => {
    const { status } = error.response as AxiosResponse;
    switch (status) {
      case 404:
        toast.error("Resource not found");
        console.log("fdnfghjndfkngdfogn");
        router.navigate("/not-found");
        break;
      case 500:
        toast.error("Internal Server Error Accured");
        router.navigate("/server-error");
        break;
      default:
        break;
    }
    return Promise.reject(error.message);
  }
);

const Store = {
  apiUrl:"http://localhost:8081/api/products",
  list: (page:number,size:number,brandId?:number,typeId?:number,url?:String) => {
    let requestUrl=url || `products?page=${page-1}&size=${size}`;
    if(brandId!=undefined)
    {
      requestUrl+=`brandId=${brandId}`
    }
    if(typeId!=undefined)
    {
      requestUrl+=`typeId=${typeId}`
    }
    return requests.get(requestUrl);
  },
  Details: (id: number) => requests.get(`products/${id}`),
  types:()=>requests.get('products/types').then(types=>[{id:0,name:"All"},...types]),
  Brands:()=>requests.get('products/brands').then(types=>[{id:0,name:"All"},...types]),
  Search:(keyword:string)=>requests.get(`products?keyword=${keyword}`)
};

const BasketStore = {
  get: async () => {
    try {
      return await basketService.getBasket();
    } catch (error) {
      console.error("Failed to get Basket: ", error);
      throw error;
    }
  },
  addItem: async (product: Product, dispatch: Dispatch) => {
    try {
      const result = await basketService.addItemToBasket(product, 1, dispatch);
      console.log(result);
      return result;
    } catch (error) {
      console.error("Failed to add new item to basket:", error);
      throw error;
    }
  },
  removeItem: async (itemId: number, dispatch: Dispatch) => {
    try {
      await basketService.remove(itemId, dispatch);
    } catch (error) {
      console.error("Failed to remove an item from basket:", error);
      throw error;
    }
  },
  incrementItemQuantity: async (
    itemId: number,
    quantity: number = 1,
    dispatch: Dispatch
  ) => {
    try {
      await basketService.incrementItemQuantity(itemId, quantity, dispatch);
    } catch (error) {
      console.error("Failed to increment item quantity in basket:", error);
      throw error;
    }
  },
  decrementItemQuantity: async (
    itemId: number,
    quantity: number = 1,
    dispatch: Dispatch
  ) => {
    try {
      await basketService.decrementItemQuantity(itemId, quantity, dispatch);
    } catch (error) {
      console.error("Failed to decrement item quantity in basket:", error);
      throw error;
    }
  },
  setBasket: async (basket: Basket, dispatch: Dispatch) => {
    try {
      await basketService.setBasket(basket, dispatch);
    } catch (error) {
      console.error("Failed to set basket:", error);
      throw error;
    }
  },
  deleteBasket: async (basketId: string) => {
    try {
      await basketService.deleteBasket(basketId);
    } catch (error) {
      console.log("Failed to delete the Basket");
      throw error;
    }
  },
};

const agent = {
  Store,
  BasketStore,
};

export default agent;

865780067444092