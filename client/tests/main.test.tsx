import { render, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { RouterProvider } from "react-router-dom";
import { store } from "../src/app/store/configureStore";
import { router } from "../src/app/router/Routes";

vi.mock("../src/app/api/agent", () => ({
  default: {
    Store: {
      list: vi
        .fn()
        .mockResolvedValue({
          content: [{ id: 1, name: "Product 1" }],
          totalElements: 1,
        }),
      brands: vi.fn().mockResolvedValue([{ id: 1, name: "Brand 1" }]),
      types: vi.fn().mockResolvedValue([{ id: 1, name: "Type 1" }]),
    },
  },
}));

describe("Main Application", () => {
  it("renders the application without crashing", () => {
    render(
      <Provider store={store}>
        <RouterProvider router={router} />
      </Provider>
    );

    // Debug the rendered DOM
    screen.debug();

    // Check for the actual text rendered by your app
    expect(screen.getByText(/Home Page/i)).toBeInTheDocument();
  });
});
