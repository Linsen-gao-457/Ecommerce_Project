import basketService from "../../../src/app/api/basketService";
import axios from "axios";
import { setBasket } from "../../../src/features/basket/basketSlice";
import { Basket, Product } from "../../../src/app/models/basket";

// Mock dependencies
vi.mock("axios");
vi.mock("../../../src/features/basket/basketSlice", () => ({
  setBasket: vi.fn(),
}));

describe("BasketService", () => {
  const mockDispatch = vi.fn();

  beforeEach(() => {
    vi.clearAllMocks();
    localStorage.clear();
  });

  describe("getBasketFromApi", () => {
    it("should fetch the basket from the API", async () => {
      const mockBasket = { id: "1", items: [] };
      vi.mocked(axios.get).mockResolvedValue({ data: mockBasket });

      const result = await basketService.getBasketFromApi();

      expect(axios.get).toHaveBeenCalledWith(
        "http://localhost:8081/api/baskets"
      );
      expect(result).toEqual(mockBasket);
    });

    it("should throw an error if the API call fails", async () => {
      vi.mocked(axios.get).mockRejectedValue(new Error("API Error"));

      await expect(basketService.getBasketFromApi()).rejects.toThrow(
        "Failed to retrieve the basket."
      );
    });
  });

  describe("getBasket", () => {
    it("should retrieve the basket from localStorage", async () => {
      const mockBasket = { id: "1", items: [] };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      const result = await basketService.getBasket();

      expect(result).toEqual(mockBasket);
    });

    it("should throw an error if the basket is not in localStorage", async () => {
      await expect(basketService.getBasket()).rejects.toThrow(
        "Basket not found in local storage"
      );
    });
  });

  describe("addItemToBasket", () => {
    it("should add an item to the basket and update localStorage", async () => {
      const mockProduct: Product = {
        id: 1,
        name: "Test Product",
        price: 100,
        description: "Test Description",
        pictureUrl: "test.jpg",
        productBrand: "Test Brand",
        productType: "Test Type",
      };
      const mockBasket = { id: "1", items: [] };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      const result = await basketService.addItemToBasket(
        mockProduct,
        2,
        mockDispatch
      );

      expect(result.basket.items).toHaveLength(1);
      expect(result.basket.items[0].quantity).toBe(2);
      expect(localStorage.getItem("basket")).toEqual(
        JSON.stringify(result.basket)
      );
      expect(mockDispatch).toHaveBeenCalledWith(setBasket(result.basket));
    });

    it("should create a new basket if none exists", async () => {
      const mockProduct: Product = {
        id: 1,
        name: "Test Product",
        price: 100,
        description: "Test Description",
        pictureUrl: "test.jpg",
        productBrand: "Test Brand",
        productType: "Test Type",
      };

      const result = await basketService.addItemToBasket(
        mockProduct,
        1,
        mockDispatch
      );

      expect(result.basket.items).toHaveLength(1);
      expect(result.basket.items[0].quantity).toBe(1);
      expect(localStorage.getItem("basket")).toBeTruthy();
      expect(mockDispatch).toHaveBeenCalledWith(setBasket(result.basket));
    });
  });

  describe("remove", () => {
    it("should remove an item from the basket", async () => {
      const mockBasket = {
        id: "1",
        items: [{ id: 1, name: "Test Product", quantity: 2, price: 100 }],
      };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      await basketService.remove(1, mockDispatch);

      const updatedBasket = JSON.parse(localStorage.getItem("basket")!);
      expect(updatedBasket.items).toHaveLength(0);
      expect(mockDispatch).toHaveBeenCalledWith(setBasket(updatedBasket));
    });

    it("should clear the basket if it becomes empty", async () => {
      const mockBasket = {
        id: "1",
        items: [{ id: 1, name: "Test Product", quantity: 1, price: 100 }],
      };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      await basketService.remove(1, mockDispatch);
    });
  });

  describe("incrementItemQuantity", () => {
    it("should increment the quantity of an item in the basket", async () => {
      const mockBasket = {
        id: "1",
        items: [{ id: 1, name: "Test Product", quantity: 1, price: 100 }],
      };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      await basketService.incrementItemQuantity(1, 2, mockDispatch);

      const updatedBasket = JSON.parse(localStorage.getItem("basket")!);
      expect(updatedBasket.items[0].quantity).toBe(3);
      expect(mockDispatch).toHaveBeenCalledWith(setBasket(updatedBasket));
    });
  });

  describe("decrementItemQuantity", () => {
    it("should decrement the quantity of an item in the basket", async () => {
      const mockBasket = {
        id: "1",
        items: [{ id: 1, name: "Test Product", quantity: 3, price: 100 }],
      };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      await basketService.decrementItemQuantity(1, 1, mockDispatch);

      const updatedBasket = JSON.parse(localStorage.getItem("basket")!);
      expect(updatedBasket.items[0].quantity).toBe(2);
      expect(mockDispatch).toHaveBeenCalledWith(setBasket(updatedBasket));
    });

    it("should not decrement below 1", async () => {
      const mockBasket = {
        id: "1",
        items: [{ id: 1, name: "Test Product", quantity: 1, price: 100 }],
      };
      localStorage.setItem("basket", JSON.stringify(mockBasket));

      await basketService.decrementItemQuantity(1, 1, mockDispatch);

      const updatedBasket = JSON.parse(localStorage.getItem("basket")!);
      expect(updatedBasket.items[0].quantity).toBe(1);
    });
  });
});
