import { render, screen, fireEvent } from "@testing-library/react";
import { describe, it, expect, vi } from "vitest";
import { BrowserRouter } from "react-router-dom";
import ServerError from "../src/app/errors/ServerError";

const mockNavigate = vi.fn();

// Mock react-router-dom
vi.mock("react-router-dom", async () => {
  const actual = await vi.importActual("react-router-dom");
  return {
    ...actual,
    useNavigate: () => mockNavigate,
  };
});

describe("ServerError Component", () => {
  it("renders the ServerError page correctly", () => {
    render(
      <BrowserRouter>
        <ServerError />
      </BrowserRouter>
    );

    // Check if all elements are rendered
    expect(screen.getByAltText(/500 Server Error/i)).toBeInTheDocument();
    expect(
      screen.getByText(/Oops! Something went wrong./i)
    ).toBeInTheDocument();
    expect(
      screen.getByText(/The server encountered an internal error/i)
    ).toBeInTheDocument();
    expect(
      screen.getByRole("button", { name: /Go Home/i })
    ).toBeInTheDocument();
  });

  it("navigates to home page when 'Go Home' button is clicked", () => {
    render(
      <BrowserRouter>
        <ServerError />
      </BrowserRouter>
    );

    const button = screen.getByRole("button", { name: /Go Home/i });
    fireEvent.click(button);

    expect(mockNavigate).toHaveBeenCalledWith("/");
  });
});
