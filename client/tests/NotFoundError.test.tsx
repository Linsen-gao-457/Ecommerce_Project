import { render, screen, fireEvent } from "@testing-library/react";
import { describe, it, expect, vi } from "vitest";
import { BrowserRouter } from "react-router-dom";
import NotFound from "../src/app/errors/NotFoundError";

const mockNavigate = vi.fn();

// Partially mock `react-router-dom` to keep `BrowserRouter` intact
vi.mock("react-router-dom", async () => {
  const actual = await vi.importActual("react-router-dom");
  return {
    ...actual,
    useNavigate: () => mockNavigate,
  };
});

describe("NotFound Component", () => {
  it("renders the NotFound page correctly", () => {
    render(
      <BrowserRouter>
        <NotFound />
      </BrowserRouter>
    );

    expect(screen.getByAltText(/404 Not Found/i)).toBeInTheDocument();
    expect(screen.getByText(/Oops! Page not found./i)).toBeInTheDocument();
    expect(
      screen.getByText(/We can't seem to find the page you're looking for./i)
    ).toBeInTheDocument();
    expect(
      screen.getByRole("button", { name: /Go Home/i })
    ).toBeInTheDocument();
  });

  it("navigates to the home page when the 'Go Home' button is clicked", () => {
    render(
      <BrowserRouter>
        <NotFound />
      </BrowserRouter>
    );

    const button = screen.getByRole("button", { name: /Go Home/i });
    fireEvent.click(button);
    expect(mockNavigate).toHaveBeenCalledWith("/");
  });
});
