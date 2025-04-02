import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import ProductDetails from "../../../src/features/catalog/ProductDetails";
import { Provider } from "react-redux";
import { store } from "../../../src/app/store/configureStore";
import { vi } from "vitest";
import agent from "../../../src/app/api/agent";
import { MemoryRouter, Route, Routes } from "react-router-dom";

// Mock dependencies
vi.mock("../../../src/app/api/agent", () => ({
  default: {
    Store: {
      details: vi.fn(),
    },
    Basket: {
      incrementItemQuantity: vi.fn(),
      decrementItemQuantity: vi.fn(),
      addItem: vi.fn(),
    },
  },
}));

describe("ProductDetails", () => {
  const renderWithProviders = (
    ui: React.ReactElement,
    route: string = "/catalog/1"
  ) => {
    window.history.pushState({}, "Test page", route);

    return render(
      <Provider store={store}>
        <MemoryRouter initialEntries={[route]}>
          <Routes>
            <Route path="/catalog/:id" element={ui} />
          </Routes>
        </MemoryRouter>
      </Provider>
    );
  };

  beforeEach(() => {
    vi.clearAllMocks();
  });

  it.skip("renders loading state initially", () => {
    renderWithProviders(<ProductDetails />);
    expect(screen.getByText(/Loading\.\.\.\./i)).toBeInTheDocument();
  });

  it("renders product details when loaded", async () => {
    const mockProduct = {
      id: 1,
      name: "Test Product",
      description: "Test Description",
      price: 100,
      pictureUrl: "/images/products/test-product.jpg",
      productType: "Electronics",
      productBrand: "Test Brand",
    };

    vi.mocked(agent.Store.details).mockResolvedValue(mockProduct);

    renderWithProviders(<ProductDetails />);

    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: /Test Product/i })
      ).toBeInTheDocument();
      expect(screen.getByText(/Test Description/i)).toBeInTheDocument();
      expect(screen.getByText(/₹100\.00/i)).toBeInTheDocument();
      expect(screen.getByText(/Electronics/i)).toBeInTheDocument();
      expect(screen.getByText(/Test Brand/i)).toBeInTheDocument();
    });
  });

  it("handles quantity input changes", async () => {
    const mockProduct = {
      id: 1,
      name: "Test Product",
      description: "Test Description",
      price: 100,
      pictureUrl: "/images/products/test-product.jpg",
      productType: "Electronics",
      productBrand: "Test Brand",
    };

    vi.mocked(agent.Store.details).mockResolvedValue(mockProduct);

    renderWithProviders(<ProductDetails />);

    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: /Test Product/i })
      ).toBeInTheDocument();
    });

    const quantityInput = screen.getByLabelText(/Quantity in Cart/i);
    fireEvent.change(quantityInput, { target: { value: "5" } });

    expect(quantityInput).toHaveValue(5);
  });

  it("adds a new product to the basket", async () => {
    const mockProduct = {
      id: 1,
      name: "Test Product",
      description: "Test Description",
      price: 100,
      pictureUrl: "/images/products/test-product.jpg",
      productType: "Electronics",
      productBrand: "Test Brand",
    };

    vi.mocked(agent.Store.details).mockResolvedValue(mockProduct);
    vi.mocked(agent.Basket.addItem).mockResolvedValue({});

    renderWithProviders(<ProductDetails />);

    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: /Test Product/i })
      ).toBeInTheDocument();
    });

    const addToCartButton = screen.getByRole("button", {
      name: /Add to Cart/i,
    });
    fireEvent.click(addToCartButton);

    await waitFor(() => {
      expect(agent.Basket.addItem).toHaveBeenCalledWith(
        { ...mockProduct, quantity: 0 },
        expect.any(Function)
      );
    });
  });

  it.skip("updates the quantity of an existing product in the basket", async () => {
    const mockProduct = {
      id: 1,
      name: "Test Product",
      description: "Test Description",
      price: 100,
      pictureUrl: "/images/products/test-product.jpg",
      productType: "Electronics",
      productBrand: "Test Brand",
    };

    vi.mocked(agent.Store.details).mockResolvedValue(mockProduct);
    vi.mocked(agent.Basket.incrementItemQuantity).mockResolvedValue({});

    renderWithProviders(<ProductDetails />);

    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: /Test Product/i })
      ).toBeInTheDocument();
    });

    const quantityInput = screen.getByLabelText(/Quantity in Cart/i);
    fireEvent.change(quantityInput, { target: { value: "3" } });

    // Look for either "Update Quantity" or fallback to "Add to Cart" if not found
    let actionButton;
    try {
      actionButton = screen.getByRole("button", {
        name: /Update Quantity/i,
      });
    } catch {
      actionButton = screen.getByRole("button", {
        name: /Add to Cart/i,
      });
    }

    fireEvent.click(actionButton);

    await waitFor(() => {
      expect(agent.Basket.incrementItemQuantity).toHaveBeenCalledWith(
        1,
        3,
        expect.any(Function)
      );
    });
  });

  it("shows 'Product not found' if no product is loaded", async () => {
    vi.mocked(agent.Store.details).mockResolvedValue(null);

    renderWithProviders(<ProductDetails />);

    await waitFor(() => {
      expect(screen.getByText(/Product not found/i)).toBeInTheDocument();
    });
  });
});
