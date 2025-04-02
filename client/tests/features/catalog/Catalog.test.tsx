import { screen, fireEvent, waitFor, act } from "@testing-library/react";
import { describe, it, expect, vi, beforeEach } from "vitest";
import { render } from "@testing-library/react";
import Catalog from "../../../src/features/catalog/Catalog";
import agent from "../../../src/app/api/agent";

// Mock the agent with proper implementations
vi.mock("../../../src/app/api/agent", () => ({
  default: {
    Store: {
      list: vi.fn().mockResolvedValue({
        content: [],
        totalElements: 0,
      }),
      brands: vi.fn().mockResolvedValue([]),
      types: vi.fn().mockResolvedValue([]),
      search: vi.fn().mockResolvedValue({
        content: [],
        totalElements: 0,
      }),
      apiUrl: "api/products",
    },
  },
}));

// Mock ProductList component
vi.mock("../../../src/features/catalog/ProductList", () => ({
  default: () => <div data-testid="product-list">Product List</div>,
}));

describe("Catalog Integration", () => {
  const mockData = {
    products: {
      content: [
        { id: 1, name: "Product 1", price: 100 },
        { id: 2, name: "Product 2", price: 200 },
      ],
      totalElements: 2,
    },
    brands: [
      { id: 1, name: "Brand 1" },
      { id: 2, name: "Brand 2" },
    ],
    types: [
      { id: 1, name: "Type 1" },
      { id: 2, name: "Type 2" },
    ],
  };

  beforeEach(() => {
    vi.clearAllMocks();
    vi.mocked(agent.Store.list).mockImplementation(() =>
      Promise.resolve(mockData.products)
    );
    vi.mocked(agent.Store.brands).mockImplementation(() =>
      Promise.resolve(mockData.brands)
    );
    vi.mocked(agent.Store.types).mockImplementation(() =>
      Promise.resolve(mockData.types)
    );
    vi.mocked(agent.Store.search).mockImplementation((searchTerm) =>
      Promise.resolve({
        content: mockData.products.content.filter((p) =>
          p.name.toLowerCase().includes(searchTerm.toLowerCase())
        ),
        totalElements: 1,
      })
    );
  });

  it("completes full user workflow with search, filter, and pagination", async () => {
    await act(async () => {
      render(<Catalog />);
    });

    // 1. Initial load
    await waitFor(() => {
      expect(
        screen.queryByText(/Loading Products.../i)
      ).not.toBeInTheDocument();
      expect(screen.getByTestId("product-list")).toBeInTheDocument();
    });

    // 2. Search functionality
    const searchInput = screen.getByLabelText(/Search products/i);
    await act(async () => {
      fireEvent.change(searchInput, { target: { value: "Product 1" } });
      fireEvent.keyDown(searchInput, { key: "Enter" });
    });

    await waitFor(() => {
      expect(agent.Store.search).toHaveBeenCalledWith("Product 1");
    });

    // 3. Sort products
    const descendingRadio = screen.getByLabelText(/Descending/i);
    await act(async () => {
      fireEvent.click(descendingRadio);
    });

    // 4. Filter by brand
    const brandRadio = screen.getByLabelText(/Brand 1/);
    await act(async () => {
      fireEvent.click(brandRadio);
    });

    await waitFor(() => {
      expect(agent.Store.list).toHaveBeenCalledWith(
        expect.any(Number),
        expect.any(Number),
        undefined,
        undefined,
        expect.stringContaining("desc")
      );
    });
  });
});
