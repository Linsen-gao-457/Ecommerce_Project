import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import HomePage from "../src/features/home/HomePage";

describe("HomePage Component", () => {
  it("renders the Home Page heading", () => {
    render(<HomePage />);
    expect(screen.getByText(/Home Page/i)).toBeInTheDocument();
  });

  it("renders the heading with the correct role", () => {
    render(<HomePage />);
    screen.debug(); // Logs the rendered DOM to the console
    const heading = screen.getByRole("heading", { level: 2 }); // Check for an <h2> role
    expect(heading).toBeInTheDocument();
  });
});
